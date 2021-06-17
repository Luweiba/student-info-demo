package com.demo.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 32050
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherView {
    /**
     * 姓名
     */
    String name;

    /**
     * 教师编码
     */
    String code;

}
