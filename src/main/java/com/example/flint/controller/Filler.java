package com.example.flint.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Filler {
    @RequestMapping("/")
    public String hello(){
        return "Hello User";
    }
}
