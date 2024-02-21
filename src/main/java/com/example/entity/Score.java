package com.example.entity;

import lombok.Data;

/**
 * 积分专区表
 */
@Data
public class Score {
    private Integer id;
    private String img;
    private String name;
    private String content;
    private String type;
    private Double price;
    private String video;
    private String file;
    private String recommend;
    private String time;
}
