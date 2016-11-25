package com.lms.service;

import com.lms.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by nuwantha on 11/21/16.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    LoginService loginService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LoginUser user = loginService.getLoginUserByUserName(username);
            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getEmail(), AuthorityUtils.createAuthorityList("Admin"));
    }


}
