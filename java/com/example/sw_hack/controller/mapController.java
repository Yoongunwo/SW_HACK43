package com.example.sw_hack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mapController {
    @GetMapping("/hi")
    public String marker(){
        return "test1";
    }
    @GetMapping("/hi1")
    public String marker1(){
        return "test2";
    }
    @GetMapping("/curloc")
    public String curLocation(){
        return "curLocation";
    }
    @GetMapping("/route")
    public String route(){
        return "directionService";
    }
    @GetMapping("/geoLocation")
    public String geoLocation(){
        return "geoLocation";
    }

}
