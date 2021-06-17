package com.demo.service;

import com.alibaba.fastjson.JSON;
import com.demo.domain.Course;
import com.demo.util.JSONResult;

import java.util.List;

/**
 * @author 32050
 */
public interface CourseService {
    /**
     * 根据教师主键Code找到教师参与的课程信息
     * @param teacherCode 教师主码
     * @param page 页数
     * @param size 页大小
     * @return 返回课程列表
     */
    JSONResult<List<Course>> getCoursesForTeacherByCode(String teacherCode, int page, int size);

    /**
     * 获取学生可以选择的课程
     * @param studentCode 学生主码
     * @return 返回可以选择得到课程列表
     */
    List<Course> getCoursesCouldBeSelected(String studentCode);


    /**
     * 获取某院系开放的课程（提供分页功能）
     * @param departmentCode 院系编码
     * @param page 页数
     * @param size 页大小
     * @return 返回带有附加信息和状态码的JSON结果
     */
    JSONResult<List<Course>> getCoursesByDepartmentPaged(String departmentCode, int page, int size);

    /**
     * 插入/修改 课程信息
     * @param course 课程信息
     * @return 返回提示信息
     */
    JSONResult<Course> save(Course course);

    /**
     * 返回具有分页功能的课程信息
     * @param page 页数
     * @param size 页大小
     * @return 返回课程信息列表
     */
    JSONResult<List<Course>> getCoursesPaged(int page, int size);

    /**
     * 根据课程主码删除课程信息（会级联删除选课信息表中的部分内容）
     * @param courseCode 课程主码
     * @return 返回删除的课程信息
     */
    JSONResult<Course> del(String courseCode);

    /**
     * 根据院系主码统计条目信息
     * @param department 院系主码
     * @return 返回统计值
     */
    JSONResult<Long> countByDepartment(String department);

    /**
     * 统计所有条目的数量
     * @return 返回统计值
     */
    JSONResult<Long> countAll();

    /**
     * 统计教师开设的课程数量
     * @param teacherCode 教师主码
     * @return 返回统计值
     */
    JSONResult<Long> countCoursesForTeacher(String teacherCode);
}
