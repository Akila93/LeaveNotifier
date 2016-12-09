package com.lms.formentity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akila on 12/9/16.
 */
public class BulkLeaveForm implements Form {
    private List<String> usernamesOfFulldayLeave;
    private List<String> usernamesOfFirstHalfLeave;
    private List<String> usernamesOfSecondHalfLeave;

    public BulkLeaveForm(){
        this.usernamesOfFulldayLeave=new ArrayList<String>();
        this.usernamesOfFirstHalfLeave=new ArrayList<String>();
        this.usernamesOfSecondHalfLeave=new ArrayList<String>();
    }

    public List<String> getUsernamesOfFulldayLeave() {
        return usernamesOfFulldayLeave;
    }

    public void setUsernamesOfFulldayLeave(List<String> usernamesOfFulldayLeave) {
        this.usernamesOfFulldayLeave = usernamesOfFulldayLeave;
    }

    public List<String> getUsernamesOfFirstHalfLeave() {
        return usernamesOfFirstHalfLeave;
    }

    public void setUsernamesOfFirstHalfLeave(List<String> usernamesOfFirstHalfLeave) {
        this.usernamesOfFirstHalfLeave = usernamesOfFirstHalfLeave;
    }

    public List<String> getUsernamesOfSecondHalfLeave() {
        return usernamesOfSecondHalfLeave;
    }

    public void setUsernamesOfSecondHalfLeave(List<String> usernamesOfSecondHalfLeave) {
        this.usernamesOfSecondHalfLeave = usernamesOfSecondHalfLeave;
    }
}
