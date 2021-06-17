package com.demo.domain;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author 32050
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    private String code;
    @NotNull
    private String name;
    private String address;
    private String phoneNumber;

    public Department(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
