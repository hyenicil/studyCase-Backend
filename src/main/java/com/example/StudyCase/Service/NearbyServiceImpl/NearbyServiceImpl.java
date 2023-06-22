package com.example.StudyCase.Service.NearbyServiceImpl;
import com.example.StudyCase.Core.ServiceTools;
import com.example.StudyCase.Dto.Request.NearbyRequest;
import com.example.StudyCase.Dto.Response.LocationResponse;
import com.example.StudyCase.Entity.Location;
import com.example.StudyCase.Entity.Nearby;
import com.example.StudyCase.Repository.LocationRepository;
import com.example.StudyCase.Repository.NearbyRepository;
import com.example.StudyCase.Service.NearbyService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NearbyServiceImpl implements NearbyService {

    private final NearbyRepository nearbyRepository;
    private final LocationRepository locationRepository;
    private final ModelMapper modelMapper;
    private final ServiceTools serviceTools;



    @Override
    public List<LocationResponse> getTransactions(NearbyRequest nearbyRequest) throws IOException, JSONException {
        String urlApi= serviceTools.getUrl(nearbyRequest);
        URL url = new URL(urlApi);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.connect();
        System.out.println(urlApi);
        return getGoogleApiLocation(httpURLConnection,nearbyRequest);
    }


    @Override
    public  List<LocationResponse>  getMainMethod(NearbyRequest nearbyRequest) throws JSONException, IOException {

        Nearby nearby = modelMapper.map(nearbyRequest,Nearby.class);
        if(nearbyRepository.existsByLatitudeAndLongitudeAndRadius(nearby.getLatitude(), nearby.getLongitude(),nearby.getRadius())){
            Nearby nearby1 = nearbyRepository.findByLatitudeAndLongitudeAndRadius(nearby.getLatitude(), nearby.getLongitude(),nearby.getRadius());
            return locationRepository.findByNearbyId(nearby1.getId(), LocationResponse.class);
        }
        else {
            return getTransactions(nearbyRequest);
        }

    }
    private List<LocationResponse> getDbLocation(Nearby nearby){
        return locationRepository.findByNearbyId(nearby.getId(), LocationResponse.class) ;
    }
    private List<LocationResponse> getGoogleApiLocation(HttpURLConnection httpURLConnection, NearbyRequest nearbyRequest) throws IOException, JSONException {
        List<Location> locationArrayList = new ArrayList<>();
        Nearby nearby = modelMapper.map(nearbyRequest,Nearby.class);
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
                location.setNearby(nearby);
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
        nearbyRepository.save(nearby);

        locationRepository.saveAll(locationArrayList);

        return locationRepository.findByNearbyId(nearby.getId(),LocationResponse.class);

    }
    private Boolean getByIsThere(Location location){
        return locationRepository.existsByLatitudeAndLongitude(location.getLatitude(), location.getLongitude());
    }



}
