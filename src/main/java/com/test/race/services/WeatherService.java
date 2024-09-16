package com.test.race.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private final String API_KEY = "MY_API_KEY";
    private final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";

    public String getWeatherForRace(String location) {
        RestTemplate restTemplate = new RestTemplate();
        String url = WEATHER_URL + "?q=" + location + "&appid=" + API_KEY;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}