package com.example.StudyCase.Service;

import com.example.StudyCase.Dto.Request.NearbyRequest;
import com.example.StudyCase.Dto.Response.NearbyResponse;
import okhttp3.Response;

import java.io.IOException;

public interface NearbyService {
    NearbyResponse getLocation(NearbyRequest nearbyRequest) throws IOException;

    Response getResponseLocation(NearbyRequest nearbyRequest) throws IOException;
}
