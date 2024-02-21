package com.example.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 签到信息表
 */
@Data
public class Signin implements Serializable {
    private  static final long serialVersionUID = 1L;
    private Integer id;
    private Integer userId;
    private String time;
    private String day;
}
