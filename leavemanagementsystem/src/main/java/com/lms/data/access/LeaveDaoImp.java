package com.lms.data.access;
import com.lms.entity.Leave;
import com.lms.mapper.LeaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sun.security.krb5.internal.PAData;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by nuwantha on 11/11/16.
 */
@Repository
public class LeaveDaoImp extends JdbcDaoSupport implements LeaveDao{

    @Autowired
    DataSource dataSource;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
        this.namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);

        return dataSourceTransactionManager;
    }


    @Override
    public void addNewLeave(Leave leave) throws SQLException
    {

        String sql = "INSERT INTO leave " +
                "(userid,leavedate,leavetype,reason,comment) VALUES (?, ?,?,?,?)";

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date=null;
        try {
            date= format.parse(leave.getLeaveDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        getJdbcTemplate().update(sql, new Object[]{
                leave.getUserId(), date,leave.getLeaveType(),leave.getReasonToLeave(),leave.getComment()
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
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dateD=null;
        try {
            dateD= format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String sql="select * from leave where leavedate=?";
        List<Leave> leaveList = getJdbcTemplate().query(sql, new Object[]{dateD}, new LeaveMapper());
        return leaveList;
    }

    @Override
    public List<Leave> getTodayLeaves(){
        String sql="select * from leave where leavedate=(select CURRENT_DATE )";
        List<Leave> leaveList = getJdbcTemplate().query(sql, new LeaveMapper());
        return leaveList;
    }

    @Override
    public int getLeaveCount(int userId, int month,int year) {
        String sql="select count(*) from leave where extract( year from leavedate)=:year and extract( month from leavedate)=:month and userid=:userid";


        SqlParameterSource sqlParameterSource=new MapSqlParameterSource("year",year).addValue("month",month).addValue("userid",userId);
        int integer = namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, Integer.class);
        return integer;
    }

    @Override
    public List<Leave> getLeavesOfYear(int userid,int year) {
        String sql="select * from leave where extract( year from leavedate)=? and userid=?";
        List<Leave> leaveList = getJdbcTemplate().query(sql, new Object[]{ userid,year}, new LeaveMapper());
        return leaveList;

    }

    @Override
    public int getLeaveCount(int month,int year,String leaveType) {

        String sql="select count(*) from leave where extract( month from leavedate)=? and extract( year from leavedate)=? and leavetype=?";
        int monthlyLeaveCount = getJdbcTemplate().queryForObject(sql, new Object[]{month,year,leaveType}, Integer.class);
        return monthlyLeaveCount;

    }

    @Override
    public int isUserLeave(int userId, int month, int year, int day) {

        String sql="select count(*) from leave where extract( month from leavedate)=? and extract( year from leavedate)=? and extract( day from leavedate)=? and userid=? ";
        int monthlyLeaveCount = getJdbcTemplate().queryForObject(sql, new Object[]{month,year,day,userId}, Integer.class);
        return monthlyLeaveCount;
    }

    @Override
    public boolean isUserHasTodayLeave(String userName){
        String sql="select count(*) from leave natural join userh where leavedate=(select CURRENT_DATE ) and name=?";
        int count = getJdbcTemplate().queryForObject(sql, Integer.class,userName);
        if(count==0) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean isUserHasLeave(String userName,String date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dateD=null;
        try {
            dateD= format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String sql="select count(*) from leave natural join userh where leavedate=? and name=?";
        int count = getJdbcTemplate().queryForObject(sql, Integer.class,dateD,userName);
        if(count==0) {
            return false;
        }else {
            return true;
        }


    }

    @Override
    public int getLeaveCount(int userId, int month, int year, String leaveType) {

        String sql="select count(*) from leave where extract( month from leavedate)=? and extract( year from leavedate)=? and userid=? and leaveType=?";
        int monthlyLeaveCount = getJdbcTemplate().queryForObject(sql, new Object[]{month,year,userId,leaveType}, Integer.class);
        return monthlyLeaveCount;
    }


}
