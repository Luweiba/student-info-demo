package com.demo.service;

import com.demo.domain.Account;
import com.demo.domain.Course;
import com.demo.domain.Teacher;
import com.demo.domain.TeacherView;
import com.demo.persitence.AccountRepository;
import com.demo.persitence.CourseRepository;
import com.demo.persitence.LessonSelectedRepository;
import com.demo.persitence.TeacherRepository;
import com.demo.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 32050
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final AccountRepository accountRepository;
    private final CourseRepository courseRepository;
    private final LessonSelectedRepository lessonSelectedRepository;

    @Autowired
    public TeacherServiceImpl(LessonSelectedRepository lessonSelectedRepository, TeacherRepository teacherRepository, AccountRepository accountRepository, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.accountRepository = accountRepository;
        this.courseRepository = courseRepository;
        this.lessonSelectedRepository = lessonSelectedRepository;
    }

    @Override
    public JSONResult<Teacher> getTeacherByCode(String teacherCode) {
        Teacher teacher = this.teacherRepository.findByCode(teacherCode);
        if (teacher == null) {
            return new JSONResult<>(403, "教师信息不存在", null);
        }
        return new JSONResult<>(200, "获取教师信息成功", teacher);
    }

    @Override
    public JSONResult<Teacher> save(Teacher teacher) {
        Teacher t = this.teacherRepository.save(teacher);
        return new JSONResult<>(200, "成功插入/修改教师信息", t);
    }

    @Override
    public JSONResult<List<TeacherView>> getAllTeacher(int page, int size) {
        Page<Teacher> teachers = this.teacherRepository.findAll(PageRequest.of(page, size));
        List<TeacherView> teacherViews = teachers.getContent().stream().map(Teacher::toTeacherView).collect(Collectors.toList());
        return new JSONResult<>(200, "获取教师信息列表成功", teacherViews);
    }

    @Override
    public JSONResult<Teacher> del(String teacherCode) {
        Teacher teacher = this.teacherRepository.findByCode(teacherCode);
        if (teacher == null) {
            return new JSONResult<>(403, "教师信息不存在", null);
        }
        this.teacherRepository.deleteByCode(teacherCode);
        Account account = this.accountRepository.findByRolePrimaryKey(teacherCode);
        if (account == null) {
            return new JSONResult<>(403, "教师关联账号信息不存在", null);
        }
        this.accountRepository.deleteByUsername(account.getUsername());
        // 删除教师相关的选课信息
        List<Course> courses = this.courseRepository.findAllByTeacher(teacherCode, PageRequest.of(0, 100));
        courses.forEach(course -> {
            this.lessonSelectedRepository.deleteAllByCourseCode(course.getCode());
            this.courseRepository.deleteByCode(course.getCode());
        });
        return new JSONResult<>(200, "成功删除教师信息以及教师关联账号信息", null);
    }

    @Override
    public JSONResult<Long> countAll() {
        Long count = this.teacherRepository.count();
        return new JSONResult<>(200, "成功获取教师总人数", count);
    }

    @Override
    public JSONResult<Long> countByDepartment(String department) {
        Long count = this.teacherRepository.countByDepartment(department);
        return new JSONResult<>(200, "成功获取学院教师人数", count);
    }

    @Override
    public JSONResult<List<TeacherView>> getTeachersByDepartment(String department, int page, int size) {
        List<Teacher> teachers = this.teacherRepository.getAllByDepartment(department, PageRequest.of(page, size));
        List<TeacherView> teacherViewList = teachers.stream().map(Teacher::toTeacherView).collect(Collectors.toList());
        return new JSONResult<>(200, "获取学院教师信息成功", teacherViewList);
    }
}
