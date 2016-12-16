package com.lms.service;

import com.lms.common.Common;
import com.lms.data.access.DepartmentDao;
import com.lms.entity.Department;
import com.lms.entity.Leave;
import com.lms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;

/**
 * Created by nuwantha on 12/14/16.
 */
@Service
public class ReportService {
    @Autowired
    UserService userService;

    @Autowired
    LeaveService leaveService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean send() {
        boolean send=true;
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            //helper.setTo("nuwanthad@hsenidmobile.com");
            //helper.setReplyTo("someone@localhost");
            helper.setTo("akilac@hsenidmobile.com");

            helper.setSubject("Leave Summary Detail On "+ Common.getCurrentDate());
            helper.setText("",generateMailBody());
        } catch (MessagingException e) {
            send=false;
            e.printStackTrace();
            return send;
        } finally {}
        javaMailSender.send(mail);
        return send;
    }

    public String generateMailBody(){

        StringBuilder messageBody = new StringBuilder();
        messageBody.append("<html> <head> </head> <body> Dear sir, <br /> Following employees have applied leave today" );


        List<Department> allDepartment = departmentService.getAllDepartment();
        for (Department department : allDepartment) {
            String depName = department.getDepName();

            List<Leave> leaveList=leaveService.getTodayLeaves(department.getDepID());
            if(!leaveList.isEmpty()) {
                messageBody.append("<h3>" + depName + "</h3>");
                messageBody.append("<table style=\" border: 1px solid black;\n" +
                        "    border-collapse: collapse;\" width:100%;> <thead> <th style=\" border: 1px solid black;\n" +
                        "    border-collapse: collapse;\" > Employee Name </th> <th style=\" border: 1px solid black;\n" +
                        "    border-collapse: collapse;\"> Leave Type </th> </thead> <tbody> ");

                for (Leave leave : leaveList) {

                    User user = userService.getUserById(leave.getUserId());
                    messageBody.append("<tr><td style=\" border: 1px solid black;\n" +
                            "    border-collapse: collapse;\">" + user.getUserName() + " </td>");
                    messageBody.append("<td style=\" border: 1px solid black;\n" +
                            "    border-collapse: collapse;\">" + leave.getLeaveType() + " </td> </tr>");

                }
                messageBody.append("</tbody></table>");
            }


        }
        messageBody.append("<br /> <br /> Thank you, <br /> best regards, <br /> HR HsenidMobile.");
        messageBody.append("</body> </html>");
        return messageBody.toString();


    }
}
