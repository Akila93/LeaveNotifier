package com.lms.config;

import com.lms.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nuwantha on 11/21/16.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

            @Override
            public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws IOException {
                response.sendRedirect("/403");
            }

        }
        http
                .csrf()
                .ignoringAntMatchers("/google-login/**")
                .and()
                .authorizeRequests()
                .antMatchers("/resources/**","/google-login/**","/login","/errorcustom/**","/403").permitAll()
                .antMatchers("/registration").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/users/graph/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/users/**/graph/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
                .logout()
                .permitAll()
                .and()
                //.exceptionHandling().accessDeniedPage("/403");
                .exceptionHandling().authenticationEntryPoint(new MyAuthenticationEntryPoint()).accessDeniedPage("/403");
//                    http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**","/resources/**").permitAll().anyRequest()
//                .authenticated();


    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetailsService userDetailsService = new UserDetailsServiceImpl();
        return userDetailsService;
    }


}
