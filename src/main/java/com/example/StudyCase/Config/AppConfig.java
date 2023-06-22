package com.example.StudyCase.Config;

import com.example.StudyCase.Core.ServiceTools;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public HttpHeaders httpHeaders (){
        HttpHeaders httpHeaders = new HttpHeaders();
        return httpHeaders;
    }
    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper= new ModelMapper();
        return  modelMapper;
    }
    @Bean
    public ServiceTools serviceTools(){
        ServiceTools serviceTools= new ServiceTools();
        return  serviceTools;
    }
}
