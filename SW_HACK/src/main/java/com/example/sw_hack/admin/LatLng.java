package com.example.sw_hack.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LatLng {
    private double latitude;
    private double longitude;
    public LatLng(){}

    public LatLng(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "LatLng{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}






