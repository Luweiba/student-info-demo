package com.demo.controller;


import com.demo.domain.Account;
import com.demo.service.AccountService;
import com.demo.util.JSONResult;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 32050
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final Producer producer;

    @Autowired
    public AccountController(AccountService accountService, Producer producer) {
        this.accountService = accountService;
        this.producer = producer;
    }

    @PostMapping("/login")
    @ResponseBody
    public JSONResult<HashMap<String, Object>> login(@RequestBody Map<String, Object> obj, HttpServletRequest request) {
        Object captcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        System.out.println("Get Captcha: " + captcha + "from " + request.getSession());
        return this.accountService.login(obj, captcha);
    }

    @PostMapping("/register")
    @ResponseBody
    public JSONResult<Account> register(Account account) {
        return accountService.register(account);
    }

    @PostMapping("/passwd")
    @ResponseBody
    public JSONResult<Object> passwd(@RequestBody Map<String, Object> obj, HttpServletRequest request) {
        return null;
    }

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        // 生成文字校验码
        String text = this.producer.createText();
        // 生成图片验证码
        BufferedImage image = this.producer.createImage(text);
        // 保存验证码到Session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        System.out.println("Set Captcha: " + text  + "from " + request.getSession());
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

}
