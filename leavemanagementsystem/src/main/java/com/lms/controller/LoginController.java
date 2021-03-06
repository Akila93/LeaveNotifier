package com.lms.controller;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.lms.common.Common;
import com.lms.entity.User;
import com.lms.jasonWrapper.Wrapper;
import com.lms.service.DepartmentService;
import com.lms.service.SecurityService;
import com.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by akila on 11/15/16.
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @Autowired
    DepartmentService departmentService;

    ///////////////no modification//////////////////////////////////
    final String CLIENT_ID = "862712159345-ti9la1n9c7vtj95516st4q3nf4kt68rc";


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model,Principal principal) {
        //////////////////////////////OBJECTS///////////////////////////////
        Integer userId=null;
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users",allUsers);
        System.out.println( "email is"+allUsers.get(1).getEmail());

        //////////////////////// FILLING OBJECTS//////////////////////////////
        if(principal!=null){
            userId=userService.getUserByName(principal.getName()).getUserId();
        }


        ///////////////////////////ADDING TO MODEL////////////////////////////
        if(principal!=null){
            model.addAttribute("userId",userId);
        }
        model.addAttribute("departments",departmentService.getAllDepartment());
        model.addAttribute("userForm", new User());
        model.addAttribute("userEmail",userService.getUserByName(principal.getName()).getEmail());
        return "registration";
    }


    @RequestMapping(value = "/something")
    public String somethingUrl(Model model, @PathVariable("code") String authorizationCode){

        try {
            HttpTransport netTransport = new NetHttpTransport();
            JsonFactory jsonFactory = new JacksonFactory();
            GoogleTokenResponse token = new GoogleAuthorizationCodeTokenRequest(netTransport,
                    jsonFactory, CLIENT_ID, "jk0sCDkkvZnrJQLihTSDjzEr", authorizationCode,
                    "http://localhost:9099/something").execute();
            System.out.println("Valid access token " + token.getAccessToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "welcome";
    }

    @RequestMapping(value = "/welcome")
    public String welcomeUrl(Model model){

        try {
            String token=Common.getToken();//Common.refreshAccessToken("1/2kDjSKTfeLUWxGdiFeosDQIgvWWtCdPcl6o1tz7zUBU");//

            //model.addAttribute("code",token);
        } catch (Exception e) {
            e.printStackTrace();
            //model.addAttribute("code","fgzsdg");
        }
        return "welcome";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model,
                               Principal principal) {

        List<User> allUsers = userService.getAllUsers();

        model.addAttribute("users",allUsers);
        System.out.println("department id"+userForm.getDepId());

        if (bindingResult.hasErrors()) {

            model.addAttribute("departments",departmentService.getAllDepartment());
            model.addAttribute("userEmail",userService.getUserByName(principal.getName()).getEmail());
            return "registration";
        }
        //check whether thereis a hsenidmobile domain name.if then.
        if (userForm.getEmail().contains("@hsenidmobile")||userForm.getEmail().contains("@hsenid")) {
            userService.updateUserAccount(userForm);
        } else {
            model.addAttribute("errorName","this email don't have access");
            model.addAttribute("userEmail",userService.getUserByName(principal.getName()).getEmail());

            model.addAttribute("departments",departmentService.getAllDepartment());
            return "registration";
        }
        Integer userId=null;
        if(principal!=null){
            userId=userService.getUserByName(principal.getName()).getUserId();
            model.addAttribute("userId",userId);
        }
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
    public void loginWithGoogle(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        GoogleIdTokenVerifier verifier = null;
        String idTokenString = null;
        GoogleIdToken idToken = null;
        HttpTransport httpTransport = new UrlFetchTransport();



        idTokenString = request.getParameter("idtoken");
        verifier = new GoogleIdTokenVerifier.Builder(httpTransport, new JacksonFactory())
                .setAudience(Arrays.asList(this.CLIENT_ID))
                .setIssuer("https://accounts.google.com")
                .build();



        if (idTokenString == null) {
            System.out.println("no token mate");
            return;
        }
        if (verifier == null)
            System.out.println("no verifier!");
        if (verifier != null && idTokenString != null) {

            try {
                idToken = GoogleIdToken.parse(new JacksonFactory(), idTokenString);
            } catch (IOException e) {
                e.printStackTrace();
                ///THROW CUSTOM EXCEPTION TOKENNOTVALID();///
            }
        }


        try {
            if (idToken != null || verifier.verify(idToken)) {


                GoogleIdToken.Payload payload = idToken.getPayload();
                String email = payload.getEmail();
                User loginUserByEmail = null;
                loginUserByEmail = userService.getUserByEmail(email);
                String formUsername = loginUserByEmail.getUserName();
                String image =(String) payload.get("picture");//store url with profile data.

                //////////// TRIES TO LOGIN//////////////////////
                securityService.autologin(formUsername, email);
                ////////////////////////////SEND REDIRECT URL THROUGH HEADER/////////////////
                response.setHeader("Location", "http://localhost:9099/home");
//

                System.out.println(idTokenString);
                return;
            } else {
                System.out.println("couldn't verify! :" + idTokenString + "\nverifier:" + verifier.verify(idToken));
                ///EXCEPTIONS
            }
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            ///EXCEPTIONS
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    @RequestMapping(value ="/one")
    public void One(){
        System.out.println("one is executed");
    }

    @RequestMapping(value = "/three")
    public void three() {
        String clientId = "862712159345-ti9la1n9c7vtj95516st4q3nf4kt68rc.apps.googleusercontent.com";

        String url = "https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/admin.directory.user.readonly&redirect_uri=http://localhost:9099/one&response_type=code&client_id=" + clientId;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        System.out.println("before execution");
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("after execution");
    }

}
