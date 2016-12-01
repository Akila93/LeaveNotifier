package com.lms.controller;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.lms.entity.User;
import com.lms.service.SecurityService;
import com.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Arrays;


/**
 * Created by akila on 11/15/16.
 */
@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    String CLIENT_ID = "862712159345-ti9la1n9c7vtj95516st4q3nf4kt68rc";


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model,
                               Principal principal) {
        model.addAttribute("userForm", new User());
        Integer userId=null;
        if(principal!=null){
            userId=userService.getUserByName(principal.getName()).getUserId();
            model.addAttribute("userId",userId);
        }
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model,
                               Principal principal) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        //check whether thereis a hsenidmobile domain name.if then.
        if (userForm.getEmail().contains("@hsenidmobile")) {
            userService.createUserAccount(userForm);

        } else {
            model.addAttribute("errorName","this email didn't have access");
            return "registration";
        }
        Integer userId=null;
        if(principal!=null){
            userId=userService.getUserByName(principal.getName()).getUserId();
            model.addAttribute("userId",userId);
        }

        //securityService.autologin(userForm.getUsername(),userForm.getEmail());

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
        System.out.println("request:" + request.getParameter("idtoken"));
        GoogleIdTokenVerifier verifier = null;
        String idTokenString = null;
        GoogleIdToken idToken = null;
        idTokenString = request.getParameter("idtoken");
        HttpTransport httpTransport = new UrlFetchTransport();
        //get token
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
            }
        }


        try {
            if (idToken != null || verifier.verify(idToken)) {
                System.out.println("yoooo valid token");
                GoogleIdToken.Payload payload = idToken.getPayload();
                String email = payload.getEmail();
                System.out.println(email);
                User loginUserByEmail = null;
                loginUserByEmail = userService.getUserByEmail(email);

                String formUsername = loginUserByEmail.getUserName();
                securityService.autologin(formUsername, email);
                response.setHeader("Location", "http://localhost:9099/home");
                return;
            } else {
                System.out.println("couldn't verify! :" + idTokenString + "\nverifier:" + verifier.verify(idToken));
            }
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

}
