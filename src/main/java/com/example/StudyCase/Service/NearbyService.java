package com.example.StudyCase.Service;

import okhttp3.Response;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface NearbyService {
    void getLocation( ) throws IOException, JSONException;

    Response getResponseLocation( ) throws IOException;
}
