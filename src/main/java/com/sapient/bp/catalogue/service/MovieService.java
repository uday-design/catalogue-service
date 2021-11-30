package com.sapient.bp.catalogue.service;

import com.sapient.bp.catalogue.entity.Movie;

import java.util.Set;

public interface MovieService {

    Movie getMovieById(Integer id);

    Movie getMovieByName(String name);

    Set<Movie> getAllMoviesInCity(Integer cityId);

    Movie saveMovie(Movie movie);
}
