package com.demo.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.util.TokenUtil;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 32050
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (true) {
            return true;
        }
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        if (token != null) {
            DecodedJWT result = TokenUtil.verify(token);
            if (result != null) {
                request.getSession().setAttribute("username", result.getClaim("username").asString());
                return true;
            }
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            JSONObject json = new JSONObject();
            json.put("status", 402);
            json.put("message", "token verify fail");
            response.getWriter().append(json.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
        return false;
    }
}
