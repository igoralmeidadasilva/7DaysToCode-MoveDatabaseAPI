package com.tmdb.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TmdbApiClient {
    private final String URL = "https://api.themoviedb.org/3/movie/popular?api_key=%s&language=en-US&page=1";
    private final String API_KEY;

    public TmdbApiClient(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public HttpResponse<String> request () throws URISyntaxException, IOException, InterruptedException {
        String uri = String.format(URL, API_KEY);
    
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
            .newBuilder()
            .uri(new URI(uri))
            .GET()
            .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    
        System.out.println(response.statusCode());
        if(response.statusCode() == 200){
            System.out.println("Sucess!");
        } else {
            System.out.println("Error!");
        }
        return response;
    } 
}
