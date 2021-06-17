package com.demo.persitence;

import com.demo.domain.LessonSelected;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

/**
 * @author 32050
 */
public interface LessonSelectedRepository extends JpaRepository<LessonSelected, String> {
    /**
     * 根据学生主码查找选修的所有课程信息
     * @param studentCode 学生主码
     * @return 选修课程信息
     */
    List<LessonSelected> findAllByAcademicCode(String studentCode);

    /**
     * 根据课程主码和开课日期来查找选课信息
     * @param courseCode 课程主码
     * @return 返回选课信息
     */
    List<LessonSelected> findAllByCourseCode(String courseCode);


    /**
     * 根据课程编号主键删除课程信息
     * @param code 课程编号
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    void deleteByCode(String code);

    /**
     * 根据课程主码删除选课信息
     * @param courseCode 课程主码
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    void deleteAllByCourseCode(String courseCode);


    /**
     * 删除与该学号相关的选课信息
     * @param academicCode 学生主码
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    void deleteAllByAcademicCode(String academicCode);

    /**
     * 根据院系主码删除选课信息
     * @param departmentCode 院系主码
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    void deleteAllByDepartment(String departmentCode);

    /**
     * 根据学生学号与课程编号查找选课信息
     * @param academicCode 学生学号
     * @param courseCode 课程编号
     * @return 返回选课信息
     */
    LessonSelected findByAcademicCodeAndCourseCode(String academicCode, String courseCode);

    /**
     * 根据课程主码和学生主码查找选课信息（0或1）
     * @param courseCode 课程主码
     * @param academicCode 学生主码
     * @return 返回选课信息
     */
    List<LessonSelected> findAllByCourseCodeAndAcademicCode(String courseCode, String academicCode);
}
