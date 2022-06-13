package com.quick_example.IMDB_Application;

public class Category_Skeleton {

    //variables
    private String genre;
    private String movie_titleCategory;
    private String movie_ratingCategory;
    private String movie_genreCategory;
    private String movie_descriptionCategory;
    private String movie_trailerCategory;
    private String movie_keywordsCategory;
    private String movie_releaseCategory;
    private String movie_imageCategory;
    private String movie_idCategory;

    //constructor
    public Category_Skeleton(String movie_title, String movie_rating, String movie_genre, String movie_image, String movie_release){
        this.movie_titleCategory = movie_title;
        this.movie_ratingCategory =  movie_rating;
        this.movie_genreCategory = movie_genre;
        this.movie_imageCategory = movie_image;
        this.movie_releaseCategory = movie_release;
    }

    public Category_Skeleton(String genre, String movie_titleCategory, String movie_ratingCategory, String movie_genreCategory, String movie_descriptionCategory, String movie_trailerCategory, String movie_keywordsCategory, String movie_releaseCategory, String movie_imageCategory, String movie_idCategory) {
        this.genre = genre;
        this.movie_titleCategory = movie_titleCategory;
        this.movie_ratingCategory = movie_ratingCategory;
        this.movie_genreCategory = movie_genreCategory;
        this.movie_descriptionCategory = movie_descriptionCategory;
        this.movie_trailerCategory = movie_trailerCategory;
        this.movie_keywordsCategory = movie_keywordsCategory;
        this.movie_releaseCategory = movie_releaseCategory;
        this.movie_imageCategory = movie_imageCategory;
        this.movie_idCategory = movie_idCategory;
    }

    //Getters and Setters

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Category_Skeleton(){

    }
    public Category_Skeleton(String genre){
        this.genre = genre;
    }

    public String getMovie_titleCategory() {
        return movie_titleCategory;
    }

    public void setMovie_titleCategory(String movie_titleCategory) {
        this.movie_titleCategory = movie_titleCategory;
    }

    public String getMovie_releaseCategory() {
        return movie_releaseCategory;
    }

    public void setMovie_releaseCategory(String movie_releaseCategory) {
        this.movie_releaseCategory = movie_releaseCategory;
    }

    public String getMovie_idCategory() {
        return movie_idCategory;
    }

    public void setMovie_idCategory(String movie_idCategory) {
        this.movie_idCategory = movie_idCategory;
    }

    public String getMovie_title() {
        return movie_titleCategory;
    }

    public String getMovie_ratingCategory() {
        return movie_ratingCategory;
    }

    public String getMovie_genreCategory() {
        return movie_genreCategory;
    }

    public String getMovie_imageCategory() {
        return movie_imageCategory;
    }

    public void setMovie_ratingCategory(String movie_ratingCategory) {
        this.movie_ratingCategory = movie_ratingCategory;
    }
    public void setMovie_imageCategory(String movie_imageCategory) {
        this.movie_imageCategory = movie_imageCategory;
    }

    public void setMovie_genreCategory(String movie_genreCategory) {
        this.movie_genreCategory = movie_genreCategory;
    }

    public void setMovie_title(String movie_title) {
        this.movie_titleCategory = movie_title;
    }

    public String getMovie_descriptionCategory() {
        return movie_descriptionCategory;
    }

    public void setMovie_descriptionCategory(String movie_descriptionCategory) {
        this.movie_descriptionCategory = movie_descriptionCategory;
    }

    public String getMovie_trailerCategory() {
        return movie_trailerCategory;
    }

    public void setMovie_trailerCategory(String movie_trailerCategory) {
        this.movie_trailerCategory = movie_trailerCategory;
    }

    public String getMovie_keywordsCategory() {
        return movie_keywordsCategory;
    }

    public void setMovie_keywordsCategory(String movie_keywordsCategory) {
        this.movie_keywordsCategory = movie_keywordsCategory;
    }

    @Override
    public String toString() {
        return "menu_skeleton{" +
                "movie_title='" + movie_titleCategory + '\'' +
                ", movie_rating='" + movie_ratingCategory + '\'' +
                ", movie_genre='" + movie_genreCategory + '\'' +
                ", movie_release='" + movie_releaseCategory + '\'' +
                ", movie_image=" + movie_imageCategory +
                ", movie_keywords=" + movie_keywordsCategory +
                ", movie_id=" + movie_idCategory +
                '}';
    }
}
