package com.sapient.bp.catalogue.service;

import com.sapient.bp.catalogue.dto.MovieInTheatreDTO;
import com.sapient.bp.catalogue.dto.SaveTheatreDTO;
import com.sapient.bp.catalogue.entity.City;
import com.sapient.bp.catalogue.entity.Movie;
import com.sapient.bp.catalogue.entity.MovieTheatre;
import com.sapient.bp.catalogue.entity.Theatre;
import com.sapient.bp.catalogue.exception.SystemException;
import com.sapient.bp.catalogue.mapper.SaveTheatreMapper;
import com.sapient.bp.catalogue.repository.MovieTheatreRepository;
import com.sapient.bp.catalogue.repository.TheatreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
    private TheatreAdapter theatreAdapter;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SaveTheatreMapper saveTheatreMapper;

    @Override
    public List<Theatre> getTheatreByCity(Integer cityId) {
        return theatreRepository.findByCityId(cityId);
    }

    @Override
    public Theatre saveTheatre(SaveTheatreDTO saveTheatreDTO, Integer cityId) {
        theatreAdapter.saveTheatreAdapterConfig(saveTheatreDTO);
        return theatreRepository.save(saveTheatreMapper.saveTheatreDTOToTheatre(saveTheatreDTO, cityId));
    }

    @Override
    public void deleteTheatre(Integer theatreId) {
        theatreAdapter.deleteTheatreAdapterConfig(theatreId);
        theatreRepository.delete(getTheatre(theatreId));
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
    public List<Movie> getMoviesInTheatre(Integer theatreId) {
        return movieTheatreRepository.findAllByTheatre(getTheatre(theatreId)).stream().map(MovieTheatre::getMovie).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void loadMoviesInTheatre(Integer theatreId) {
        Theatre theatre = getTheatre(theatreId);
        MovieInTheatreDTO movieInTheatreDTO = theatreAdapter.loadMovies(theatreId);
        List<MovieTheatre> movieInTheatres = new ArrayList<>();
        movieInTheatreDTO.getMovies().forEach(movie -> {
            Movie m = movieService.getMovieByName(movie.getName());
            if (m == null) {
                Movie newMovie = new Movie();
                newMovie.setName(movie.getName());
                m = movieService.saveMovie(newMovie);
            }
            MovieTheatre movieTheatre = new MovieTheatre();
            movieTheatre.setTheatre(theatre);
            movieTheatre.setMovie(m);
            movieInTheatres.add(movieTheatre);
        });
        movieTheatreRepository.deleteAllByTheatre(theatre);
        movieTheatreRepository.saveAll(movieInTheatres);
    }
}
