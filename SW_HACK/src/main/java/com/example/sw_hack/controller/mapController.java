package com.example.sw_hack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class mapController {
    @GetMapping("/hi")
    public String marker(){
        return "test1";
    }
    @GetMapping("/hi1")
    public String marker1(){
        return "route";
    }
    @GetMapping("/getLocation")
    public String geoLocation(){
        return "geoLocation";
    }

    @GetMapping("/route")
    public String getRoute(){
        //model.addAttribute("lat", route[][0]);
        //model.addAttribute("lng", route[][1]);

        return "route";
    }
}
