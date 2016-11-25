package com.lms.data.access;
import com.lms.entity.User;
import com.lms.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nuwantha on 11/10/16.
 */
@Repository
public class UserDaoImp extends JdbcDaoSupport implements UserDao{

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public void addNewUser(User user) {
        String sql = "INSERT INTO userh " +
                "(userid, name) VALUES (?, ?)" ;
        getJdbcTemplate().update(sql, new Object[]{
                user.getUserId(), user.getUserName()
        });



    }

    @Override
    public List<User> getAllUsers() {
        String sql="select * from userh";
        List<User> userList= getJdbcTemplate().query(sql, new UserMapper());
        return  userList;
    }

    @Override
    public User getUserByName(String name) {
        String sql="select * from userh where name= ?";
        User user= (User) getJdbcTemplate().queryForObject(sql, new Object[]{name}, new UserMapper());
        return user;
    }

    @Override
    public User getUserById(int id) {
        String sql="select * from userh where userid= ?";
        User user= (User) getJdbcTemplate().queryForObject(sql, new Object[]{id}, new UserMapper());
        return user;
    }

    @Override
    public int getUserCount() {
        String sql="select count(*) from userh";
        int epmloyeeCount = getJdbcTemplate().queryForObject(sql, Integer.class);
        return  epmloyeeCount;
    }

}
