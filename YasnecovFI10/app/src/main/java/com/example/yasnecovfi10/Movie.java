package com.example.yasnecovfi10;

public class Movie {
    private int _id;
    private String movie_title;
    private String movie_director;
    private String movie_year;
    private String movie_description;
    private String movie_poster;
    private String movie_length;

    public Movie(int _id, String movie_title, String movie_director, String movie_year, String movie_description, String movie_poster, String movie_length) {
        this._id = _id;
        this.movie_title = movie_title;
        this.movie_director = movie_director;
        this.movie_year = movie_year;
        this.movie_description = movie_description;
        this.movie_poster = movie_poster;
        this.movie_length = movie_length;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getMovie_director() {
        return movie_director;
    }

    public void setMovie_director(String movie_director) {
        this.movie_director = movie_director;
    }

    public String getMovie_year() {
        return movie_year;
    }

    public void setMovie_year(String movie_year) {
        this.movie_year = movie_year;
    }

    public String getMovie_description() {
        return movie_description;
    }

    public void setMovie_description(String movie_description) {
        this.movie_description = movie_description;
    }

    public String getMovie_poster() {
        return movie_poster;
    }

    public void setMovie_poster(String movie_poster) {
        this.movie_poster = movie_poster;
    }

    public String getMovie_length() {
        return movie_length;
    }

    public void setMovie_length(String movie_length) {
        this.movie_length = movie_length;
    }
}
