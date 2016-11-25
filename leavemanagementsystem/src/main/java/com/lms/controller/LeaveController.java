package com.lms.controller;

import com.lms.entity.KeyValue;
import com.lms.entity.Leave;
import com.lms.entity.LeaveForm;
import com.lms.entity.User;
import com.lms.service.LeaveService;
import com.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Text;
import org.springframework.ui.Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nuwantha on 11/10/16.
 */

@Controller
public class LeaveController {

    @Autowired
    UserService userService;
    @Autowired
    LeaveService leaveService;

    @RequestMapping("/leave")
    public String addCustomerLeave(Model modal){

        Leave leave = new Leave();
        List<String> listOfLeaveTypes=new ArrayList<String>();
        List<Integer> listOfLeaveDates=new ArrayList<Integer>();
        List<String> listOfReasonsToLeave=new ArrayList<String>();

        listOfReasonsToLeave.add("Sick");
        listOfReasonsToLeave.add("Personal");
        listOfReasonsToLeave.add("Wedding");
        listOfReasonsToLeave.add("Exam");

        for(int i=1;i<=31;i++){
            listOfLeaveDates.add(i);
        }

        listOfLeaveTypes.add("second half");
        listOfLeaveTypes.add("first half");
        listOfLeaveTypes.add("full day");
        modal.addAttribute("listOfReasonsToLeave",listOfReasonsToLeave);
        modal.addAttribute("leave",leave);
        modal.addAttribute("leaveDates",listOfLeaveDates);
        modal.addAttribute("leaveTypes",listOfLeaveTypes);
        modal.addAttribute("users",userService.getAllUsers());
        return "leave";
    }


    @RequestMapping(value = "/leave/update", method = RequestMethod.POST)
    public String getHomePage(Leave leave) throws SQLException {
         try {
            leaveService.applyALeave(leave);
        }catch (SQLException e){
            throw  new SQLException("new Sql Exception");
        }
        return "redirect:../home";
    }


}
