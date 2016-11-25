package com.lms.mapper;

import com.lms.entity.LoginUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nuwantha on 11/23/16.
 */
public class LoginUserMapper implements RowMapper{


    @Override
    public LoginUser mapRow(ResultSet resultSet, int i) throws SQLException {
        LoginUser loginUser = new LoginUser();
        //System.out.println("mapper");
        System.out.println(i);
        if(i==0) {
            loginUser.setUsername(resultSet.getString("username"));
            loginUser.setEmail(resultSet.getString("email"));
        }
        return loginUser;
        }
}
