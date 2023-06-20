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

     @GetMapping()
     @ResponseStatus(HttpStatus.OK)
    private NearbyResponse getNearbyLocation(@RequestBody NearbyRequest nearbyRequest) throws IOException {
         return nearbyService.getLocation(nearbyRequest);
    }
    @GetMapping("/simple")
    @ResponseStatus(HttpStatus.OK)
    private Response getNearbyResponseLocation(@RequestBody NearbyRequest nearbyRequest) throws IOException {
        return nearbyService.getResponseLocation(nearbyRequest);
    }
}
