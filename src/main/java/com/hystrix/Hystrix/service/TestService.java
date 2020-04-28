package com.hystrix.Hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class TestService {

    //TODO : https://github.com/Netflix/Hystrix/wiki/Configuration - to set parameters
    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @HystrixCommand(fallbackMethod = "callStudentServiceAndGetData_Fallback")
    public String callGoogleAPI() {


        String response = restTemplate
                .exchange("http://3.14.83.14:8080/"
                        , HttpMethod.GET
                        , null
                        , new ParameterizedTypeReference<String>() {
                        }).getBody();

        System.out.println("Response Received as " + response + " -  " + new Date());

        return "NORMAL FLOW !!! - Google page " + response + " -  " + new Date();
    }

    @SuppressWarnings("unused")
    private String callStudentServiceAndGetData_Fallback() {

        System.out.println("Google is down!!! fallback route enabled...");

        return "CIRCUIT BREAKER ENABLED!!! No Response From Google at this moment. " +
                " Service will be back shortly - " + new Date();
    }

}
