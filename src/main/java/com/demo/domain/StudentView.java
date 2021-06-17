package com.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 32050
 * 用于精简前端对于学生信息的展示
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentView {
    /**
     * 姓名
     */
    String name;
    /**
     * 学号
     */
    String academicCode;

}
