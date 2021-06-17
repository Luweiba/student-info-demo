package com.demo.service;

import com.demo.domain.Account;
import com.demo.domain.Student;
import com.demo.domain.Teacher;
import com.demo.util.JSONResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 32050
 */
public interface AccountService {
    /**
     * 用户登入
     * @param obj 用户信息
     * @param captcha 验证码信息
     * @return 返回创建账户的提示性信息
     */
    JSONResult<HashMap<String, Object>> login(Map<String, Object> obj, Object captcha);

    /**
     * 注册
     * @param account 账号信息
     * @return 返回创建成功的账号信息
     */
    JSONResult<Account> register(Account account);

    /**
     * 初始化账号信息
     * @param academicCode 学生编码
     * @return 返回初始化成功的账号信息
     */
    JSONResult<Account> initAccountForStudent(String academicCode);

    /**
     * 初始化账号信息
     * @param teacherCode 教师编号
     * @return 返回初始化成功的账号信息
     */
    JSONResult<Account> initAccountForTeacher(String teacherCode);
    JSONResult<Account> updateAccount(Account account);
    JSONResult<Object> passwd(Map<String,Object> obj, String phoneCode);

}
