package com.demo.domain;


import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * @author 32050
 */
@Data
@Entity
public class Student {
    @NotNull
    private String name;
    @NotNull
    private String gender;
    @Id
    private String academicCode;
    @NotNull
    private String idNumber;
    private String phoneNumber;
    private String email;
    private Date birthday;
    private String auxiliaryDepartment;
    @NotNull
    private String majorDepartment;
    private String degreeLevel;
    private Integer credit;
    private String dorm;
    private Integer grade;
    private String address;


    public StudentView toStudentView() {
        return new StudentView(this.name, this.academicCode);
    }
}


