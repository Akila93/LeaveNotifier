package com.lms.controller;

import com.lms.common.Common;
import com.lms.formentity.Chart;
import com.lms.formentity.Graph;
import com.lms.formentity.KeyValue;
import com.lms.entity.Leave;
import com.lms.entity.User;
import com.lms.formentity.SearchForm;
import com.lms.service.LeaveService;
import com.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nuwantha on 11/10/16.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    LeaveService leaveService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/users/search")
    public String findUser(Model model, HttpServletRequest request,Principal principal){

        String name = request.getParameter("name");
        //////check user role and if he/she is not an admin redirect to home/////
        User user = null;
        if(userService.isUserHasAccount(name)){
            user = userService.getUserByName(name);
        }

        if(user==null || !userService.getUserByName(principal.getName()).getRole().contains("ROLE_ADMIN")){
            return "redirect: ../../../home";
        }

        //this should be in services///////////////////
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        try {
            year = Integer.parseInt(request.getParameter("year"));
        }catch (Exception e){
            System.out.println("well well:");
        }
        //////////////////////////////////////////////


        String redirect = "redirect:../users/"+user.getUserId()+"/"+year+"/graph";
        return redirect;
    }

    @RequestMapping("/users/graph/{year}/{month}")
    public String plotAllUsersGraphWithMonth(Model model,@PathVariable("month") String month,
                                             @PathVariable("year") int year, Principal principal){

        ArrayList<User> allUsers = (ArrayList<User>) userService.getAllUsers();
        List<Graph> leaveListOfYear = leaveService.getLeaveListOfYear(year);
        ArrayList<Chart> monthlyUserLeaveChart = leaveService.getMonthlyUserLeaveChart(year, Common.convertMonthToInt(month));
        Integer userId=null;
        if(principal!=null){
            userId=userService.getUserByName(principal.getName()).getUserId();
            model.addAttribute("userId",userId);
        }
        model.addAttribute("leaveMonth",month);
        model.addAttribute("leaveYear",year);
        model.addAttribute("leaveListOfYear",leaveListOfYear);
        model.addAttribute("monthlyUserLeaveChart",monthlyUserLeaveChart);
        model.addAttribute("allUsers",allUsers);
        return "companyanalyzis";
    }

    @RequestMapping("/users/graph/{year}")
    public String plotAllUsersGraph(Model model,@PathVariable("year") int year,Principal principal){
        //int year = 2016;
        model.addAttribute("leaveYear",year);
        Integer userId=null;
        if(principal!=null){
            userId=userService.getUserByName(principal.getName()).getUserId();
            model.addAttribute("userId",userId);
        }
        return "redirect:../../users/graph/"+year+"/January";
    }

//    @RequestMapping("/users/current-user/{year}/graph")
//    public String plotGraphForCurrentUser(Model model, @RequestParam(value="id",required = true) int id, @PathVariable("year") int year,Principal principal){
//        List leaveListOfYear = leaveService.getLeaveListOfYear(year, id);
//        User user = userService.getUserById(id);
//        List<Leave> leaveOfYear = leaveService.getLeaveOfYear(year, id);
//        Integer userId=null;
//        if(principal!=null){
//            userId=userService.getUserByName(principal.getName()).getUserId();
//            model.addAttribute("userId",userId);
//        }
//        model.addAttribute("leaveYear",year);
//        model.addAttribute("leaveListOfYear",leaveListOfYear);
//        model.addAttribute("userName",user.getUserName());
//        model.addAttribute("leavesOfYear",leaveOfYear);
//        return "graph";
//    }

    @RequestMapping("/users/{id}/{year}/graph")
    public String plotGraphs(Model model, @PathVariable("id") int id, @PathVariable("year") int year,Principal principal){
        User user = userService.getUserByName(principal.getName());
        if(!user.getRole().equals("ROLE_ADMIN") && id!=user.getUserId()){
            return "redirect: ../../../../../403";
        }
        List<Graph> leaveListOfYear = leaveService.getLeaveListOfYear(year, id);
        List<Leave> leaveOfYear = leaveService.getLeaveOfYear(year, id);
        Integer userId=null;
        if(principal!=null){
            userId=userService.getUserByName(principal.getName()).getUserId();
            model.addAttribute("userId",userId);
        }
        model.addAttribute("userRole",userService.getUserByName(principal.getName()).getRole());
        User leaveUser = userService.getUserById(id);
        model.addAttribute("leaveYear",year);
        model.addAttribute("leaveListOfYear",leaveListOfYear);
        model.addAttribute("userName",user.getUserName());
        model.addAttribute("leaveUserName",leaveUser.getUserName());
        model.addAttribute("leavesOfYear",leaveOfYear);
        return "graph";
    }


}
