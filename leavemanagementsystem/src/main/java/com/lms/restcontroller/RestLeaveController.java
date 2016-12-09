package com.lms.restcontroller;

import com.lms.entity.Leave;
import com.lms.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by nuwantha on 12/8/16.
 */
@RestController
public class RestLeaveController {

    @Autowired
    LeaveService leaveService;

    @RequestMapping(
            value = "/api.leave",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Leave>> getAllLeaves(){
        List<Leave> allLeaves = leaveService.getAllLeaves();
        return new ResponseEntity<List<Leave>>(allLeaves, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api.leave.add",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Leave> addNewLeave(@RequestBody Leave leave) throws SQLException{
        System.out.println("add leave is called");
        leaveService.applyALeave(leave);
        return new ResponseEntity<Leave>(leave,HttpStatus.OK);
    }


}
