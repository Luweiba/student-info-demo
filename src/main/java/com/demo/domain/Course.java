package com.demo.domain;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

/**
 * @author 32050
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String code;
    /**
     * 课程名
     */
    @NotNull
    private String name;
    /**
     * 授课老师
     */
    @NotNull
    private String teacher;
    /**
     * 开课院系
     */
    @NotNull
    private String department;
    /**
     * 学时
     */
    @NotNull
    private Integer totalHours;

    /**
     * 学分
     */
    @NotNull
    private Integer credit;
    /**
     * 学期
     */
    @NotNull
    private Integer period;
    /**
     * 开课日期
     */
    @NotNull
    private Date startDate;

    /**
     * 课程人数
     */
    @NotNull
    private Integer capacity;


    public Course(String name, String teacherCode, String departmentCode, Integer credit, Integer totalHours, Integer period, Date startDate, Integer capacity) {
        this.name = name;
        this.teacher = teacherCode;
        this.department = departmentCode;
        this.period = period;
        this.startDate = startDate;
        this.capacity = capacity;
        this.credit = credit;
        this.totalHours = totalHours;
    }

}
