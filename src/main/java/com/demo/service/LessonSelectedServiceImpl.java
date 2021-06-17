package com.demo.service;

import com.demo.domain.*;
import com.demo.persitence.CourseRepository;
import com.demo.persitence.DepartmentRepository;
import com.demo.persitence.LessonSelectedRepository;
import com.demo.persitence.StudentRepository;
import com.demo.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 32050
 */
@Service
public class LessonSelectedServiceImpl implements LessonSelectedService {
    private final LessonSelectedRepository lessonSelectedRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public LessonSelectedServiceImpl(LessonSelectedRepository lessonSelectedRepository,
                                     CourseRepository courseRepository,
                                     StudentRepository studentRepository,
                                     DepartmentRepository departmentRepository) {
        this.lessonSelectedRepository = lessonSelectedRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public JSONResult<List<Course>> getCoursesForStudentByCode(String studentCode) {
        List<LessonSelected> lessonsSelected = this.lessonSelectedRepository.findAllByAcademicCode(studentCode);
        List<Course> courses = lessonsSelected.stream().map(lessonSelected -> this.courseRepository.findByCode(lessonSelected.getCourseCode())).collect(Collectors.toList());
        return new JSONResult<>(200, "获取选课信息成功", courses);
    }

    @Override
    public JSONResult<List<StudentView>> getStudentsByCourseCodeForTeacher(String courseCode) {
        List<LessonSelected> lessonSelectedList = this.lessonSelectedRepository.findAllByCourseCode(courseCode);
        List<StudentView> students = lessonSelectedList.stream().map(lessonSelected -> this.studentRepository.findStudentByAcademicCode(lessonSelected.getAcademicCode())).filter(Objects::nonNull).map(Student::toStudentView).collect(Collectors.toList());
        return new JSONResult<>(200, "获取选修该课程的学生信息成功", students);
    }


    @Override
    public JSONResult<LessonSelected> chooseCourseForStudent(String courseCode, String academicCode) {
        LessonSelected test = this.lessonSelectedRepository.findByAcademicCodeAndCourseCode(academicCode, courseCode);
        if (test != null) {
            return new JSONResult<>(403, "选课信息已存在", test);
        }
        Student student = this.studentRepository.findStudentByAcademicCode(academicCode);
        if (student == null) {
            return new JSONResult<>(403, "学生信息不存在", null);
        }
        Course course = this.courseRepository.findByCode(courseCode);
        if (course == null) {
            return new JSONResult<>(403, "课程信息不存在", null);
        }
        Department department = this.departmentRepository.getById(course.getDepartment());
        LessonSelected lessonSelected = new LessonSelected(courseCode, academicCode, student.getGrade(), course.getDepartment(), null, student.getDegreeLevel(), course.getStartDate());
        var l = this.lessonSelectedRepository.save(lessonSelected);
        return new JSONResult<>(200, "选课成功", l);
    }

    @Override
    public JSONResult<LessonSelected> del(String code, String academicCode) {
        LessonSelected lessonSelected = this.lessonSelectedRepository.findByAcademicCodeAndCourseCode(academicCode, code);
        if (lessonSelected == null) {
            return new JSONResult<>(403, "选课信息不存在", null);
        }
        this.lessonSelectedRepository.deleteByCode(lessonSelected.getCode());
        return new JSONResult<>(200, "删除选课信息成功", lessonSelected);
    }

    @Override
    public JSONResult<Boolean> checkForStudent(String courseCode, String academicCode) {
        List<LessonSelected> lessonSelectedList = this.lessonSelectedRepository.findAllByCourseCodeAndAcademicCode(courseCode, academicCode);
        boolean ans = false;
        if (lessonSelectedList.size() > 0) {
            ans = true;
        }
        return new JSONResult<>(200, "检查学生选课信息成功", ans);
    }
}
