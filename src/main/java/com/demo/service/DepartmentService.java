package com.demo.service;

import com.demo.domain.Department;
import com.demo.domain.LessonSelected;
import com.demo.util.JSONResult;

import java.util.List;

/**
 * @author 32050
 */
public interface DepartmentService {

    /**
     * 插入/修改 院系信息
     * @param department 院系信息
     * @return 返回成功插入或修改的院系信息
     */
    JSONResult<Department> save(Department department);

    /**
     * 获取院系信息
     * @param page 分页，页数
     * @param size 分页，页大小
     * @return 返回院系信息列表
     */
    JSONResult<List<Department>> getAllDepartment(int page, int size);

    /**
     * 删除院系信息
     * @param code 院系主码
     * @return 返回删除的院系信息
     */
    JSONResult<Department> del(String code);

    /**
     * 统计院系的数量
     * @return 返回统计值
     */
    JSONResult<Long> countAll();

}
