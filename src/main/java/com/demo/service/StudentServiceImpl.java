package com.demo.service;

import com.demo.domain.Account;
import com.demo.domain.Student;
import com.demo.domain.StudentView;
import com.demo.persitence.AccountRepository;
import com.demo.persitence.LessonSelectedRepository;
import com.demo.persitence.StudentRepository;
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
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final AccountRepository accountRepository;
    private final LessonSelectedRepository lessonSelectedRepository;
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, AccountRepository accountRepository, LessonSelectedRepository lessonSelectedRepository) {
        this.studentRepository = studentRepository;
        this.accountRepository = accountRepository;
        this.lessonSelectedRepository = lessonSelectedRepository;
    }

    @Override
    public JSONResult<Student> getStudentByCode(String code) {
        Student student = this.studentRepository.findStudentByAcademicCode(code);
        if (student == null) {
            return new JSONResult<>(403, "学生信息不存在", null);
        }
        return new JSONResult<>(200, "获取学生信息成功", student);
    }

    @Override
    public JSONResult<Student> save(Student student) {
        Student s = this.studentRepository.save(student);
        return new JSONResult<>(200, "成功插入/修改 学生信息", s);
    }

    @Override
    public JSONResult<List<StudentView>> getAllStudent(int page, int size) {
        Page<Student> students = this.studentRepository.findAll(PageRequest.of(page, size));
        List<StudentView> studentViews = students.getContent().stream().map(Student::toStudentView).collect(Collectors.toList());
        return new JSONResult<>(200, "获取学生信息成功", studentViews);
    }

    @Override
    public JSONResult<List<StudentView>> getStudentsByDepartment(String department, int page, int size) {
        List<Student> students = this.studentRepository.getAllByMajorDepartment(department, PageRequest.of(page, size));
        List<StudentView> studentViewList = students.stream().map(Student::toStudentView).collect(Collectors.toList());
        return new JSONResult<>(200, "获取学院学生信息列表成功", studentViewList);
    }

    @Override
    public JSONResult<Student> del(String academicCode) {
        Student student = this.studentRepository.findStudentByAcademicCode(academicCode);
        if (student == null) {
            return new JSONResult<>(403, "学生信息不存在", null);
        }
        this.studentRepository.deleteByAcademicCode(academicCode);
        // 删除关联账号信息
        Account account = this.accountRepository.findByRolePrimaryKey(student.getAcademicCode());
        if (account == null) {
            return new JSONResult<>(403, "学生账号信息不存在", null);
        }
        this.accountRepository.deleteByUsername(account.getUsername());
        this.lessonSelectedRepository.deleteAllByAcademicCode(academicCode);
        return new JSONResult<>(200, "学生信息以及关联账号信息已删除", student);
    }

    @Override
    public JSONResult<Long> countAll() {
        Long count = this.studentRepository.count();
        return new JSONResult<>(200, "成功获取学生总人数", count);
    }

    @Override
    public JSONResult<Long> countByDepartment(String department) {
        Long count = this.studentRepository.countByMajorDepartment(department);
        return new JSONResult<>(200, "成功获取学院学生人数", count);
    }
}
