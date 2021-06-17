package com.demo;

import com.demo.domain.Account;
import com.demo.domain.Course;
import com.demo.domain.Department;
import com.demo.domain.Teacher;
import com.demo.service.AccountService;
import com.demo.service.CourseService;
import com.demo.service.DepartmentService;
import com.demo.service.TeacherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;


/**
 * @author 32050
 */
@SpringBootApplication
public class StudentInfoDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentInfoDemoApplication.class, args);
    }


    /**
     * 创建超级用户
     */
    @Bean
    CommandLineRunner createAdmin(AccountService accountService) {
        return args -> {
            Account account = new Account("admin", "990415", 0, null);
            accountService.register(account);
        };
    }
    /**
     * 插入数据
     */
    CommandLineRunner insertData(CourseService courseService, DepartmentService departmentService, TeacherService teacherService) {
        return args -> {
            Department department = new Department("0001","信息科学与工程学院", null, null);
            System.out.println(department.getCode());
            var result = departmentService.save(department);
            System.out.println(result);
            Teacher teacher = new Teacher("000001", "陈渤文", "男", null, department.getCode());
            System.out.println(teacherService.save(teacher));
            Course course = new Course("计算机网络", teacher.getCode(), department.getCode(), 2,  64, 1, new Date(System.currentTimeMillis()-5400000), 64);
            System.out.println(course.getCode());
            System.out.println(courseService.save(course));

        };
    }

}
