package com.example.sw_hack.controller;


import com.example.sw_hack.service.GoogleMaps;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class LocationController {
    private final GoogleMaps googleMaps;

    public LocationController(GoogleMaps googleMaps) {
        this.googleMaps = googleMaps;
    }

    @PostMapping("/location")
    public ResponseEntity<String> getLocation(
            @RequestParam(name = "origin") String origin,
            @RequestParam(name = "destination") String destination,
            @RequestParam(name = "mode") String mode,
            @RequestParam(name = "alternatives") boolean alternatives

    ) throws IOException {
        // 여기에서 요청을 처리하고 결과를 반환합니다.
        googleMaps.getLocation(origin,destination,mode,alternatives);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}

