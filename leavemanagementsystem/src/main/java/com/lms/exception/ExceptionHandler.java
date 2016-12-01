package com.lms.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.sql.SQLException;

/**
 * Created by nuwantha on 11/16/16.
 */

@ControllerAdvice
public class ExceptionHandler {
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(value = SQLException.class)
    public String handleSqlException(Exception exception){
        System.out.println("eraeeeeeeeeeeeee");
        return "redirect:errorcustom/sqlexception";
    }


    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(value =  Exception.class)
    public String emptyResultDataAccessExceptionHandler(Exception exception){
        return "redirect:errorcustom/emptyResultDataAccessException";
    }




}
