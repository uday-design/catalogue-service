package com.sapient.bp.catalogue.service;

import com.sapient.bp.catalogue.dto.MovieDTO;
import com.sapient.bp.catalogue.entity.City;
import com.sapient.bp.catalogue.entity.Movie;
import com.sapient.bp.catalogue.entity.MovieTheatre;
import com.sapient.bp.catalogue.entity.Theatre;
import com.sapient.bp.catalogue.exception.SystemException;
import com.sapient.bp.catalogue.repository.MovieTheatreRepository;
import com.sapient.bp.catalogue.repository.TheatreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TheatreServiceImpl implements TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private CityService cityService;

    @Autowired
    private MovieTheatreRepository movieTheatreRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Theatre> getTheatreByCity(Integer cityId) {
        return theatreRepository.findByCityId(cityId);
    }

    @Override
    public Theatre saveTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    @Override
    public void deleteTheatre(Theatre theatre) {
        theatreRepository.delete(theatre);
    }

    @Transactional
    @Override
    public void loadMoviesInTheatre(Theatre theatre) {
        String moviesURL = theatre.getMovieAPI();
        try {
            MovieDTO[] movies = restTemplate.getForObject(moviesURL, MovieDTO[].class);
            movieTheatreRepository.deleteAllByTheatre(theatre);
            Arrays.stream(movies).forEach(m -> {
                Movie movie = movieService.getMovieByName(m.getName());
                if (movie == null) {
                    Movie newMovie = new Movie();
                    newMovie.setName(m.getName());
                    movie = movieService.saveMovie(newMovie);
                }
                MovieTheatre movieTheatre = new MovieTheatre();
                movieTheatre.setTheatre(theatre);
                movieTheatre.setMovie(movie);
                movieTheatreRepository.save(movieTheatre);
            });
        } catch (RestClientException e) {
            log.error("Unable to get movies using url " + moviesURL, e);
            throw new SystemException("Unable to get movies using url " + moviesURL, e);
        }
    }

    @Override
    public List<Theatre> getTheatresForMovieAndCity(Integer movieId, Integer cityId) {
        Movie movie = movieService.getMovieById(movieId);
        City city = cityService.get(cityId);
        return movieTheatreRepository.findAllByMovieAndTheatreCity(movie, city).stream().map(MovieTheatre::getTheatre).collect(Collectors.toList());
    }

    @Override
    public Theatre getTheatre(Integer id) {
        return theatreRepository.findById(id).orElseThrow(() -> new SystemException("Theatre not found!"));
    }

    @Override
    public List<Movie> getMoviesInTheatre(Theatre theatre) {
        return movieTheatreRepository.findAllByTheatre(theatre).stream().map(MovieTheatre::getMovie).collect(Collectors.toList());
    }
}
