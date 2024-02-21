package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    //id
    private Integer id;
    private String img;
    private String name;
    private String content;
    private String type;
    private Double price;
    private String video;
    private String file;
    private Double discount;
    private String recommend;
    private String time;
}
