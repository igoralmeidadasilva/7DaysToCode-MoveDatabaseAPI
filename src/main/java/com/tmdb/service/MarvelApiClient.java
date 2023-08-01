package com.tmdb.service;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MarvelApiClient {
    private final String URL = "https://gateway.marvel.com/v1/public/series?ts=%s&hash=%s&apikey=%s";
    private final String API_KEY;

    public MarvelApiClient(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public void request() throws NoSuchAlgorithmException, URISyntaxException, IOException, InterruptedException{
        final String PRIVATE_KEY = "";
        final int TIME_STAMP = new Random().nextInt(10);
        final String HASH = getHash(TIME_STAMP + PRIVATE_KEY + API_KEY);
        String uri = String.format(URL, TIME_STAMP, HASH, API_KEY);
        
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
    }

    private static String getHash (String s) throws NoSuchAlgorithmException {
        MessageDigest retorno = MessageDigest.getInstance("MD5");
        retorno.update(s.getBytes(),0,s.length());
        return new BigInteger(1, retorno.digest()).toString(16);
    }
}
