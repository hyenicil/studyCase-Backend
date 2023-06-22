package com.example.StudyCase.Controller;

import com.example.StudyCase.Dto.Request.NearbyRequest;
import com.example.StudyCase.Service.NearbyService;
import lombok.SneakyThrows;
import okhttp3.Response;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping
@CrossOrigin
public class nearbyController {

    private final NearbyService nearbyService;

    public nearbyController(NearbyService nearbyService) {
        this.nearbyService = nearbyService;
    }

    @PostMapping("/final")
    @ResponseStatus(HttpStatus.OK)
    private void getLocation(@RequestBody NearbyRequest nearbyRequest) throws JSONException, IOException {
        nearbyService.getTransactions(nearbyRequest);
    }



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
