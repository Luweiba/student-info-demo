package com.demo.persitence;

import com.demo.domain.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

/**
 * @author 32050
 */
public interface CourseRepository extends JpaRepository<Course, String> {

    /**
     * 找到教师参与的课程
     * @param teacherCode 教师主码
     * @param page 分页信息
     * @return 返回课程信息
     */
    List<Course> findAllByTeacher(String teacherCode, Pageable page);

    /**
     * 找到学院开设的课程信息
     * @param departmentCode 学院主码
     * @param page 分页信息
     * @return 返回课程信息
     */
    List<Course> findAllByDepartment(String departmentCode, Pageable page);

    /**
     * 根据课程编号查找课程信息
     * @param courseCode 课程编号
     * @return 返回课程信息
     */
    Course findByCode(String courseCode);

    /**
     * 根据课程名与教师编码查找课程信息
     * @param name 课程名
     * @param teacherCode 教师编号
     * @return 返回课程信息
     */
    Course findByNameAndTeacher(String name, String teacherCode);

    /**
     * 根据院系主码统计条目信息
     * @param department 院系主码
     * @return 返回统计值
     */
    Long countByDepartment(String department);

    /**
     * 根据教师主码查找该教师开设的课程
     * @param teacherCode 教师主码
     * @return 返回课程数量
     */
    Long countByTeacher(String teacherCode);

    /**
     * 根据教师信息、课程名、开课日期查找课程信息
     * @param teacherCode 教师主码
     * @param courseName 课程名
     * @param date 开课日期
     * @return 返回课程信息列表
     */
    List<Course> findAllByTeacherAndAndNameAndStartDate(String teacherCode, String courseName, Date date);

    /**
     * 根据课程编号主键删除课程信息
     * @param code 课程编号
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    void deleteByCode(String code);

    /**
     * 根据院系编号删除课程信息
     * @param department 院系编号
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    void deleteAllByDepartment(String department);
}
