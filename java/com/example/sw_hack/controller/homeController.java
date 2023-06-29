package com.example.sw_hack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeController {
    @GetMapping("/")
    public String step1(){
        return "selectType";
    }
    @GetMapping("/step2")
    public String step2(){
        return "setDistance";
    }
}
