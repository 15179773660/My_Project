package com.example.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 积分兑换信息表
 */
@Data
public class Scoreorder implements Serializable {
    private  static final long serialVersionUID = 1L;
    private Integer id;
    private Integer scoreId;
    private Integer score;
    private String orderId;
    private String time;
    private Integer userId;

    private String scoreImg;
    private String scoreName;
    private String userName;


}
