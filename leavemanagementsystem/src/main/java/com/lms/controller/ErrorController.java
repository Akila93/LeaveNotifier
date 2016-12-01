package com.lms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by nuwantha on 11/29/16.
 */
@Controller
public class ErrorController {
    @RequestMapping("/403")
    public String permissionDenid(){
        return "403";
    }
    @RequestMapping("/errorcustom/sqlexception")
    public String sqlException(Model model) {
        model.addAttribute("errorName","SQLException");
        model.addAttribute("errorCode", HttpStatus.INTERNAL_SERVER_ERROR);
        model.addAttribute("errorDetail","please use correct data");
        System.out.println("nuwantha");
        return "errorpage";
    }

    @RequestMapping("/errorcustom/emptyResultDataAccessException")
    public String emptyResultDataAccessException(Model model) {

        model.addAttribute("errorName","EmptyResultDataAccessException");
        model.addAttribute("errorCode", HttpStatus.NOT_FOUND);
        model.addAttribute("errorDetail","you don't have an account. create a an account and then log in to the system");

        return "errorpage";
    }


}
