package com.example.StudyCase.Controller;

import com.example.StudyCase.Service.NearbyService;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class nearbyController {


    private final NearbyService nearbyService;

     @GetMapping("/sade")
     @ResponseStatus(HttpStatus.OK)
    private void getNearbyLocation( ) throws IOException, JSONException {
          nearbyService.getLocation();
    }
    @GetMapping("/simple")
    @ResponseStatus(HttpStatus.OK)
    private Response getNearbyResponseLocation( ) throws IOException {
        return nearbyService.getResponseLocation();
    }
}
