package com.lms.data.access;

import com.lms.entity.LoginUser;
import com.lms.mapper.LoginUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by nuwantha on 11/23/16.
 */
@Repository
public class LoginDaoImp extends JdbcDaoSupport implements LoginDao {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }



    @Override
    public LoginUser getLoginUserByEmail(String email) {
        String sql = "select * from loginuser where email=?";
        LoginUser loginUser = (LoginUser) getJdbcTemplate().queryForObject(sql, new Object[]{
                email}, new LoginUserMapper());
        return loginUser;
    }

    @Override
    public LoginUser getLoginUserByUserName(String userName) {
        String sql = "select * from loginuser where username=?";
        LoginUser loginUser = (LoginUser) getJdbcTemplate().queryForObject(sql, new Object[]{
                userName}, new LoginUserMapper());
        return loginUser;
    }

    @Override
    public void createUserAccount(LoginUser loginUser) {

        String sql = "INSERT INTO loginuser"+"(username,email) VALUES (?, ?)" ;
        int update = getJdbcTemplate().update(sql, new Object[]{
                loginUser.getUsername(),loginUser.getEmail()
        });


    }
}
