package com.example.sw_hack.controller;

import com.example.sw_hack.admin.LatLng;
import com.example.sw_hack.service.GoogleMaps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
public class homeController {
    private final GoogleMaps googleMaps;

    public homeController(GoogleMaps googleMaps) {
        this.googleMaps = googleMaps;
    }

    @GetMapping("/")
    public String main(){
        return "main";
    }
    @GetMapping("/step1")
    public String step1() {
        return "selectType";
    }

    @GetMapping("/step2")
    public String step2() {
        list = null;
        return "setDistance";
    }
    List<double[]> list;

    @GetMapping("/tmap/{km}/{lat}/{lon}")
    public String course1(@PathVariable(name = "km") String km,
                          @PathVariable(name = "lat") String lat,
                          @PathVariable(name = "lon") String lon,
                          Model model) {
        if (list == null) {
            googleMaps.setLatitude(lat);
            googleMaps.setLongitude(lon);
            list = googleMaps.getRandomPlace(Long.parseLong(km));
        }

        model.addAttribute("latLng", list.get(0));
        log.info(String.valueOf(list.get(0)[0]));
        return "course1";
    }
    private int index = 0;

    @GetMapping("/tmap/course1")
    public String course1(Model model) {
        model.addAttribute("latLng", list.get(0));
        index = 0;
        log.info(String.valueOf(list.get(0)[0]));
        log.info(String.valueOf(list.get(0)[1]));

        return "course1";
    }
    @PostMapping("/tmap/course1")
    public String course1_1(@RequestParam("booleanValue") Boolean booleanValue, Model model) {
        if(booleanValue && list.size() > 3){
            list.subList(0, 3).clear();
        }
        model.addAttribute("latLng", list.get(0));
        index = 0;
        return "course1";
    }
    @GetMapping("/tmap/course2")
    public String course2(Model model) {
        model.addAttribute("latLng", list.get(1));
        index = 1;
        return "course2";
    }
    @PostMapping("/tmap/course2")
    public String course2_1(@RequestParam("booleanValue") Boolean booleanValue, Model model) {
        if(booleanValue && list.size() > 3){
            list.subList(0, 3).clear();
        }
        model.addAttribute("latLng", list.get(1));
        index = 1;
        return "course2";
    }
    @GetMapping("/tmap/course3")
    public String course3(Model model) {
        model.addAttribute("latLng", list.get(2));
        index = 2;
        return "course3";
    }
    @PostMapping("/tmap/course3")
    public String course3_1(@RequestParam("booleanValue") Boolean value, Model model) {
        if(value && list.size() > 3){
            list.subList(0, 3).clear();
        }
        model.addAttribute("latLng", list.get(2));
        index = 2;
        return "course3";
    }
    @GetMapping("/tmap/final")
    public String finalCourse(Model model){
        model.addAttribute("latLng", list.get(index));
        return "onWalking";
    }
}