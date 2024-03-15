package com.oceanLife.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.oceanLife.filter.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,JwtAuthenticationFilter authFilter) throws Exception {
        http.authorizeHttpRequests(registry -> registry
        						.requestMatchers(HttpMethod.GET, "/api/user").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/user").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/user/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/user/refresh-token").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/activity").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/activity").hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/activity/?*").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/activity").permitAll()
                                .anyRequest().authenticated()
        						)
                .csrf(AbstractHttpConfigurer::disable)
        		.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//    
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,BCryptPasswordEncoder passwordEncoder) {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}