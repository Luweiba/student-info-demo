package com.demo.service;


import com.demo.domain.Course;
import com.demo.domain.LessonSelected;
import com.demo.domain.Student;
import com.demo.domain.StudentView;
import com.demo.util.JSONResult;

import java.sql.Date;
import java.util.List;

/**
 * @author 32050
 */
public interface LessonSelectedService {

    /**
     * 根据学生主键Code找到的学生选修的课程信息
     * @param studentCode 教师主码
     * @return 返回课程列表
     */
    JSONResult<List<Course>> getCoursesForStudentByCode(String studentCode);



    /**
     * 根据课程和选课信息查找选修该课程的学生主码信息
     * @param courseCode 课程主码
     * @return 学生主码信息
     */
    JSONResult<List<StudentView>> getStudentsByCourseCodeForTeacher(String courseCode);

    /**
     * 添加学生-课程选课信息
     * @param courseCode 课程编号
     * @param academicCode 学生编号
     * @return 返回选课信息
     */
    JSONResult<LessonSelected> chooseCourseForStudent(String courseCode, String academicCode);

    /**
     * 根据课程主码信息删除选课信息
     * @param code 课程主码
     * @param academicCode 学生学号
     * @return 返回删除的选课信息
     */
    JSONResult<LessonSelected> del(String code, String academicCode);

    /**
     * 检查学生有没有选修该课程
     * @param courseCode 课程主码
     * @param academicCode 学生主码
     * @return 返回选课信息
     */
    JSONResult<Boolean> checkForStudent(String courseCode, String academicCode);
}
