package com.demo.persitence;

import com.demo.domain.Teacher;
import com.demo.domain.TeacherView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author 32050
 */
public interface TeacherRepository extends JpaRepository<Teacher, String> {
    /**
     * 通过教师Code查找教师信息
     * @param code 教师主码
     * @return 返回教师信息，如果没找到则返回null
     */
    Teacher findByCode(String code);

    /**
     * 根据学院代码查找该学院的教师信息
     * @param department 学院代码
     * @param page 分页信息
     * @return 返回教师信息列表
     */
    List<Teacher> getAllByDepartment(String department, Pageable page);

    /**
     * 根据教师编号主键删除教师信息
     * @param code 教师编号
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    void deleteByCode(String code);

    /**
     * 根据院系编号删除教师信息
     * @param departmentCode 院系编号
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    void deleteAllByDepartment(String departmentCode);

    /**
     * 根据学院代码统计该学院老师的数量
     * @param department 学院代码
     * @return 返回统计值
     */
    Long countByDepartment(String department);
}
