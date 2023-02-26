package com.adityasrivastava.taskmanagerwithauth.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    String sayHello(){
        return "Hello!";
    }
}
