package com.example.StudyCase.Controller;

import com.example.StudyCase.Dto.Request.NearbyRequest;
import com.example.StudyCase.Dto.Response.LocationResponse;
import com.example.StudyCase.Service.NearbyService;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class nearbyController {

    private final NearbyService nearbyService;

    public nearbyController(NearbyService nearbyService) {
        this.nearbyService = nearbyService;
    }

    @GetMapping("/nearby")
    @ResponseStatus(HttpStatus.OK)
    private List<LocationResponse> getLocation( @RequestParam String latitude, @RequestParam String longitude,@RequestParam int radius ) throws JSONException, IOException {
        NearbyRequest nearbyRequest = new NearbyRequest(longitude,latitude,radius);
        return  nearbyService.getMainMethod(nearbyRequest);
    }


}
