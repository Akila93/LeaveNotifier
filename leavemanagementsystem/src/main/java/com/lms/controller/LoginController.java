package com.lms.controller;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.lms.entity.LoginUser;
import com.lms.service.LoginService;
import com.lms.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;


/**
 * Created by akila on 11/15/16.
 */
@Controller
public class LoginController{
    @Autowired
   LoginService loginService;

    @Autowired
    SecurityService securityService;
///    String CLIENT_ID;
    String CLIENT_ID="862712159345-ti9la1n9c7vtj95516st4q3nf4kt68rc";
//    @RequestMapping("/")
//    public String getLogin(Model modal, Principal principal){
//        modal.addAttribute("login",new LoginForm());
//        System.out.println(principal.getName());
//        return "login";
//    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new LoginUser());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm")LoginUser userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        loginService.createUserAccount(userForm);


        securityService.autologin(userForm.getUsername(),userForm.getEmail());

        return "redirect:/home";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = "/google-login")
    public void loginWithGoogle(HttpServletRequest request,HttpServletResponse response){
        System.out.println("request:"+request.getParameter("idtoken"));
        GoogleIdTokenVerifier verifier=null;
        String idTokenString=null;
        GoogleIdToken idToken=null;
        try {
            // (Receive idTokenString by HTTPS POST)
            idTokenString=request.getParameter("idtoken");

            HttpTransport httpTransport = new UrlFetchTransport();
            //get token
            verifier = new GoogleIdTokenVerifier.Builder(httpTransport , new JacksonFactory())
                    .setAudience(Arrays.asList(this.CLIENT_ID))
                    .setIssuer("https://accounts.google.com")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }

//        if(idTokenString!=null){
//            model.addAttribute("username","nuwantha");
//            model.addAttribute("password","nuwantha");
//            return "redirect ../login";
//        }


        try {
            if(idTokenString==null) {
                System.out.println("no token mate");
                return;
            }
            if(verifier==null)
                System.out.println("no verifier!");
            if(verifier!=null && idTokenString!=null){

                //idToken = verifier.verify(idTokenString);
                idToken = GoogleIdToken.parse(new JacksonFactory(),idTokenString);
            }


        if (idToken != null || verifier.verify(idToken)) {
            System.out.println("yoooo valid token");
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            System.out.println(email);

            LoginUser loginUserByEmail = loginService.getLoginUserByEmail(email);
//            System.out.println(loginUserByEmail.getUsername()+" "+loginUserByEmail.getPassword());
            String formUsername=loginUserByEmail.getUsername();//load from db
              //repost user name and password
            securityService.autologin(formUsername,email);
//            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
            response.setHeader("Location", "http://localhost:9099/home");
            //response.sendRedirect("http://localhost:9099/home");
            return;
        } else {
            System.out.println("couldn't verify! :"+idTokenString+"\nverifier:"+verifier.verify(idToken));

        }
        } catch (Exception e) {
            System.out.println("exception verifing fails!");
            e.printStackTrace();
        }
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

}
