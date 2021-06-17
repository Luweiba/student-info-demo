package com.demo.controller;


import com.demo.domain.Department;
import com.demo.service.DepartmentService;
import com.demo.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 32050
 */
@Controller
@RequestMapping("/dpt")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * 获取院系信息
     * @return 返回院系列表信息
     */
    @GetMapping("/info")
    @ResponseBody
    public JSONResult<List<Department>> getAllDpt(@RequestParam("page") int page, @RequestParam("size") int size) {
        return this.departmentService.getAllDepartment(page, size);
    }




    @PostMapping("/add")
    @ResponseBody
    public JSONResult<Department> addDepartment(@RequestBody Department department) {
         return this.departmentService.save(department);
    }

    @GetMapping("/del")
    @ResponseBody
    public JSONResult<Department> del(@RequestParam("code") String code) {
        return this.departmentService.del(code);
    }

    @GetMapping("/count_all")
    @ResponseBody
    public JSONResult<Long> countAll() {
        return this.departmentService.countAll();
    }
}
