package com.lms.controller;

import com.lms.entity.Leave;
import com.lms.formentity.HomeForm;
import com.lms.formentity.LoginForm;
import com.lms.formentity.SearchForm;
import com.lms.service.LeaveService;
import com.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.lms.entity.Leave;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by akila on 11/15/16.
 */
@Controller
public class HomeController {

    @Autowired
    UserService userService;
    @Autowired
    LeaveService leaveService;



    @RequestMapping("/home")
    public String getHomePage(Model model, Principal principal){
        if(!userService.getUserByName(principal.getName()).getRole().contains("ROLE_ADMIN")){
            String year="2016";
            String redirectUrl="redirect: ../../users/"+userService.getUserByName(principal.getName()).getUserId()+"/"+year+"/graph";
            return redirectUrl;
        }
        Integer userId=null;
        //if(principal!=null){
            userId=userService.getUserByName(principal.getName()).getUserId();
            model.addAttribute("userId",userId);
        //}
        ///change this to service layer
        List<Leave> leaveList = new ArrayList<Leave>();
        //if(userService.getUserByName(principal.getName()).getRole()=="ROLE_ADMIN"){
            leaveList = leaveService.getAllLeaves();
        //}
        ///////////////
        model.addAttribute("userRole",userService.getUserByName(principal.getName()).getRole());
        model.addAttribute("searchForm",new SearchForm());
        HomeForm homeForm=new HomeForm();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
        homeForm.setDate(format);
        model.addAttribute("userCount",userService.getUserCount());
        model.addAttribute("homeForm",homeForm);
        model.addAttribute("leaveList",leaveList);
        model.addAttribute("userEmail",userService.getUserByName(principal.getName()).getEmail());
        return "home";

    }


    @RequestMapping(value = "/home", method =  RequestMethod.POST)
    public String getHomePage(Model model, HttpServletRequest request, Principal principal){
        HomeForm homeForm=null;
        Integer userId=null;
        if(principal!=null){
             userId=userService.getUserByName(principal.getName()).getUserId();
            model.addAttribute("userId",userId);
        }
        if(homeForm==null){
            homeForm = new HomeForm();
            homeForm.setDate(request.getParameter("date"));
        }
        System.out.println(homeForm.getDate());
        HomeForm form=new HomeForm();
        form.setDate(homeForm.getDate());
        //System.out.println("role:"+principal.getName());
        model.addAttribute("userRole",userService.getUserByName(principal.getName()).getRole());
        model.addAttribute("searchForm",new SearchForm());
        model.addAttribute("userCount",userService.getUserCount());
        model.addAttribute("homeForm",form);
        model.addAttribute("leaveList",leaveService.getLeavesOfDay(homeForm.getDate()));//allow admin only
        model.addAttribute("userEmail",userService.getUserByName(principal.getName()).getEmail());
        return "home";
    }

//    @RequestMapping(value = "/home/login/check", method =  RequestMethod.POST)
//    public String getLoginHomePage(Model model,LoginForm loginForm){
//
//
//        HomeForm form=new HomeForm();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//        int year = cal.get(Calendar.YEAR);
//        int month = cal.get(Calendar.MONTH)+1;
//        int day = cal.get(Calendar.DATE);
//        String dateInString = year+"-"+month+"-"+day;
//        form.setDate(dateInString);
//        model.addAttribute("homeForm",form);
//        model.addAttribute("leaveList",leaveService.getLeavesOfDay(dateInString));
//        return "redirect:../../home";
//
//    }

}
