package com.example.StudyCase.Service.NearbyServiceImpl;
import com.example.StudyCase.Dto.Request.NearbyRequest;
import com.example.StudyCase.Entity.Location;
import com.example.StudyCase.Repository.LocationRepository;
import com.example.StudyCase.Service.NearbyService;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NearbyServiceImpl implements NearbyService {

    private static final String apiKey="AIzaSyDKiYmQddIuGh9NQsH6n4SIHDYUoFNS6Xk";

    private final LocationRepository locationRepository;




    @Override
    public void getLocation() throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String urlApi="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522%2C151.1957362&radius=1500&key=AIzaSyDKiYmQddIuGh9NQsH6n4SIHDYUoFNS6Xk";
        System.out.println(urlApi);

       URL url = new URL(urlApi);
       HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
       httpURLConnection.setRequestMethod("GET");
       httpURLConnection.connect();


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
        return response;
    }

    @Override
    public String getUrl(NearbyRequest nearbyRequest) {
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
        String location ="location="+nearbyRequest.getLatitude()+","+nearbyRequest.getLongitude();
        String radius = "&radius="+nearbyRequest.getRadius();
        String key= "&key="+apiKey;
        String urlApi=url+location+radius+key;
        return urlApi;
    }

    @Override
    public void getTransactions(NearbyRequest nearbyRequest) throws IOException, JSONException {
        String url=getUrl(nearbyRequest);
        HttpURLConnection httpURLConnection= getConnectMethod(url);
        getMainMethod(httpURLConnection);
    }

    private HttpURLConnection getConnectMethod(String urlApi) throws IOException {
        URL url = new URL(urlApi);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.connect();
        return httpURLConnection;
    }

    private void getMainMethod(HttpURLConnection httpURLConnection) throws IOException, JSONException {
        List<Location> locationArrayList = new ArrayList<>();
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
            for(int i = 0; i<jsonArray.length();i++){
                Location location =  new Location();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject geometry = jsonObject.getJSONObject("geometry");
                JSONObject locationString = geometry.getJSONObject("location");
                String latitude = locationString.getString("lat");
                String longitude = locationString.getString("lng");
                String name = jsonObject.getString("name");
                JSONArray types = jsonObject.getJSONArray("types");
                location.setName(name);
                location.setLatitude(latitude);
                location.setLongitude(longitude);
                List<String> list = new ArrayList<>();
                for (int t = 0; t<types.length();t++){
                   list.add(types.getString(t));
                }
                location.setTypes(list);
                if(!getByIsThere(location)){
                    locationArrayList.add(location);
                }
            }
        }
        locationRepository.saveAll(locationArrayList);
    }
    private Boolean getByIsThere(Location location){
        return locationRepository.existsByLatitudeAndLongitude(location.getLatitude(), location.getLongitude());
    }
    private List<Location> getAllLocation(){
        return locationRepository.findAll();
    }


}
