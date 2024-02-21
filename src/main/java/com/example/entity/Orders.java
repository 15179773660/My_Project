package com.example.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 签到信息表
 */
@Data
public class Orders implements Serializable {
    private  static final long serialVersionUID = 1L;
    private Integer id;
    private Integer courseId;
    private Double price;
    private String orderId;
    private String time;
    private Integer userId;



    private String courseImg;
    private String courseName;
    private String userName;
    private String courseType;

}
