package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.demo.domain.Account;
import com.demo.domain.Student;
import com.demo.domain.Teacher;
import com.demo.domain.TeacherView;
import com.demo.service.AccountService;
import com.demo.service.TeacherService;
import com.demo.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 32050
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final AccountService accountService;

    @Autowired
    public TeacherController(TeacherService teacherService, AccountService accountService) {
        this.teacherService = teacherService;
        this.accountService = accountService;
    }

    @GetMapping("/info")
    @ResponseBody
    public JSONResult<Teacher> getTeacherInfo(@RequestParam("code") String code) {
        return this.teacherService.getTeacherByCode(code);
    }

    @GetMapping("/info_all")
    @ResponseBody
    public JSONResult<List<TeacherView>> getAllTeacher(@RequestParam("page") int page, @RequestParam("size") int size) {
        return this.teacherService.getAllTeacher(page, size);
    }

    @GetMapping("/info_department")
    @ResponseBody
    public JSONResult<List<TeacherView>> getTeachersByDepartment(@RequestParam("department") String department, @RequestParam("page") int page, @RequestParam("size") int size) {
        return this.teacherService.getTeachersByDepartment(department, page, size);
    }

    @PostMapping("add")
    @ResponseBody
    public JSONResult<Account> addTeacher(@RequestBody Teacher teacher) {
        JSONResult<Teacher> result =  this.teacherService.save(teacher);
        if (result.getStatus() == 200) {
            return this.accountService.initAccountForTeacher(result.getContent().getCode());
        } else {
            return new JSONResult<>(403, "添加教师信息出错，错误信息为：" + result.getMessage(), null);
        }
    }

    @GetMapping("/del")
    @ResponseBody
    public JSONResult<Teacher> del(@RequestParam("teacherCode") String teacherCode) {
        return this.teacherService.del(teacherCode);
    }

    @GetMapping("count_all")
    @ResponseBody
    public JSONResult<Long> countAll() {
        return this.teacherService.countAll();
    }

    @GetMapping("count")
    @ResponseBody
    public JSONResult<Long> count(@RequestParam("department") String department) {
        return this.teacherService.countByDepartment(department);
    }
}
