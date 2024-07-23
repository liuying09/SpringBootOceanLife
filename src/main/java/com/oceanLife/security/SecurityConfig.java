package com.oceanLife.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private CustomAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private CustomAuthenticationEntryPoint authenticationEntryPoint ;
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,JwtAuthenticationFilter authFilter) throws Exception {
    	http.authorizeHttpRequests(registry -> registry
    							/* api user */
    							.requestMatchers(HttpMethod.GET, "/api/user/?*").hasAuthority("ADMIN")
        						.requestMatchers(HttpMethod.GET, "/api/user").hasAuthority("ADMIN")
        						.requestMatchers(HttpMethod.PUT, "/api/user/?*").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/user").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/user/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/user/refresh-token").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/user/sendCodeMail/?*").permitAll()
                                /* api activity */
                                .requestMatchers(HttpMethod.POST, "/api/activity").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/activity").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/activity/?*").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/activity").permitAll()
                                /* other */
                                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                                .requestMatchers("/h2-console/**").permitAll() //fot h2db
                                .anyRequest().authenticated()
        						)
    		.csrf(AbstractHttpConfigurer::disable)
        	.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
        	// 目的是告訴 Spring Security 不要創建或使用 HTTP 會話來存儲用戶的安全上下文信息
        	.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        	// 自訂未驗證回傳訊息
            .exceptionHandling(exceptionHandling -> exceptionHandling
            					.authenticationEntryPoint(authenticationEntryPoint)
            					.accessDeniedHandler(accessDeniedHandler))
            .authenticationProvider(authenticationProvider())
        	// for h2db(測試完可刪掉)
        	.headers((headers) -> headers
                     .frameOptions((frameOptions) -> frameOptions.sameOrigin()));

        return http.build();
    }
    
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
    
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    
}