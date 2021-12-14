package com.example.pickafilm;

public class MovieContainer {

    private String name;
    private String year;
    private int rating;
    private String desc;
    private String genre;
    private String runtime;
    private int imgId; // Later to be implemented as container for movieposter.

    // constructor.
    public MovieContainer(String name, String year,  int rating, String desc, String genre, String runtime, int imgId) {
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.desc = desc;
        this.genre = genre;
        this.runtime = runtime;
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }


}