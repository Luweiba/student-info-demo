package com.demo.service;


import com.demo.domain.Course;
import com.demo.domain.Student;
import com.demo.persitence.CourseRepository;
import com.demo.persitence.LessonSelectedRepository;
import com.demo.persitence.StudentRepository;
import com.demo.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 32050
 */
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final LessonSelectedRepository lessonSelectedRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, LessonSelectedRepository lessonSelectedRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.lessonSelectedRepository = lessonSelectedRepository;
    }


    @Override
    public JSONResult<List<Course>> getCoursesForTeacherByCode(String teacherCode, int page, int size) {
        List<Course> courses = this.courseRepository.findAllByTeacher(teacherCode, PageRequest.of(page, size));
        return new JSONResult<>(200, "获取教师教授的课程信息成功", courses);
    }

    @Override
    public List<Course> getCoursesCouldBeSelected(String studentCode) {
        Student student = this.studentRepository.findStudentByAcademicCode(studentCode);
        if (student == null) {
            return null;
        }
        String majorDepartment = student.getMajorDepartment();
        if (majorDepartment == null) {
            return null;
        }
        List<Course> courses = this.courseRepository.findAllByDepartment(majorDepartment, null);
        // 处理辅修专业
        String auxiliaryDepartment = student.getAuxiliaryDepartment();
        if (auxiliaryDepartment != null && !auxiliaryDepartment.equals(majorDepartment)) {
            List<Course> auxiliaryCourses = this.courseRepository.findAllByDepartment(auxiliaryDepartment, null);
            courses.addAll(auxiliaryCourses);
        }
        return courses;
    }

    @Override
    public JSONResult<List<Course>> getCoursesByDepartmentPaged(String departmentCode, int page, int size) {
        List<Course> courses = this.courseRepository.findAllByDepartment(departmentCode, PageRequest.of(page, size));
        return new JSONResult<>(200, "获取课程信息成功", courses);
    }

    @Override
    public JSONResult<Course> save(Course course) {
        List<Course> text = this.courseRepository.findAllByTeacherAndAndNameAndStartDate(course.getTeacher(), course.getName(), course.getStartDate());
        if (text.size() > 0) {
            return new JSONResult<>(403, "课程信息已存在", null);
        }
        Course c = this.courseRepository.save(course);
        return new JSONResult<>(200, "插入/修改 课程信息成功", c);
    }

    @Override
    public JSONResult<List<Course>> getCoursesPaged(int page, int size) {
        Page<Course> courses = this.courseRepository.findAll(PageRequest.of(page, size));
        return new JSONResult<List<Course>>(200, "获取课程信息成功", courses.getContent());
    }

    @Override
    public JSONResult<Course> del(String courseCode) {
        Course course = this.courseRepository.findByCode(courseCode);
        if (course == null) {
            return new JSONResult<>(403, "课程信息不存在", null);
        }
        this.courseRepository.deleteByCode(courseCode);
        // 删除与该课程相关的选课信息
        this.lessonSelectedRepository.deleteAllByCourseCode(courseCode);
        return new JSONResult<>(200, "成功删除课程信息", course);
    }

    @Override
    public JSONResult<Long> countByDepartment(String department) {
        Long count = this.courseRepository.countByDepartment(department);
        return new JSONResult<>(200, "成功获取统计值", count);
    }

    @Override
    public JSONResult<Long> countAll() {
        Long count = this.courseRepository.count();
        return new JSONResult<>(200, "成功获取统计值", count);
    }

    @Override
    public JSONResult<Long> countCoursesForTeacher(String teacherCode) {
        Long count = this.courseRepository.countByTeacher(teacherCode);
        return new JSONResult<>(200, "获取教师开设课程数成功", count);
    }


}
