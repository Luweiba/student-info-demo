package com.demo.controller;


import com.demo.domain.Account;
import com.demo.domain.LessonSelected;
import com.demo.domain.Student;
import com.demo.domain.StudentView;
import com.demo.service.AccountService;
import com.demo.service.StudentService;
import com.demo.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 32050
 */
@Controller
@RequestMapping("/stu")
public class StudentController {

    private final StudentService studentService;
    private final AccountService accountService;

    @Autowired
    public StudentController(StudentService studentService, AccountService accountService) {
        this.studentService = studentService;
        this.accountService = accountService;
    }

    /**
     * 根据学生主码查询教师信息
     * @param academicCode 学生主码
     * @return 返回学生信息，如果没查到，则返回带有错误提示的信息
     */
    @GetMapping("/info")
    @ResponseBody
    public JSONResult<Student> getStudentInfo(@RequestParam("code") String academicCode) {
        return this.studentService.getStudentByCode(academicCode);
    }

    @GetMapping("/info_all")
    @ResponseBody
    public JSONResult<List<StudentView>> getAllStudent(@RequestParam("size") int size, @RequestParam("page") int page) {
        return this.studentService.getAllStudent(page, size);
    }

    @PostMapping("/add")
    @ResponseBody
    public JSONResult<Account> addStudent(@RequestBody Student student) {
        JSONResult<Student> result =  this.studentService.save(student);
        if (result.getStatus() == 200) {
            return this.accountService.initAccountForStudent(result.getContent().getAcademicCode());
        } else {
            return new JSONResult<>(403, "添加学生信息出错, 错误信息：" + result.getMessage(), null);
        }
    }

    @GetMapping("/del")
    @ResponseBody
    public JSONResult<Student> del(@RequestParam("academicCode") String academicCode) {
        return this.studentService.del(academicCode);
    }

    @GetMapping("count_all")
    @ResponseBody
    public JSONResult<Long> countAll() {
        return this.studentService.countAll();
    }

    @GetMapping("/count")
    @ResponseBody
    public JSONResult<Long> count(@RequestParam("department") String department) {
        return this.studentService.countByDepartment(department);
    }

    @GetMapping("/info_department")
    @ResponseBody
    public JSONResult<List<StudentView>> getStudentsByDepartment(@RequestParam("department") String department, @RequestParam("page") int page, @RequestParam("size") int size) {
        return this.studentService.getStudentsByDepartment(department, page, size);
    }
}
