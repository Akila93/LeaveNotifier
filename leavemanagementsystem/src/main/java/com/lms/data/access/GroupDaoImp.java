package com.lms.data.access;

import com.lms.entity.Group;
import com.lms.mapper.GroupMapper;
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
public class GroupDaoImp extends JdbcDaoSupport implements GroupDao {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }



    @Override
    public void createGroup(Group group) {
        String sql="insert into groups(groupname,grouplederid) values (?,?)";
        getJdbcTemplate().update(sql,group.getGroupName(),group.getGroupLederId());

    }

    @Override
    public List<Group> getAllGroup() {
        String sql="select * from groups";
        List<Group> groupList = getJdbcTemplate().query(sql, new GroupMapper());
        return groupList;

    }

    @Override
    public List<Group> getGroup(int groupLeaderId) {
        String sql="select * from groups where grouplederid=?";
        List<Group> groupList = getJdbcTemplate().query(sql,new Object[]{groupLeaderId}, new GroupMapper());
        return groupList;
    }

    @Override
    public Group getGroup(String groupName) {
        String sql="select * from groups where groupname=?";
        Group group =(Group) getJdbcTemplate().queryForObject(sql, new Object[]{groupName}, new GroupMapper());
        return group;

    }

    @Override
    public boolean deleteGroup(String groupId) {
        String sql="delete from  groups where groupid=?";
        int update = getJdbcTemplate().update(sql, groupId);
        if(update>0) {
            return true;
        }else{
            return false;
        }
    }
}
