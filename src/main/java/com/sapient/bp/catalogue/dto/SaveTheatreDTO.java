package com.sapient.bp.catalogue.dto;

import lombok.Data;

@Data
public class SaveTheatreDTO {

    private Integer id;

    private String name;

    private String movieAPI;

    private String movieDetailsAPI;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovieAPI() {
        return movieAPI;
    }

    public void setMovieAPI(String movieAPI) {
        this.movieAPI = movieAPI;
    }

    public String getMovieDetailsAPI() {
        return movieDetailsAPI;
    }

    public void setMovieDetailsAPI(String movieDetailsAPI) {
        this.movieDetailsAPI = movieDetailsAPI;
    }
}
