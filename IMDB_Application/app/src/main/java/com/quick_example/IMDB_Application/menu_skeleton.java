package com.quick_example.IMDB_Application;

public class menu_skeleton {
    //variables
    private String movie_title;
    private String movie_rating;
    private String movie_genre;
    private String movie_description;
    private String movie_trailer ;
    private String movie_keywords;

    public String getMovie_release() {
        return movie_release;
    }

    public void setMovie_release(String movie_release) {
        this.movie_release = movie_release;
    }

    private String movie_release;
    private String movie_image;

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    private String movie_id;

    //constructor
    public menu_skeleton(String movie_title, String movie_rating, String movie_genre, String movie_image, String movie_release){
        this.movie_title = movie_title;
        this.movie_rating =  movie_rating;
        this.movie_genre = movie_genre;
        this.movie_image = movie_image;
        this.movie_release = movie_release;
    }



    public menu_skeleton() {

    }

    //Getters and Setters

    public String getMovie_title() {
        return movie_title;
    }

    public String getMovie_rating() {
        return movie_rating;
    }

    public String getMovie_genre() {
        return movie_genre;
    }

    public String getMovie_image() {
        return movie_image;
    }

    public void setMovie_rating(String movie_rating) {
        this.movie_rating = movie_rating;
    }
    public void setMovie_image(String movie_image) {
        this.movie_image = movie_image;
    }

    public void setMovie_genre(String movie_genre) {
        this.movie_genre = movie_genre;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }


    @Override
    public String toString() {
        return "menu_skeleton{" +
                "movie_title='" + movie_title + '\'' +
                ", movie_rating='" + movie_rating + '\'' +
                ", movie_genre='" + movie_genre + '\'' +
                ", movie_release='" + movie_release + '\'' +
                ", movie_image=" + movie_image +
                ", movie_keywords=" + movie_keywords +
                ", movie_id=" + movie_id +
                '}';
    }

    public String getMovie_description() {
        return movie_description;
    }

    public void setMovie_description(String movie_description) {
        this.movie_description = movie_description;
    }

    public String getMovie_trailer() {
        return movie_trailer;
    }

    public void setMovie_trailer(String movie_trailer) {
        this.movie_trailer = movie_trailer;
    }

    public String getMovie_keywords() {
        return movie_keywords;
    }

    public void setMovie_keywords(String movie_keywords) {
        this.movie_keywords = movie_keywords;
    }
}
