package com.lms.controller;

import com.lms.common.Common;
import com.lms.entity.Chart;
import com.lms.entity.KeyValue;
import com.lms.entity.Leave;
import com.lms.entity.User;
import com.lms.service.LeaveService;
import com.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
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

    @RequestMapping("/users/graph/{year}/{month}")
    public String plotAllUsersGraphWithMonth(Model model,@PathVariable("month") String month,
                                             @PathVariable("year") int year){
//        List leaveListOfYear = leaveService.getLeaveListOfYear(year, 3);
//        User user = userService.getUser(3);
//        List<Leave> leaveOfYear = leaveService.getLeaveOfYear(year, 3);

        ArrayList<User> allUsers = (ArrayList<User>) userService.getAllUsers();
        List<KeyValue> leaveListOfYear = leaveService.getLeaveListOfYear(year);
        ArrayList<Chart> monthlyUserLeaveChart = leaveService.getMonthlyUserLeaveChart(year, Common.convertMonthToInt(month));
        model.addAttribute("leaveMonth",month);
        model.addAttribute("leaveYear",year);
        model.addAttribute("leaveListOfYear",leaveListOfYear);
        model.addAttribute("monthlyUserLeaveChart",monthlyUserLeaveChart);
        model.addAttribute("allUsers",allUsers);
        return "companyanalyzis";
    }

    @RequestMapping("/users/graph/{year}")
    public String plotAllUsersGraph(Model model,@PathVariable("year") int year){
        //int year = 2016;
        return "redirect:../../users/graph/"+year+"/January";
    }

    @RequestMapping("/users/{id}/{year}/graph")
    public String plotGraphs(Model model, @PathVariable("id") int id, @PathVariable("year") int year){
        List leaveListOfYear = leaveService.getLeaveListOfYear(year, id);
        User user = userService.getUser(id);
        List<Leave> leaveOfYear = leaveService.getLeaveOfYear(year, id);

        model.addAttribute("leaveYear",year);
        model.addAttribute("leaveListOfYear",leaveListOfYear);
        model.addAttribute("userName",user.getUserName());
        model.addAttribute("leavesOfYear",leaveOfYear);
        return "graph";
    }


}
