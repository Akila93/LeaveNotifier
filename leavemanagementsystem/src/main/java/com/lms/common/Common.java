package com.lms.common;

import com.lms.entity.Leave;
import com.lms.entity.User;
import com.lms.formentity.BulkLeaveForm;
import com.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.transform.sax.SAXSource;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nuwantha on 11/18/16.
 */
@Component
public class Common {

    @Autowired
    UserService userService;

    public static int convertMonthToInt(String month){
        switch (month){
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;



        }
        return 0;
    }

    public static String getMonthName(int monthNumber) {
        String[] months = new DateFormatSymbols().getMonths();
        int n = monthNumber-1;
        return (n >= 0 && n <= 11) ? months[n] : "wrong number";
    }



    public ArrayList<Leave> createLeaveList(BulkLeaveForm bulkLeaveForm){

        ArrayList<Leave> todayLeaveList = new ArrayList<>();
        todayLeaveList.addAll(createLeaveOfGivenLeaveType(bulkLeaveForm.getUsernamesOfFulldayLeave(),"full day"));
        todayLeaveList.addAll(createLeaveOfGivenLeaveType(bulkLeaveForm.getUsernamesOfFirstHalfLeave(),"first half"));
        todayLeaveList.addAll(createLeaveOfGivenLeaveType(bulkLeaveForm.getUsernamesOfSecondHalfLeave(),"second half"));
        return todayLeaveList;
    }


    public ArrayList<Leave> createLeaveOfGivenLeaveType(List<String> names,String leaveType){

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String dateString = dateFormat.format(date);
            ArrayList<Leave> todayLeaveListOfGivenLeaveType = new ArrayList<Leave>();
            for (String name :names){
                name=name.substring(1,name.length()-1);

                Leave leave = new Leave();
                User userByName = userService.getUserByName(name);
                leave.setName(name);
                leave.setUserId(userByName.getUserId());
                leave.setLeaveType(leaveType);
                leave.setLeaveDate(dateString);
                todayLeaveListOfGivenLeaveType.add(leave);
            }

            return todayLeaveListOfGivenLeaveType;

    }

    public static String getCurrentDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }
}
