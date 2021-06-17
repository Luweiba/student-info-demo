package com.demo.service;

import com.demo.domain.Student;
import com.demo.domain.StudentView;
import com.demo.util.JSONResult;

import java.util.List;


/**
 * @author 32050
 */
public interface StudentService {

    /**
     * 根据主码code获取学生信息
     * @param code 学生主码
     * @return 返回学生信息，如果查找不到，则返回null
     */
    JSONResult<Student> getStudentByCode(String code);

    /**
     * 插入/修改 学生信息
     * @param student 学生信息
     * @return 返回插入成功的学生信息
     */
    JSONResult<Student> save(Student student);

    /**
     * 获取学生信息
     * @param page 页数
     * @param size 页大小
     * @return 返回学生信息列表
     */
    JSONResult<List<StudentView>> getAllStudent(int page, int size);

    /**
     * 根据学院代码返回该学院的学生信息
     * @param department 学院代码
     * @param page 页数
     * @param size 页大小
     * @return 学生信息列表
     */
    JSONResult<List<StudentView>> getStudentsByDepartment(String department, int page, int size);

    /**
     * 根据学生主码删除学生信息
     * @param academicCode 学生学号
     * @return 返回被删除的学生信息
     */
    JSONResult<Student> del(String academicCode);

    /**
     * 返回学生总数
     * @return 返回统计值
     */
    JSONResult<Long> countAll();

    /**
     * 根据学院代码查找该学院的学生总数
     * @param department 学院代码
     * @return 返回统计值
     */
    JSONResult<Long> countByDepartment(String department);

}
