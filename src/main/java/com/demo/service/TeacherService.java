package com.demo.service;

import com.demo.domain.Teacher;
import com.demo.domain.TeacherView;
import com.demo.util.JSONResult;

import java.util.List;

/**
 * @author 32050
 */
public interface TeacherService {
    /**
     * 根据教师主码获取教师信息
     * @param teacherCode 教师主码
     * @return 返回教师信息，如果没找到则返回null
     */
    JSONResult<Teacher> getTeacherByCode(String teacherCode);

    /**
     * 插入/修改 教师信息
     * @param teacher 教师信息
     * @return 返回插入成功的教师信息
     */
    JSONResult<Teacher> save(Teacher teacher);

    /**
     * 获取教师信息列表
     * @param page 页数
     * @param size 页面大小
     * @return 返回教师信息列表
     */
    JSONResult<List<TeacherView>> getAllTeacher(int page, int size);

    /**
     * 根据教师主码信息删除教师信息
     * @param teacherCode 教师主码
     * @return 返回删除的教师信息
     */
    JSONResult<Teacher> del(String teacherCode);

    /**
     * 统计教师总人数
     * @return 返回统计值
     */
    JSONResult<Long> countAll();

    /**
     * 根据院系代码查找该院系的老师数量
     * @param department 院系代码
     * @return 返回老师数量
     */
    JSONResult<Long> countByDepartment(String department);

    /**
     * 根据学院代码查找该学院的教师信息
     * @param department 学院代码
     * @param page 页数
     * @param size 页大小
     * @return 返回教师信息列表
     */
    JSONResult<List<TeacherView>> getTeachersByDepartment(String department, int page, int size);
}
