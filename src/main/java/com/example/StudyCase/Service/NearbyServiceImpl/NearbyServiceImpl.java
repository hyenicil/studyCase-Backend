package com.example.StudyCase.Service.NearbyServiceImpl;

import com.example.StudyCase.Dto.Request.NearbyRequest;
import com.example.StudyCase.Dto.Response.NearbyResponse;
import com.example.StudyCase.Service.NearbyService;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NearbyServiceImpl implements NearbyService {
    @Override
    public NearbyResponse getLocation(NearbyRequest nearbyRequest) throws IOException {
        return null;
    }

    @Override
    public Response getResponseLocation(NearbyRequest nearbyRequest) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522%2C151.1957362&radius=1500&type=restaurant&keyword=cruise&key=YOUR_API_KEY")
                .method("GET", body)
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }
}
