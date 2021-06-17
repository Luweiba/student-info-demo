package com.demo.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author 32050
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    /**
     * 用户名
     */
    @Id
    private String username;
    /**
     * 用户密码（使用HMAC-256散列后存入数据库）
     */
    private String password;
    /**
     * 角色类型ID：学生（1）、教师（2）、管理员（0）
     */
    private int roleNumber;
    /**
     * 角色主码（管理员为null）
     */
    @Column(unique = true)
    private String rolePrimaryKey;
}
