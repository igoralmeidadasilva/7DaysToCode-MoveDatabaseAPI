package com.tmdb;

import java.util.List;
import com.tmdb.model.HTMLGenerator;
import com.tmdb.model.Movie;
import com.tmdb.service.MarvelApiClient;
import com.tmdb.service.TmdbApiClient;
import com.tmdb.model.TmdbMovieJsonParser;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

public class App 
{
    public static void main( String[] args ) throws URISyntaxException, IOException, InterruptedException, NoSuchAlgorithmException
    {
        tmdbApi();
        marvelApi();
    }
    
    private static void tmdbApi() throws URISyntaxException, IOException, InterruptedException{
        String API_KEY = "";
        TmdbMovieJsonParser jsonParser = new TmdbMovieJsonParser();
        TmdbApiClient client = new TmdbApiClient(API_KEY);
    
        List<Movie> movies = jsonParser.parse(jsonParser.parseJson(client.request().body()));
        try (
            PrintWriter writer = new PrintWriter("src\\main\\java\\com\\tmdb\\index.html")) {
            HTMLGenerator html = new HTMLGenerator(writer);
            html.generate(movies);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void marvelApi() throws NoSuchAlgorithmException, URISyntaxException, IOException, InterruptedException{
        String API_KEY = "";
        MarvelApiClient client = new MarvelApiClient(API_KEY);
        client.request();
    }
    
}
