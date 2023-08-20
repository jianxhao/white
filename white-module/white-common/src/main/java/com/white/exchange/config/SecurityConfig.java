package com.white.exchange.config;

import com.white.exchange.common.AccessDeniedHandlerImpl;
import com.white.exchange.common.AuthenticationEntryPointImpl;
import com.white.exchange.filters.CheckLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author xh
 * @create 2023-08-20  11:53
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private AuthenticationConfiguration authenticationConfiguration;
    private CheckLoginFilter checkLoginFilter;
    private AccessDeniedHandlerImpl accessDeniedHandler;
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    @Autowired
    public void setAuthenticationConfiguration(AuthenticationConfiguration authenticationConfiguration){
        this.authenticationConfiguration = authenticationConfiguration;
    }
    @Autowired
    public void setCheckLoginFilter(CheckLoginFilter checkLoginFilter){
        this.checkLoginFilter = checkLoginFilter;
    }

    @Autowired
    public void setAccessDeniedHandler(AccessDeniedHandlerImpl accessDeniedHandler){
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Autowired
    public void setAuthenticationEntryPoint(AuthenticationEntryPointImpl authenticationEntryPoint){
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET).permitAll()
                .antMatchers("/api/user/login").anonymous()
                .anyRequest().authenticated();
        http.addFilterBefore(checkLoginFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint);
        http.cors();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
        return authenticationManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
