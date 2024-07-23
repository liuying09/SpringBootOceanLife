package com.oceanLife.security;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	   @Autowired
	   private TokenService tokenService;

	   @Autowired
	   private UserDetailsService userDetailsService;
	   
	   
	   @Override
	   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,FilterChain filterChain) 
			   throws ServletException, IOException {
	        
	        // 取得 request header 的值
	        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
	        
	        if (bearerToken != null) {
	            String accessToken = bearerToken.replace("Bearer ", "");
	                        
	            // 解析 token
	            Map<String, Object> tokenPayload = tokenService.parseToken(accessToken);
	            String username = (String) tokenPayload.get("username");
	            long expirationLong = (Integer) tokenPayload.get("exp") * 1000L; // 過期時間轉為毫秒
	            Date expirationDate = new Date(expirationLong);
	            
	            // 查詢使用者
	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
	            
	            // 進行驗證
	            if (tokenService.validateToken(userDetails, username, expirationDate)) {
	            	// Creating an authentication token using UserDetails
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
																			userDetails,
																			null, 
																			userDetails.getAuthorities()
																		);
					// Setting authentication details
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					// Setting the authentication token in the SecurityContext
		            SecurityContextHolder.getContext().setAuthentication(authToken);
	            	
	            }
	        }

	        // 將 request 送往 controller 或下一個 filter
	        filterChain.doFilter(request, response);
	    }
}
