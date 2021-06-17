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
public class Teacher {

    @Id
    private String code;
    @NotNull
    private String name;
    @NotNull
    private String gender;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String department;


    public Teacher(String name, String gender, String phoneNumber, String department) {
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.department = department;
    }

    public TeacherView toTeacherView() {
        return new TeacherView(this.name, this.code);
    }
}
