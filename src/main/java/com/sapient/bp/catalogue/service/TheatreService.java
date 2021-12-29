package com.sapient.bp.catalogue.service;

import com.sapient.bp.catalogue.dto.SaveTheatreDTO;
import com.sapient.bp.catalogue.entity.Movie;
import com.sapient.bp.catalogue.entity.Theatre;

import java.util.List;

public interface TheatreService {

    List<Theatre> getTheatreByCity(Integer cityId);

    Theatre saveTheatre(SaveTheatreDTO theatre, Integer cityId);

    void deleteTheatre(Integer theatreId);

    List<Theatre> getTheatresForMovieAndCity(Integer movieId, Integer cityId);

    Theatre getTheatre(Integer id);

    List<Movie> getMoviesInTheatre(Integer theatreId);

    void loadMoviesInTheatre(Integer theatreId);
}
