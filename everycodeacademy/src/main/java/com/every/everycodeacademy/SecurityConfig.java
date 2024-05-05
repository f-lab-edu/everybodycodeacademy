package com.every.everycodeacademy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests ()
            .requestMatchers("/로그인페이지", "/css/**", "/images/**", "/js/**").permitAll()
            .anyRequest().authenticated()

            .and()
            .formLogin()
            .loginPage("/user/login")
            .loginProcessingUrl("/user/loginprocess")
            .permitAll();
            //.successHandler(로그인 성공 시 실행할 커스터마이즈드 핸들러)
            //.failureHandler(로그인 실패 시 실행할 커스터마이즈드 핸들러);

        http
            .sessionManagement()
            .invalidSessionUrl("/로그인페이지")

            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher ("/user/logout"))
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .permitAll();


        //CSRF 토큰
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CustomPasswordEncoder();
    }
}