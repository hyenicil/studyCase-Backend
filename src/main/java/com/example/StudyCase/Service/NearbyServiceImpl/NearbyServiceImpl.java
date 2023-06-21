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
    public Response getLocation() throws IOException {
        HttpUrl mySearchUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("maps.googleapis.com")
                .addPathSegment("maps")
                .addPathSegment("api")
                .addPathSegment("place")
                .addPathSegment("nearbysearch")
                .addPathSegment("json")
                .addQueryParameter("location","-33.8670522,151.1957362")
                .addQueryParameter("radius","1500")
                .addQueryParameter("key","AIzaSyDKiYmQddIuGh9NQsH6n4SIHDYUoFNS6Xk").build();
        System.out.println(mySearchUrl);
        Request request1 = new Request.Builder()
                .url(mySearchUrl)
                .addHeader("Accept","application/json")
                .method("GET",null)
                .build();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Response response = client.newCall(request1).execute();
        System.out.println(response);
        return response;
    }

    @Override
    public Response getResponseLocation() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522%2C151.1957362&radius=1500&key=AIzaSyDKiYmQddIuGh9NQsH6n4SIHDYUoFNS6Xk")
                .method("GET", body)
                .build();
        System.out.println();
        Response response = client.newCall(request).execute();
        return response;
    }
}
