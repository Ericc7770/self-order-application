package com.example.interceptor;

import com.example.common.properties.JWTProperties;
import com.example.common.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class JWTTokenAdminInterceptor implements HandlerInterceptor {

    private final JWTProperties jwtProperties;

    public JWTTokenAdminInterceptor(JWTProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        try{
            log.info("JWT authenticate: {}", token);
            Claims claims = JWTUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get("empId").toString());
            log.info("Current employee ID: {}", empId);
            return true;
        }catch (Exception ex){
            response.setStatus(401);
            return false;
        }
    }
}
