package com.example.sw_hack.controller;

import org.springframework.stereotype.Controller;
import com.example.sw_hack.service.GoogleMaps;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class tmapController {
    private final GoogleMaps googleMaps;
    public tmapController(GoogleMaps googleMaps) {
        this.googleMaps = googleMaps;
    }

/*    @GetMapping("/tmap/{km}")
    public String tmap(@PathVariable(name="km")long km, Model model){
        double[][] list =  googleMaps.getRandomPlace(km);
        model.addAttribute("latLng", list);
        return "tmap";
    }*/

    @GetMapping("/tmap/curlocate")
    public String curloc(){
        return "curlocate";
    }
}
