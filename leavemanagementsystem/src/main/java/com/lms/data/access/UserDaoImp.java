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
    public void createUserAccount(User user) {
        String sql = "INSERT INTO userh " +
                "(name,email,role) VALUES (?,?,?)" ;
        getJdbcTemplate().update(sql,user.getUserName(),user.getEmail(),String.valueOf(user.getRole())
        );



    }

    @Override
    public List<User> getAllUsers() {
        String sql="select * from userh";
        return getJdbcTemplate().query(sql, new UserMapper());

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
    public User getUserByEmail(String email) {
        String sql="select * from userh where email= ?";
        User user= (User) getJdbcTemplate().queryForObject(sql, new Object[]{email}, new UserMapper());
        return user;
    }

    @Override
    public int getUserCount() {
        String sql="select count(*) from userh";
        int epmloyeeCount = getJdbcTemplate().queryForObject(sql, Integer.class);
        return  epmloyeeCount;
    }

    @Override
    public boolean isUserHasAccount(String userName) {
        String sql="select count( *) from userh where name=?";
        int count = getJdbcTemplate().queryForObject(sql, Integer.class,userName);
        if(count==0) {
            return false;
        }else{
            return true;
        }
    }

}
