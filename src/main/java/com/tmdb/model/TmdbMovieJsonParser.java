package com.tmdb.model;

import java.util.ArrayList;
import java.util.List;

public class TmdbMovieJsonParser implements JsonParser{

    public String[] parseJson (String json){
        int indiceInferior = json.indexOf("[");
        int indiceSuperior = json.lastIndexOf("]");
        String substring = json.substring(indiceInferior, indiceSuperior);
        String[] filmes = substring.split("}");
        return filmes;
    }

    @Override
    public List<Movie> parse(String[] lista){
        List<Movie> movies = new ArrayList<>();
        for (String filme : lista) {
            String id = getMovieId(filme);
            String title = getMovieTitle(filme);
            String overview = getMovieOverview(filme);
            String poster = getMoviePoster(filme);
            String release = getMovieRelease(filme);
            String average = getMovieAverage(filme);
            Movie item = new Movie(id, title, overview, poster, release, average);
            movies.add(item);
        }  
        return movies;
    }

    private String getMovieId(String filme){
        int indiceInferior = filme.indexOf("\"id\":");
        int indiceSuperior = filme.lastIndexOf(",\"original_language\"");
        String retorno = filme.substring(indiceInferior, indiceSuperior);
        retorno = retorno.substring(retorno.indexOf(":")+1, retorno.length());
        return retorno;
    }

    private String getMovieTitle(String filme){
        int indiceInferior = filme.indexOf("\"original_title\":");
        int indiceSuperior = filme.lastIndexOf(",\"overview\":");
        String retorno = filme.substring(indiceInferior, indiceSuperior);
        retorno = retorno.substring(retorno.indexOf(":")+1, retorno.length());
        return retorno;
    }

    private String getMovieOverview(String filme){
        int indiceInferior = filme.indexOf("\"overview\":");
        int indiceSuperior = filme.lastIndexOf(",\"popularity\":");
        String retorno = filme.substring(indiceInferior, indiceSuperior);
        retorno = retorno.substring(retorno.indexOf(":")+1, retorno.length());
        return retorno;
    }

    private String getMoviePoster(String filme){
        int indiceInferior = filme.indexOf("\"poster_path\":");
        int indiceSuperior = filme.lastIndexOf(",\"release_date\":");
        String retorno = filme.substring(indiceInferior, indiceSuperior);
        retorno = retorno.substring(retorno.indexOf(":")+1, retorno.length());
        retorno = retorno.substring(1, retorno.length()-1);
        return retorno;
    }

    private String getMovieRelease(String filme){
        int indiceInferior = filme.indexOf("\"release_date\":");
        int indiceSuperior = filme.lastIndexOf(",\"title\":");
        String retorno = filme.substring(indiceInferior, indiceSuperior);
        retorno = retorno.substring(retorno.indexOf(":")+1, retorno.length());
        return retorno;
    }

    private String getMovieAverage(String filme){
        int indiceInferior = filme.indexOf("\"vote_average\":");
        int indiceSuperior = filme.lastIndexOf(",\"vote_count\":");
        String retorno = filme.substring(indiceInferior, indiceSuperior);
        retorno = retorno.substring(retorno.indexOf(":")+1, retorno.length());
        return retorno;
    }

}
