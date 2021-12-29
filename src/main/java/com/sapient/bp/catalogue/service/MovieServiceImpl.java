package com.sapient.bp.catalogue.service;

import com.sapient.bp.catalogue.entity.Movie;
import com.sapient.bp.catalogue.exception.SystemException;
import com.sapient.bp.catalogue.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheatreService theatreService;

    @Override
    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id).orElseThrow(() -> new SystemException("Movie not found!"));
    }

    @Override
    public Movie getMovieByName(String name) {
        return movieRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Set<Movie> getAllMoviesInCity(Integer cityId) {
        return theatreService.getTheatreByCity(cityId).stream().flatMap(t -> theatreService.getMoviesInTheatre(t.getId()).stream()).collect(Collectors.toSet());
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
