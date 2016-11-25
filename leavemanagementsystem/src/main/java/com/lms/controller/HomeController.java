package com.lms.controller;

import com.lms.entity.HomeForm;
import com.lms.entity.LoginForm;
import com.lms.service.LeaveService;
import com.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by akila on 11/15/16.
 */
@Controller
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    LeaveService leaveService;

//    @RequestMapping("/hgh")
//    public String getRedirectToHome(){
//        return "redirect:home";
//    }

    @RequestMapping("/home")
    public String getHomePage(Model model){
        HomeForm homeForm=new HomeForm();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        homeForm.setDate(format);
        model.addAttribute("userCount",userService.getUserCount());
        model.addAttribute("homeForm",homeForm);
        model.addAttribute("leaveList",leaveService.getAllLeaves());
        return "home";
    }
    @RequestMapping(value = "/home", method =  RequestMethod.POST)
    public String getHomePage(Model model,HomeForm homeForm){
        if(homeForm==null){
            homeForm = new HomeForm();
        }
        System.out.println(homeForm.getDate());
        HomeForm form=new HomeForm();
        form.setDate(homeForm.getDate());
        model.addAttribute("userCount",userService.getUserCount());
        model.addAttribute("homeForm",form);
        model.addAttribute("leaveList",leaveService.getLeavesOfDay(homeForm.getDate()));
        return "home";
    }

    @RequestMapping(value = "/home/login/check", method =  RequestMethod.POST)
    public String getLoginHomePage(Model model,LoginForm loginForm){


        ////////check login valid or not


        HomeForm form=new HomeForm();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DATE);
        String dateInString = year+"-"+month+"-"+day;
        form.setDate(dateInString);
        model.addAttribute("homeForm",form);
        model.addAttribute("leaveList",leaveService.getLeavesOfDay(dateInString));
        return "redirect:../../home";
    }


}
