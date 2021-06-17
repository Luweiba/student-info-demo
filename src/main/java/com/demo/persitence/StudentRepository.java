package com.demo.persitence;

import com.demo.domain.LessonSelected;
import com.demo.domain.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author 32050
 */
public interface StudentRepository extends JpaRepository<Student, String> {
    /**
     * 通过学号academicCode返回学生信息
     * @param academicCode 学号
     * @return 学生信息
     */
    Student findStudentByAcademicCode(String academicCode);

    /**
     * 根据学号删除学生信息
     * @param academicCode 学号
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    void deleteByAcademicCode(String academicCode);

    /**
     * 根据院系编号删除学生信息
     * @param departmentCode 院系编号
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    void deleteAllByMajorDepartment(String departmentCode);

    /**
     * 根据学院代码查找学生总数
     * @param department 学院代码
     * @return 返回统计值
     */
    Long countByMajorDepartment(String department);

    /**
     * 根据学院代码返回该学院的学生信息
     * @param department 学院代码
     * @param page 分页信息
     * @return 返回学生信息列表
     */
    List<Student> getAllByMajorDepartment(String department, Pageable page);
}
