package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends Account implements Serializable {
    private static final long serialVersion = 1L;

    //id为主键
    private Integer id;
    private String password;
    private String name;
    private String avatar;
    private String role;
    private String phone;
    private String email;
    private String member;
    private Integer score;
    private Double account;

}
