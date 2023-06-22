package com.example.StudyCase.Service;

import com.example.StudyCase.Dto.Request.NearbyRequest;
import com.example.StudyCase.Dto.Response.LocationResponse;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface NearbyService {
    List<LocationResponse> getTransactions(NearbyRequest nearbyRequest) throws IOException, JSONException;

    List<LocationResponse> getMainMethod(NearbyRequest nearbyRequest) throws JSONException, IOException;
}
