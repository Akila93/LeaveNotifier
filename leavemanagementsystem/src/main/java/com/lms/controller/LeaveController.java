package com.lms.controller;

import com.lms.common.Common;
import com.lms.service.ReportService;
import com.lms.entity.Leave;
import com.lms.entity.User;
import com.lms.formentity.BulkLeaveForm;
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

    @Autowired
    ReportService reportService;

    @Autowired
    Common common;

    @RequestMapping("/bulk-leave")
    public String addBulkLeave(Model model,Principal principal){
        List<User> allUsers = userService.getUserHasNotLeaveToday();

        model.addAttribute("bulkForm", new BulkLeaveForm());
        model.addAttribute("users", allUsers);
        model.addAttribute("userId", userService.getUserByName(principal.getName()).getUserId());
        model.addAttribute("userRole", userService.getUserByName(principal.getName()).getRole());
        model.addAttribute("userEmail",userService.getUserByName(principal.getName()).getEmail());
        return "bulkleave";
    }
    @RequestMapping(value = "/bulk-leave",method = RequestMethod.POST)
    public String addBulkLeave(BulkLeaveForm form, Model model, Principal principal) throws SQLException {

        ArrayList<Leave> leaveList = common.createLeaveList(form);
        leaveService.applyAllLeaveOfToday(leaveList);
        for (Leave leave:leaveList){
            System.out.println(leave.getName()+"  "+leave.getLeaveDate()+" "+leave.getLeaveType());
        }

        System.out.println("full:"+form.getUsernamesOfFulldayLeave()+",first:"+form.getUsernamesOfFirstHalfLeave()+",second:"+form.getUsernamesOfSecondHalfLeave());
        User user = userService.getUserByName(principal.getName());

        ////////////////////////////////////////////////////////////////
        ///add leaves for bulk form
        ///////////////////////////////////////////////////////////
        List<User> allUsers = userService.getUserHasNotLeaveToday();
        model.addAttribute("users", allUsers);
        model.addAttribute("bulkForm", new BulkLeaveForm());
        model.addAttribute("userId", userService.getUserByName(principal.getName()).getUserId());
        model.addAttribute("userRole", userService.getUserByName(principal.getName()).getRole());
        model.addAttribute("userEmail",userService.getUserByName(principal.getName()).getEmail());
        return "bulkleave";
    }

//    @RequestMapping(
//            value = "/bulk-leave",
//            method = RequestMethod.POST
//    )
//    public void addLeaves(@RequestBody ArrayList<Leave> leaveList) throws SQLException{
//                leaveService.applyAllLeaveOfToday(leaveList);
//
//    }

    @RequestMapping("/leave")
    public String addCustomerLeave(Model modal, Principal principal){

        modal=this.addModelValues(modal,principal,null);
        ////////////////////////////////////////////////////////////////////////////////////////
        modal.addAttribute("userEmail",userService.getUserByName(principal.getName()).getEmail());
        return "leave";
    }

    private Model addModelValues(Model modal,Principal principal,String errorName){
        Leave leave = new Leave();

        Integer userId=null;
        User currentUser = userService.getUserByName(principal.getName());

        List<String> listOfLeaveTypes=new ArrayList<String>();
        List<Integer> listOfLeaveDates=new ArrayList<Integer>();
        List<String> listOfReasonsToLeave=new ArrayList<String>();
        List<User> allUsers = userService.getAllUsers();
        if(principal!=null){
            userId=userService.getUserByName(principal.getName()).getUserId();
        }
        //////////////////////////////////////////////////////////////////////////////////////


        ////////////////ADDING DATA TO EMPTY OBJECTS IN THE MODEL///////////////////////////////

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
        ////////////////////////////////////////////////////////////////////////////////////////


        //////////////////////////ADDING OBJECT S TO MODELS//////////////////////////////////////
        if(principal!=null){
            modal.addAttribute("userId",userId);
        }
        if(errorName!=null){
            modal.addAttribute("errorName",errorName);
        }
        modal.addAttribute("userRole",userService.getUserByName(principal.getName()).getRole());
        modal.addAttribute("listOfReasonsToLeave",listOfReasonsToLeave);
        modal.addAttribute("leave",leave);
        modal.addAttribute("leaveDates",listOfLeaveDates);
        modal.addAttribute("leaveTypes",listOfLeaveTypes);
        modal.addAttribute("currentUser",principal.getName());
        if(currentUser.getRole().equals("ROLE_ADMIN")) {
            modal.addAttribute("users", allUsers);
        }else{
            allUsers = new ArrayList<>();
            allUsers.add(currentUser);
            modal.addAttribute("users",allUsers);
        }
        return modal;
    }
    @RequestMapping("/leave-reporting")
    public String generateAndSendReport(Model model,Principal principal){

        System.out.println("mail started to send");
        boolean send = reportService.send();
        if(send){
            model.addAttribute("emailNotification","success");
        }
        System.out.println("mail send");
        return "redirect: ../../home";
    }

    @RequestMapping(value = "/leave", method = RequestMethod.POST)
    public String getHomePage(Leave leave,Principal principal,Model model) throws SQLException {
        //System.out.println(principal.getName()+":"+leave.getName());
        if(leave.getName()==""||leave.getComment()==""
                || leave.getLeaveDate()=="" || leave.getReasonToLeave()==""|| leave.getLeaveType()==""){
            model=this.addModelValues(model,principal,null);
            model.addAttribute("userEmail",userService.getUserByName(principal.getName()).getEmail());
            return "leave";
        }
        User user = userService.getUserByName(principal.getName());
        System.out.println("leave name"+leave.getLeaveDate()+" "+leave.getName());
        if(!user.getRole().equals("ROLE_ADMIN")){
            leave.setName(principal.getName());
        }

        if((!user.getRole().equals("ROLE_ADMIN")) && (!leave.getName().equals(principal.getName()))){
            String errorName="you can't add leaves for others";
            model=this.addModelValues(model,principal,errorName);
            model.addAttribute("userEmail",userService.getUserByName(principal.getName()).getEmail());
            return "leave";
        }else if(leaveService.isUserHasLeave(leave.getName(),leave.getLeaveDate())){
            String errorName=leave.getName()+" has already added leave "+leave.getLeaveDate();
            model=this.addModelValues(model,principal,errorName);
            model.addAttribute("userEmail",userService.getUserByName(principal.getName()).getEmail());
            return  "leave";
        }

         try {
            leaveService.applyALeave(leave);
        }catch (SQLException e){
            throw  new SQLException("new Sql Exception");
        }
        return "redirect:/home";
    }


}
