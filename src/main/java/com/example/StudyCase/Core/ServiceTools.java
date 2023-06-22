package com.example.StudyCase.Core;

import com.example.StudyCase.Dto.Request.NearbyRequest;
import org.springframework.beans.factory.annotation.Value;

public class ServiceTools {


    @Value("${api.key}")
    private  String apiKey;

    public  String getUrl(NearbyRequest nearbyRequest) {
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
        String location ="location=" + nearbyRequest.getLatitude() + "," + nearbyRequest.getLongitude();
        String radius = "&radius=" + nearbyRequest.getRadius();
        String key ="&key="+ apiKey;
        return url+location+radius + key;
    }
}
