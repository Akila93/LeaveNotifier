package com.lms.controller;

import com.lms.entity.Leave;
import com.lms.entity.User;
import com.lms.service.LeaveService;
import com.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Text;
import org.springframework.ui.Model;

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
        //userService.addNewUser(new User(012,"nimala"));
        modal.addAttribute("users",userService.getAllUsers());
        List<User> allUsers = userService.getAllUsers();

        return "user";
    }


    @RequestMapping("/")
    public String getRedirectToHome(){
        return "redirect:home";
    }

    @RequestMapping("/home")
    public String getHomePage(){
        return "home";
    }

    @RequestMapping(value = "leave/update", method = RequestMethod.POST)
    public String getHomePage(Leave leave){
        //leave add
        leaveService.applyALeave(leave);
        return "redirect:home";
    }

}
