package com.bootcodingtask.rating_functionality.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthCheckController {
    @GetMapping ("/health-check")
    public String healthCheck(){
        return "Ok,everything is running fine :)";
    }
}
