package com.example.StudyCase.Service;

import com.example.StudyCase.Dto.Request.NearbyRequest;
import okhttp3.Response;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface NearbyService {


    void getLocation( ) throws IOException, JSONException;

    Response getResponseLocation( ) throws IOException;

    String getUrl(NearbyRequest nearbyRequest) throws IOException;

    void getTransactions(NearbyRequest nearbyRequest) throws IOException, JSONException;
}
