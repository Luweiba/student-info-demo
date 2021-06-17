package com.demo.persitence;

import com.demo.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

/**
 * @author 32050
 */
public interface DepartmentRepository extends JpaRepository<Department, String> {

    /**
     * 根据课程编号主键删除课程信息
     * @param code 课程编号
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    void deleteByCode(String code);

    /**
     * 根据主码查询院系信息
     * @param code 院系主码
     * @return 返回院系信息
     */
    Department findByCode(String code);
}
