package com.oceanLife.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        responseBody.put("message", "身分驗證失敗，尚未登入或憑證已過期");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(responseBody));
        out.flush();
		
	}

}
