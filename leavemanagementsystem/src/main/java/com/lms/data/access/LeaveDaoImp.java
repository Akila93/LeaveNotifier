package com.lms.data.access;
import com.lms.entity.Leave;
import com.lms.mapper.LeaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by nuwantha on 11/11/16.
 */
@Repository
public class LeaveDaoImp extends JdbcDaoSupport implements LeaveDao{

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }

    @Override
    public void addNewLeave(Leave leave) {
        String sql = "INSERT INTO leave " +
                "(userid,leavedate,leavetype) VALUES (?, ?)" ;

        getJdbcTemplate().update(sql, new Object[]{
                leave.getUserId(), leave.getLeaveDate(),leave.getLeaveType()
        });
    }

    @Override
    public List<Leave> getAllLeaves() {
        String sql="select * from leave";
        List<Leave> leaveList = getJdbcTemplate().query(sql, new LeaveMapper());
        return  leaveList;
    }

    @Override
    public List<Leave> getLeavesOfDay(String date) {
        String sql="select * from leave where leavedate=?";
        List<Leave> leaveList = getJdbcTemplate().query(sql, new Object[]{date}, new LeaveMapper());
        return leaveList;
    }


}
