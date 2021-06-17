package com.demo.controller;



import com.demo.domain.Course;
import com.demo.service.CourseService;
import com.demo.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 32050
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/info_department")
    @ResponseBody
    public JSONResult<List<Course>> getCoursesByDepartmentPaged(@RequestParam("code") String code, @RequestParam("page") int page, @RequestParam("size") int size) {
        return this.courseService.getCoursesByDepartmentPaged(code, page, size);
    }

    @GetMapping("/info_all")
    @ResponseBody
    public JSONResult<List<Course>> getCoursesAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return this.courseService.getCoursesPaged(page, size);
    }

    @GetMapping("/info")
    @ResponseBody
    public JSONResult<List<Course>> getCourseByDepartment(@RequestParam("department") String department, @RequestParam("page") int page, @RequestParam("size") int size) {
        return this.courseService.getCoursesByDepartmentPaged(department, page, size);
    }

    @GetMapping("/get_courses_for_teacher")
    @ResponseBody
    public JSONResult<List<Course>> getCoursesForTeacher(@RequestParam("teacherCode") String teacherCode, @RequestParam("page") int page, @RequestParam("size") int size) {
        return this.courseService.getCoursesForTeacherByCode(teacherCode, page, size);
    }

    @GetMapping("/count_courses_for_teacher")
    @ResponseBody
    public JSONResult<Long> countCoursesForTeacher(@RequestParam("teacherCode") String teacherCode) {
        return this.courseService.countCoursesForTeacher(teacherCode);
    }

    @PostMapping("add")
    @ResponseBody
    public JSONResult<Course> addCourse(@RequestBody Course course) {
        return this.courseService.save(course);
    }

    @GetMapping("/del")
    @ResponseBody
    public JSONResult<Course> del(@RequestParam("courseCode") String courseCode) {
        return this.courseService.del(courseCode);
    }

    @GetMapping("/count")
    @ResponseBody
    public JSONResult<Long> countByDepartment(@RequestParam("department") String department) {
        return this.courseService.countByDepartment(department);
    }

    @GetMapping("/count_all")
    @ResponseBody
    public JSONResult<Long> countAll() {
        return this.courseService.countAll();
    }


}
