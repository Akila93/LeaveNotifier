package com.lms.service;

import com.lms.data.access.LeaveDao;
import com.lms.data.access.UserDao;
import com.lms.entity.Chart;
import com.lms.entity.KeyValue;
import com.lms.entity.Leave;
import com.lms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nuwantha on 11/10/16.
 */
@Service
public class LeaveService {

    @Autowired
    LeaveDao leaveDao;
    @Autowired
    UserDao userDao;

    public void applyALeave(Leave leave) throws SQLException{
        User user = userDao.getUserByName(leave.getName());
        leave.setUserId(user.getUserId());
        leaveDao.addNewLeave(leave);
    }

    public List<Leave> getAllLeaves(){
        List<Leave> allLeaves = leaveDao.getTodayLeaves();
        List<Leave> modifyLeaves = new ArrayList<Leave>();

        for (Leave leave:allLeaves){
            User user = userDao.getUserById(leave.getUserId());
            leave.setName(user.getUserName());
            modifyLeaves.add(leave);
        }
        return modifyLeaves;
    }

    public List getLeaveListOfYear(int year,int userid){
        List<KeyValue> graph=new ArrayList();
        for (int i=1;i<=12;i++){
            int leaveCount = leaveDao.getLeaveCount(userid,i,year);
            graph.add(new KeyValue(i,leaveCount));
            }
        return graph;
    }

    public List<Leave> getLeavesOfDay(String date){
        List<Leave> allLeaves = leaveDao.getLeavesOfDay(date);
        List<Leave> modifyLeaves = new ArrayList<Leave>();

        for (Leave leave:allLeaves){
            User user = userDao.getUserById(leave.getUserId());
            leave.setName(user.getUserName());
            modifyLeaves.add(leave);
        }
        return modifyLeaves;
    }

    public List<Leave> getLeaveOfYear(int userid,int year){
        List<Leave> leavesOfYear = leaveDao.getLeavesOfYear(userid, year);
        return leavesOfYear;
    }

    public List getLeaveListOfYear(int year){
        List<KeyValue> graph=new ArrayList();
        for (int i=1;i<=12;i++){
            int leaveCount = leaveDao.getLeaveCount(i,year);
            graph.add(new KeyValue(i,leaveCount));
        }
        return graph;
    }

    public ArrayList<Chart> getMonthlyUserLeaveChart(int year,int month){
        ArrayList<Chart> chart=new ArrayList<>();
        for (int i=1;i<=31;i++){
            List<User> allUsers = userDao.getAllUsers();
            int userCount = userDao.getUserCount();
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int j=0;j<userCount;j++){
                User user = allUsers.get(j);
                int userLeave = leaveDao.isUserLeave(user.getUserId(), month, year, i);
                arr.add(userLeave);
            }
            Chart chart1 = new Chart(i, arr);
            chart.add(chart1);
        }
        return chart;
    }
}
