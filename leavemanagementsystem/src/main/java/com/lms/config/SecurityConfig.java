package com.lms.config;

import com.lms.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by nuwantha on 11/21/16.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .ignoringAntMatchers("/google-login/**")
                .and()
                .authorizeRequests()
                .antMatchers("/resources/**","/google-login/**", "/registration","/login").permitAll()
                .anyRequest().authenticated()
//                .and()
//               .formLogin()
////                .defaultSuccessUrl("/home",true)
//               .loginPage("/login")
//                .permitAll()
                .and()
                .logout()
                .permitAll();

//                    http.antMatcher("/**").authorizeRequests().antMatchers("/", "/login**","/resources/**").permitAll().anyRequest()
//                .authenticated();


    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetailsService userDetailsService=new UserDetailsServiceImpl();
        return userDetailsService;
    }


}
