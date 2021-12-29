package com.sapient.bp.catalogue.service;

import com.sapient.bp.catalogue.entity.Movie;
import com.sapient.bp.catalogue.exception.SystemException;
import com.sapient.bp.catalogue.repository.MovieRepository;
import com.sapient.bp.catalogue.util.BaseTestCase;
import com.sapient.bp.catalogue.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class MovieServiceTest extends BaseTestCase {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private TheatreService theatreService;

    @InjectMocks
    private MovieService movieService = new MovieServiceImpl();

    @Test
    public void testSaveMovie() {
        Mockito.when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(TestUtil.getMovie());

        Movie movie = movieService.saveMovie(TestUtil.getMovie());

        Assertions.assertEquals(TestUtil.getMovie().getId(), movie.getId());
    }

    @Test
    public void testGetMovieById() {
        Mockito.when(movieRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(TestUtil.getMovie()));

        Movie movie = movieService.getMovieById(1);

        Assertions.assertEquals(TestUtil.getMovie().getId(), movie.getId());
        Assertions.assertEquals(TestUtil.getMovie().getName(), movie.getName());
    }

    @Test
    public void testGetNull() {
        Mockito.when(movieRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(SystemException.class, () -> {
            Movie movie = movieService.getMovieById(1);
        });

        Assertions.assertTrue(exception.getMessage().contains("Movie not found!"));
    }

    @Test
    public void testGetMovieByName() {
        Mockito.when(movieRepository.findByNameIgnoreCase(Mockito.anyString())).thenReturn(TestUtil.getMovie());

        Movie movie = movieService.getMovieByName("Test");

        Assertions.assertEquals(TestUtil.getMovie().getId(), movie.getId());
        Assertions.assertEquals(TestUtil.getMovie().getName(), movie.getName());
    }

    @Test
    public void testGetAllMoviesInCity() {
        Mockito.when(theatreService.getTheatreByCity(Mockito.anyInt())).thenReturn(Collections.singletonList(TestUtil.getTheatre()));
        Mockito.when(theatreService.getMoviesInTheatre(Mockito.anyInt())).thenReturn(Collections.singletonList(TestUtil.getMovie()));

        Set<Movie> movies = movieService.getAllMoviesInCity(1);

        Assertions.assertEquals(1, movies.size());
        Assertions.assertEquals(TestUtil.getMovie().getId(), movies.toArray(new Movie[movies.size()])[0].getId());
        Assertions.assertEquals(TestUtil.getMovie().getName(), movies.toArray(new Movie[movies.size()])[0].getName());
    }
}
