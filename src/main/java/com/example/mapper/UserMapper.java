package com.example.mapper;

import cn.hutool.core.util.ObjectUtil;
import com.example.entity.Account;
import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    //查询所有
    List<User> selectAll(User user);

    @Select("select * from user where username = #{username}")
    User selectByUserName(String username);

    //新增用戶信息
    void insert(User user);
    //根據id查詢
    User selectById(Integer id);
    //根據id刪除用戶信息
    void deleteById(Integer id);
    /**
     * 修改
     */
    int updateById(User user);
    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);

}
