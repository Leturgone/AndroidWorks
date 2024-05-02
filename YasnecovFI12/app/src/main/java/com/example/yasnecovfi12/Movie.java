package com.example.yasnecovfi12;

public class Movie {
    private String movie_title;
    private String movie_year;

    public Movie(String movie_title, String movie_year) {
        this.movie_title = movie_title;
        this.movie_year = movie_year;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getMovie_year() {
        return movie_year;
    }

    public void setMovie_year(String movie_year) {
        this.movie_year = movie_year;
    }
}
