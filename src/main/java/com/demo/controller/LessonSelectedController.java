package com.demo.controller;

import com.demo.domain.Course;
import com.demo.domain.Department;
import com.demo.domain.LessonSelected;
import com.demo.domain.StudentView;
import com.demo.service.LessonSelectedService;
import com.demo.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

/**
 * @author 32050
 */
@Controller
@RequestMapping("/lesson_select")
public class LessonSelectedController {

    private final LessonSelectedService lessonSelectedService;

    @Autowired
    public LessonSelectedController(LessonSelectedService lessonSelectedService) {
        this.lessonSelectedService = lessonSelectedService;
    }

    @GetMapping("/choose_course")
    @ResponseBody
    public JSONResult<LessonSelected> chooseCourseForStudent(@RequestParam("academicCode") String academicCode, @RequestParam("courseCode") String courseCode) {
        return this.lessonSelectedService.chooseCourseForStudent(courseCode, academicCode);
    }

    @GetMapping("/get_courses_for_student")
    @ResponseBody
    public JSONResult<List<Course>> getCoursesForStudent(@RequestParam("academicCode") String academicCode) {
        return this.lessonSelectedService.getCoursesForStudentByCode(academicCode);
    }

    @GetMapping("/get_student")
    @ResponseBody
    public JSONResult<List<StudentView>> getStudentListForTeacherAndCourseCode(@RequestParam("courseCode") String courseCode) {
        return this.lessonSelectedService.getStudentsByCourseCodeForTeacher(courseCode);
    }

    @GetMapping("/del")
    @ResponseBody
    public JSONResult<LessonSelected> del(@RequestParam("courseCode") String courseCode, @RequestParam("academicCode") String academicCode) {
        return this.lessonSelectedService.del(courseCode, academicCode);
    }

    @GetMapping("/check")
    @ResponseBody
    public JSONResult<Boolean> check(@RequestParam("courseCode") String courseCode, @RequestParam("academicCode") String academicCode) {
        return this.lessonSelectedService.checkForStudent(courseCode, academicCode);
    }


}
