package com.example.sw_hack.controller;

import com.example.sw_hack.admin.LatLng;
import com.example.sw_hack.service.GoogleMaps;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

//@Slf4j
@Controller
public class homeController {
    private final GoogleMaps googleMaps;

    public homeController(GoogleMaps googleMaps) {
        this.googleMaps = googleMaps;
    }

    @GetMapping("/")
    public String step1() {
        return "selectType";
    }

    @GetMapping("/step2")
    public String step2() {
        return "setDistance";
    }

    //    @PostMapping("/tmap/{km}")
//    public String calculateCurLocation(@PathVariable(name="km")long km, LatLng latLng, Model model){
//        googleMaps.setLatitude(latLng.getLatitude());
//        googleMaps.setLongitude(latLng.getLongitude());
//        double[][] list = googleMaps.getRandomPlace(km);
//        model.addAttribute("latLng", list);
//        log.info(String.valueOf(latLng.getLatitude()));
//        log.info(String.valueOf(km));
//        return "tmap";
//    }
    @GetMapping("/tmap/{km}/{lat}/{lon}")
    public String calculateCurLocation(@PathVariable(name = "km") String km,
                                               @PathVariable(name = "lat") String lat,
                                               @PathVariable(name = "lon") String lon,
                                               Model model) {
        googleMaps.setLatitude(lat);
        googleMaps.setLongitude(lon);
        double[][] list = googleMaps.getRandomPlace(Long.parseLong(km));
        model.addAttribute("latLng", list);
        return "tmap";
    }
}