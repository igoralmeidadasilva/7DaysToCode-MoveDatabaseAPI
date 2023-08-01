package com.tmdb.model;

public class Movie implements Content{
    
    private String id;
    private String title;
    private String overview;
    private String urlImage;
    private String year;
    private String rating;
    
    public Movie() {}

    public Movie(String id, String title, String overview, String urlImage, String year, String rating) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.urlImage = urlImage;
        this.year = year;
        this.rating = rating;
    }

    
    public String getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    @Override
    public String getUrlImage() {
        return urlImage;
    }

    @Override
    public String getYear() {
        return year;
    }

    @Override
    public String getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "id=" + id + ", title=" + title + "\noverview=" + overview + "\nposter=" + urlImage + "\nrelease="
                + year + ", average=" + rating;
    }

}
