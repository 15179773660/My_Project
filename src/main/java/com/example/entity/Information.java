package com.example.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class Information implements Serializable{
        private static final long serialVersionUID = 1L;
        private Integer id;
        private String name;
        private String img;
        private Integer score;
        private String time;
        private String recommend;
        private Integer userId;
        private String status;
        private String descr;
        private String userName;
        private String content;
        private String file;
}
