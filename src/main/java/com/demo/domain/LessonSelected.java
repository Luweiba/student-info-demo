package com.demo.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

/**
 * @author 32050
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class LessonSelected {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String code;
    @NotNull
    private String courseCode;
    @NotNull
    private String academicCode;
    private Integer grade;
    @NotNull
    private String department;
    private String description;
    private String degreeLevel;
    @NotNull
    private Date startDate;

    public LessonSelected(String courseCode, String academicCode, Integer grade, String department, String description, String degreeLevel, Date startDate) {
        this.academicCode = academicCode;
        this.courseCode = courseCode;
        this.grade = grade;
        this.department = department;
        this.description = description;
        this.startDate = startDate;
        this.degreeLevel = degreeLevel;
    }
}
