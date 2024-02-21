package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Constants;
import com.example.common.enums.MemberEnum;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;

import com.example.entity.Admin;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.UserMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    //查詢所有
    public List<User> selectAll(User user) {
        return userMapper.selectAll(user);
    }
    //分頁查詢
    public PageInfo<User> selectPage(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectAll(user);
        return PageInfo.of(list);
    }
    //添加用戶信息
    public void add(User user) {
        //判断一下该数据在数据库里有没有
        User dbUser=userMapper.selectByUserName(user.getUsername());
        if(ObjectUtil.isNotEmpty(dbUser)){
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        //初始化一些页面没有填写的信息
        if (ObjectUtil.isEmpty(user.getPassword())){//判断是否为空
            user.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if (ObjectUtil.isEmpty(user.getName())){
            user.setName(user.getUsername());
        }
        user.setMember(MemberEnum.NO.info);//设置初始用户为会员
        user.setRole(RoleEnum.USER.name());
        userMapper.insert(user);
    }
    //根據id查詢用戶信息
    public User selectById(Integer id) {
        User user= userMapper.selectById(id);
        // 生成token
        String tokenData = user.getId() + "-" + RoleEnum.USER.name();
        String token = TokenUtils.createToken(tokenData, user.getPassword());
        user.setToken(token);
        return user;
    }
    //根據id刪除用戶
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }
    //根據id批量刪除用戶
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            userMapper.deleteById(id);
        }

    }
    //修改用戶信息
    public void updateById(User user) {
        userMapper.updateById(user);
    }
    /**
     * 登录
     */
    public Account login(Account account) {
        Account dbUser = userMapper.selectByUserName(account.getUsername());
        if (ObjectUtil.isNull(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbUser.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        // 生成token
        String tokenData = dbUser.getId() + "-" + RoleEnum.USER.name();
        String token = TokenUtils.createToken(tokenData, dbUser.getPassword());
        dbUser.setToken(token);
        return dbUser;
    }
    /**
     * 注册
     */
    public void register(Account account) {
        User user = new User();
        BeanUtils.copyProperties(account, user);
        add(user);
    }
    /**
     * 修改密码
     */
    public void updatePassword(Account account) {
        User dbUser = userMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbUser.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        dbUser.setPassword(account.getNewPassword());
        userMapper.updateById(dbUser);
    }

    public void recharge(Double account) {
        Account currentUser= TokenUtils.getCurrentUser();
        User user = userMapper.selectById(currentUser.getId());
        user.setAccount(user.getAccount() + account);
        //是否充值500
        if(account>=500){
            user.setMember(MemberEnum.YES.info);
        }
        userMapper.updateById(user);
    }
}
