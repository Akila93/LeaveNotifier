package com.lms.controller;

import com.lms.entity.Leave;
import com.lms.entity.User;
import com.lms.service.LeaveService;
import com.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import java.security.Principal;
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
    public String addCustomerLeave(Model modal, Principal principal){

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
        Integer userId=null;
        if(principal!=null){
            userId=userService.getUserByName(principal.getName()).getUserId();
            modal.addAttribute("userId",userId);
        }
        listOfLeaveTypes.add("second half");
        listOfLeaveTypes.add("first half");
        listOfLeaveTypes.add("full day");
        modal.addAttribute("listOfReasonsToLeave",listOfReasonsToLeave);
        modal.addAttribute("leave",leave);
        modal.addAttribute("leaveDates",listOfLeaveDates);
        modal.addAttribute("leaveTypes",listOfLeaveTypes);
        User currentUser = userService.getUserByName(principal.getName());
        if(currentUser.getRole().equals("ROLE_ADMIN")) {
            modal.addAttribute("users", userService.getAllUsers());
        }else{

            ArrayList<User> users = new ArrayList<>();
            users.add(currentUser);
            modal.addAttribute("users",users);

        }
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
