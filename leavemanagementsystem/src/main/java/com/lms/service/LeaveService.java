package com.lms.service;

import com.lms.data.access.LeaveDao;
import com.lms.data.access.UserDao;
import com.lms.formentity.Chart;
import com.lms.formentity.Graph;
import com.lms.formentity.KeyValue;
import com.lms.entity.Leave;
import com.lms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
//
//    public List getLeaveListOfYear(int year,int userid){
//        List<KeyValue> graph=new ArrayList();
//        for (int i=1;i<=12;i++){
//            int leaveCount = leaveDao.getLeaveCount(userid,i,year);
//            graph.add(new KeyValue(i,leaveCount));
//            }
//        return graph;
//    }

    public List<Graph> getLeaveListOfYear(int year,int userid){
        List<Graph> graph=new ArrayList();
        for (int i=1;i<=12;i++){
            int firstHalfLeaves = leaveDao.getLeaveCount(userid,i,year,"first half");
            int secondHalfLeaves=leaveDao.getLeaveCount(userid,i,year,"second half");
            int fullDayLeaves=leaveDao.getLeaveCount(userid,i,year,"full day");
            graph.add(new Graph(i,firstHalfLeaves,secondHalfLeaves,fullDayLeaves));
        }
        return graph;
    }



    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    public List<Graph> getLeaveListOfYear(int year){
        List<Graph> graph=new ArrayList();
        for (int i=1;i<=12;i++){
            int firsthalf = leaveDao.getLeaveCount(i,year,"first half");
            int secondhalf=leaveDao.getLeaveCount(i,year,"second half");

            int fullday=leaveDao.getLeaveCount(i,year,"full day");
            graph.add(new Graph(i,firsthalf,secondhalf,fullday));

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

    public boolean isUserHasTodayLeave(String userName){
        return leaveDao.isUserHasTodayLeave(userName);
    }

    public boolean isUserHasLeave(String userName,String date){

        return leaveDao.isUserHasLeave(userName,date);
    }


    @Transactional
    public void applyAllLeaveOfToday(ArrayList<Leave> leaveList) throws SQLException {
        for (Leave leave : leaveList) {
            leaveDao.addNewLeave(leave);
        }
    }

}
