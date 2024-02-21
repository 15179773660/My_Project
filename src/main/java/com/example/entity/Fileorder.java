package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fileorder implements Serializable {
    private static final long serialVersionUID = 1L;
    //id
    private Integer id;
    private Integer fileId;
    private Integer score;
    private String orderId;
    private String time;
    private Integer userId;


    private String fileImg;
    private String fileName;
    private String userName;
}
