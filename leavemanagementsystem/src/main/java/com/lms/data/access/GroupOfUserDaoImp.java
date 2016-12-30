package com.lms.data.access;

import com.lms.entity.GroupOfUser;
import com.lms.entity.User;
import com.lms.mapper.GroupOfUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by nuwantha on 12/28/16.
 */
@Repository
public class GroupOfUserDaoImp extends JdbcDaoSupport implements GroupOfUserDao {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public void addUserOfGroup(User user, int groupId) {
        String sql="insert into userofgroup(userid,groupid) values (?,?)";
        getJdbcTemplate().update(sql,user.getUserId(),groupId);
    }

    @Override
    public List<GroupOfUser> getUsersOfGroup(int groupId) {
        String sql="select * from userofgroup where groupid=?";
        List<GroupOfUser> groupOfUserList = getJdbcTemplate().query(sql, new Object[]{groupId}, new GroupOfUserMapper());
        return groupOfUserList;
    }

    @Override
    public boolean deleteUsersOfGroup(int groupid) {
        return false;
    }
}
