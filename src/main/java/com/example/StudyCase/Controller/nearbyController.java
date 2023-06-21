package com.example.StudyCase.Controller;

import com.example.StudyCase.Dto.Request.NearbyRequest;
import com.example.StudyCase.Dto.Response.NearbyResponse;
import com.example.StudyCase.Service.NearbyService;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class nearbyController {


    private final NearbyService nearbyService;

     @GetMapping("/sade")
     @ResponseStatus(HttpStatus.OK)
    private Response getNearbyLocation( ) throws IOException {
         return nearbyService.getLocation();
    }
    @GetMapping("/simple")
    @ResponseStatus(HttpStatus.OK)
    private Response getNearbyResponseLocation( ) throws IOException {
        return nearbyService.getResponseLocation();
    }
}
