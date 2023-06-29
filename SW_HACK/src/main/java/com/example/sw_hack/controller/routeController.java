package com.example.sw_hack.controller;

import com.example.sw_hack.admin.LatLng;
import com.example.sw_hack.service.GoogleMaps;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class routeController {
    private final GoogleMaps googleMaps;

    public routeController(GoogleMaps googleMaps) {
        this.googleMaps = googleMaps;
    }

    @GetMapping("/{origin}/{destination}/{alternatives}")
    public ResponseEntity getMap(@PathVariable(name = "origin")String origin, @PathVariable(name = "destination")String destination,
                                 @PathVariable(name = "alternatives")boolean alternatives) throws JsonProcessingException {
        return new ResponseEntity(googleMaps.makeDiretcion(origin,destination,alternatives), HttpStatus.ACCEPTED);

    }

    @GetMapping("/km/{km}")
    public String getMap(@PathVariable(name = "km")long km, Model model) {
        double[][] list =  googleMaps.getRandomPlace(km);
        model.addAttribute("latLng", list);
        return "test1";
    }
}
