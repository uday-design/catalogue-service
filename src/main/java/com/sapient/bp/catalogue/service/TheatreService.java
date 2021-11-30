package com.sapient.bp.catalogue.service;

import com.sapient.bp.catalogue.entity.Movie;
import com.sapient.bp.catalogue.entity.Theatre;

import java.util.List;

public interface TheatreService {

    List<Theatre> getTheatreByCity(Integer cityId);

    Theatre saveTheatre(Theatre theatre);

    void deleteTheatre(Theatre theatre);

    void loadMoviesInTheatre(Theatre theatre);

    List<Theatre> getTheatresForMovieAndCity(Integer movieId, Integer cityId);

    Theatre getTheatre(Integer id);

    List<Movie> getMoviesInTheatre(Theatre theatre);
}
