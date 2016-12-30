package com.lms.common;

import com.google.api.client.auth.oauth2.*;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.lms.entity.Leave;
import com.lms.entity.User;
import com.lms.jasonWrapper.Users;
import com.lms.jasonWrapper.Wrapper;
import com.lms.formentity.BulkLeaveForm;
import com.lms.service.UserService;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.transform.sax.SAXSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by nuwantha on 11/18/16.
 */
@Component
public class Common {
    public static final VerificationCodeReceiver receiver = new LocalServerReceiver.Builder().setHost("localhost").setPort(43057).build();

    @Autowired
    UserService userService;

    public static int convertMonthToInt(String month){
        switch (month){
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;



        }
        return 0;
    }

    public static String getMonthName(int monthNumber) {
        String[] months = new DateFormatSymbols().getMonths();
        int n = monthNumber-1;
        return (n >= 0 && n <= 11) ? months[n] : "wrong number";
    }



    public ArrayList<Leave> createLeaveList(BulkLeaveForm bulkLeaveForm){

        ArrayList<Leave> todayLeaveList = new ArrayList<>();
        todayLeaveList.addAll(createLeaveOfGivenLeaveType(bulkLeaveForm.getUsernamesOfFulldayLeave(),"full day"));
        todayLeaveList.addAll(createLeaveOfGivenLeaveType(bulkLeaveForm.getUsernamesOfFirstHalfLeave(),"first half"));
        todayLeaveList.addAll(createLeaveOfGivenLeaveType(bulkLeaveForm.getUsernamesOfSecondHalfLeave(),"second half"));
        return todayLeaveList;
    }


    public ArrayList<Leave> createLeaveOfGivenLeaveType(List<String> names,String leaveType){

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String dateString = dateFormat.format(date);
            ArrayList<Leave> todayLeaveListOfGivenLeaveType = new ArrayList<Leave>();
            for (String name :names){
                name=name.substring(1,name.length()-1);

                Leave leave = new Leave();
                User userByName = userService.getUserByName(name);
                leave.setName(name);
                leave.setUserId(userByName.getUserId());
                leave.setLeaveType(leaveType);
                leave.setLeaveDate(dateString);
                todayLeaveListOfGivenLeaveType.add(leave);
            }

            return todayLeaveListOfGivenLeaveType;

    }

    public static String getCurrentDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }



    public static String getToken() throws Exception {

        String clientId = "862712159345-ti9la1n9c7vtj95516st4q3nf4kt68rc.apps.googleusercontent.com";
        String clientSecret = "jk0sCDkkvZnrJQLihTSDjzEr";
        String scope = "https://www.googleapis.com/auth/admin.directory.user.readonly";



        AuthorizationCodeFlow flow=
                new AuthorizationCodeFlow.Builder(BearerToken.authorizationHeaderAccessMethod(),
                        new NetHttpTransport(),
                        new JacksonFactory(),
                        new GenericUrl(GoogleOAuthConstants.TOKEN_SERVER_URL),
                        new BasicAuthentication(clientId, clientSecret),
                        clientId,
                        GoogleOAuthConstants.AUTHORIZATION_SERVER_URL)
                        .setScopes(Collections.singleton(scope)).build();


        System.out.println("file: "+new File("src/main/resources/leave-notifier-4567f7fd912b.p12").canRead());
        GoogleCredential googleCredential = new GoogleCredential.Builder()
                .setTransport(new NetHttpTransport())
                .setJsonFactory(new JacksonFactory())
                .setServiceAccountId("leave-notifier@appspot.gserviceaccount.com")
                .setServiceAccountPrivateKeyFromP12File(new File("src/main/resources/leave-notifier-4567f7fd912b.p12"))
                .setServiceAccountScopes(Collections.singleton(scope))
                .setServiceAccountUser("akilac@hsenidmobile.com")
                .build();

        System.out.println("GoogleCredential.access token:"+googleCredential.getAccessToken());
        System.out.println("token: just");
        Credential credentials =null;
        try {
            receiver.stop();
        }finally {
            try{
                class CustomThread extends Thread{
                    public void setStatus(boolean status) {
                        this.status = status;
                    }

                    public boolean getStatus() {
                        return status;
                    }

                    private boolean status=false;

                    public void run(){
                        while (!this.getStatus()){}
                        synchronized (this){
                            String url = "https://accounts.google.com/o/oauth2/auth?client_id=862712159345-ti9la1n9c7vtj95516st4q3nf4kt68rc.apps.googleusercontent.com&redirect_uri=http://localhost:43057/Callback&response_type=code&scope=https://www.googleapis.com/auth/admin.directory.user.readonly";
                            String os = System.getProperty("os.name").toLowerCase();
                            if(os.indexOf( "win" ) >= 0){
                                try {
                                    Runtime rt = Runtime.getRuntime();
                                    Process p =rt.exec( "rundll32 url.dll,FileProtocolHandler " + url);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }else if(os.indexOf( "mac" ) >= 0){
                                try {
                                    Runtime rt = Runtime.getRuntime();
                                    Process p=rt.exec( "open" + url);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }else {
                                try {
                                    Runtime rt = Runtime.getRuntime();
                                    String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror",
                                            "netscape","opera","links","lynx"};
                                    StringBuffer cmd = new StringBuffer();
                                    for (int i=0; i<browsers.length; i++)
                                        cmd.append( (i==0  ? "" : " || " ) + browsers[i] +" \"" + url + "\" ");
                                    Process p=rt.exec(new String[] { "sh", "-c", cmd.toString() });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                };
                CustomThread customThread = new CustomThread();
                customThread.start();
                synchronized (new Object()) {
                    customThread.setStatus(true);
                    credentials = new AuthorizationCodeInstalledApp(flow, receiver)
                            .authorize("akilac@hsenidmobile.com");
                }

                //send request to https://accounts.google.com/o/oauth2/auth?client_id=862712159345-ti9la1n9c7vtj95516st4q3nf4kt68rc.apps.googleusercontent.com&redirect_uri=http://localhost:43057/Callback&response_type=code&scope=https://www.googleapis.com/auth/admin.directory.user.readonly
            }catch (Exception e){
                System.out.println("Error: "+e.getMessage());
            }finally {
                System.out.println("token: "+credentials.getAccessToken()+"\nrefresh: "+credentials.getRefreshToken());
                receiver.stop();
            }

        }
        return credentials.getAccessToken();
    }

    public static List<User> printUserList(){
        final String uri = "https://www.googleapis.com/admin/directory/v1/users?viewType=domain_public&domain=hsenidmobile.com&maxResults=500";
        String token=null;
        try {
            token=getToken();
        }catch (Exception e){
            System.out.println("error: "+e.getMessage());
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String authorization="Bearer  "+token;
         List<User> userList=new ArrayList<User>();
        if(token!=null) {
            System.out.println("new token in use");

            headers.set("Authorization",authorization);
            HttpEntity<String> entity = new HttpEntity<String>("parameters",headers);


            ResponseEntity<Wrapper> result = restTemplate.exchange(uri, HttpMethod.GET, entity, Wrapper.class);
            Wrapper body = result.getBody();
            body.getUsers();

            System.out.println(body.getUsers().size());
            for (Users users:body.getUsers()){
                User user = new User();
                user.setDepId("1");
                user.setRole("ROLE_USER");
                user.setEmail(users.getPrimaryEmail());
                user.setUserName(users.getName().getGivenName());
                userList.add(user);

            }
        }
        return userList;
    }
}
