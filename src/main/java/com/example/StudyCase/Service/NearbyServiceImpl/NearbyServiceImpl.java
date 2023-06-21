package com.example.StudyCase.Service.NearbyServiceImpl;

import com.example.StudyCase.Entity.Geometry;
import com.example.StudyCase.Service.NearbyService;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import io.swagger.v3.core.util.Json;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RequiredArgsConstructor
@Service
public class NearbyServiceImpl implements NearbyService {
    @Override
    public void getLocation() throws IOException, JSONException {
        HttpHeaders httpHeaders = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String urlApi="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522%2C151.1957362&radius=1500&key=AIzaSyDKiYmQddIuGh9NQsH6n4SIHDYUoFNS6Xk";
        System.out.println(urlApi);

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
       URL url = new URL(urlApi);
       HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
       httpURLConnection.setRequestMethod("GET");
       httpURLConnection.connect();

       int responseCodeHttp = httpURLConnection.getResponseCode();

       if(responseCodeHttp!=200 ){
           throw new RuntimeException("ResponseCode:" + responseCodeHttp);
       }
       else {
           BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
           String inputReader;
           StringBuilder buffer = new StringBuilder();
           while ((inputReader=reader.readLine())!=null){
               buffer.append(inputReader);
           }
           reader.close();
           httpURLConnection.disconnect();
           JSONObject object = new JSONObject(buffer.toString());
           JSONArray jsonArray = object.getJSONArray("results");
           System.out.println(jsonArray);

           for(int i = 0; i<jsonArray.length();i++){
               JSONObject jsonObject = jsonArray.getJSONObject(i);
               JSONObject geometry = jsonObject.getJSONObject("geometry");
               JSONObject location = geometry.getJSONObject("location");
               String latitude = location.getString("lat");
               String longitude = location.getString("lng");
               String name = jsonObject.getString("name");
               System.out.println(name+", " +latitude+", "+longitude);
           }
       }
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
        Response response = client.newCall(request).execute();
        return response;
    }
}
