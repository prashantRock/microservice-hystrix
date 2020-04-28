package com.hystrix.Hystrix.controller;

import com.hystrix.Hystrix.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @Autowired
    TestService testService;

    @RequestMapping(value = "/getGoogle", method = RequestMethod.GET)
    public String getStudents() {
        System.out.println("Going to call student service to get data!");
        return testService.callGoogleAPI();
    }

}
