package com.sapient.bp.catalogue.service;

import com.sapient.bp.catalogue.dto.SaveTheatreDTO;
import com.sapient.bp.catalogue.entity.City;
import com.sapient.bp.catalogue.entity.Movie;
import com.sapient.bp.catalogue.entity.Theatre;
import com.sapient.bp.catalogue.exception.SystemException;
import com.sapient.bp.catalogue.mapper.SaveTheatreMapper;
import com.sapient.bp.catalogue.repository.MovieTheatreRepository;
import com.sapient.bp.catalogue.repository.TheatreRepository;
import com.sapient.bp.catalogue.util.BaseTestCase;
import com.sapient.bp.catalogue.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TheatreServiceTest extends BaseTestCase {

    @Mock
    private TheatreRepository theatreRepository;

    @Mock
    private MovieService movieService;

    @Mock
    private CityService cityService;

    @Mock
    private MovieTheatreRepository movieTheatreRepository;

    @Mock
    private TheatreAdapter theatreAdapter;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private SaveTheatreMapper saveTheatreMapper;

    @InjectMocks
    private TheatreService theatreService = new TheatreServiceImpl();

    @Test
    public void testSaveTheatre() {
        Mockito.when(theatreRepository.save(Mockito.any(Theatre.class))).thenReturn(TestUtil.getTheatre());
        Mockito.doNothing().when(theatreAdapter).saveTheatreAdapterConfig(Mockito.any(SaveTheatreDTO.class));
        Mockito.when(saveTheatreMapper.saveTheatreDTOToTheatre(Mockito.any(SaveTheatreDTO.class), Mockito.anyInt())).thenReturn(TestUtil.getTheatre());

        Theatre theatre = theatreService.saveTheatre(TestUtil.getSaveTheatreDTO(), 1);

        Assertions.assertEquals(TestUtil.getTheatre().getId(), theatre.getId());
    }

    @Test
    public void testDelete() {
        Mockito.when(theatreRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(TestUtil.getTheatre()));
        Mockito.doNothing().when(theatreRepository).delete(Mockito.any(Theatre.class));
        Mockito.doNothing().when(theatreAdapter).deleteTheatreAdapterConfig(Mockito.anyInt());

        theatreService.deleteTheatre(1);

        Mockito.verify(theatreRepository, Mockito.times(1)).delete(Mockito.any(Theatre.class));

    }

    @Test
    public void testGetTheatre() {
        Mockito.when(theatreRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(TestUtil.getTheatre()));

        Theatre theatre = theatreService.getTheatre(1);

        Assertions.assertEquals(TestUtil.getTheatre().getId(), theatre.getId());
        Assertions.assertEquals(TestUtil.getTheatre().getName(), theatre.getName());
    }

    @Test
    public void testGetNull() {
        Mockito.when(theatreRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(SystemException.class, () -> {
            Theatre theatre = theatreService.getTheatre(1);
        });

        Assertions.assertTrue(exception.getMessage().contains("Theatre not found!"));
    }

    @Test
    public void testGetTheatreByCity() {
        Mockito.when(theatreRepository.findByCityId(Mockito.anyInt())).thenReturn(Collections.singletonList(TestUtil.getTheatre()));

        List<Theatre> theatres = theatreService.getTheatreByCity(1);

        Assertions.assertEquals(TestUtil.getTheatre().getId(), theatres.get(0).getId());
        Assertions.assertEquals(TestUtil.getTheatre().getName(), theatres.get(0).getName());
    }

    @Test
    public void testGetTheatresForMovieAndCity() {
        Mockito.when(movieService.getMovieById(Mockito.anyInt())).thenReturn(TestUtil.getMovie());
        Mockito.when(cityService.get(Mockito.anyInt())).thenReturn(TestUtil.getCity());
        Mockito.when(movieTheatreRepository.findAllByMovieAndTheatreCity(Mockito.any(Movie.class), Mockito.any(City.class))).thenReturn(Collections.singletonList(TestUtil.getMovieTheatre()));

        List<Theatre> theatres = theatreService.getTheatresForMovieAndCity(1, 1);

        Assertions.assertEquals(TestUtil.getTheatre().getId(), theatres.get(0).getId());
        Assertions.assertEquals(TestUtil.getTheatre().getName(), theatres.get(0).getName());
    }

    @Test
    public void testGetMoviesInTheatre() {
        Mockito.when(theatreRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(TestUtil.getTheatre()));
        Mockito.when(movieService.getMovieById(Mockito.anyInt())).thenReturn(TestUtil.getMovie());
        Mockito.when(cityService.get(Mockito.anyInt())).thenReturn(TestUtil.getCity());
        Mockito.when(movieTheatreRepository.findAllByTheatre(Mockito.any(Theatre.class))).thenReturn(Collections.singletonList(TestUtil.getMovieTheatre()));

        List<Movie> movies = theatreService.getMoviesInTheatre(1);

        Assertions.assertEquals(TestUtil.getMovie().getId(), movies.get(0).getId());
        Assertions.assertEquals(TestUtil.getMovie().getName(), movies.get(0).getName());
    }

    @Test
    public void testLoadMoviesInTheatre() {
        Mockito.when(theatreAdapter.loadMovies(Mockito.anyInt())).thenReturn(TestUtil.getMovieInTheatreDTO());
        Mockito.when(theatreRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(TestUtil.getTheatre()));
        Mockito.doNothing().when(movieTheatreRepository).deleteAllByTheatre(Mockito.any(Theatre.class));
        Mockito.when(movieService.getMovieByName(Mockito.anyString())).thenReturn(TestUtil.getMovie());
        Mockito.when(movieService.saveMovie(Mockito.any(Movie.class))).thenReturn(TestUtil.getMovie());
        Mockito.when(movieTheatreRepository.saveAll(Mockito.any(List.class))).thenReturn(null);

        theatreService.loadMoviesInTheatre(1);

        Mockito.verify(movieTheatreRepository, Mockito.times(1)).deleteAllByTheatre(Mockito.any(Theatre.class));
        Mockito.verify(movieService, Mockito.times(1)).getMovieByName(Mockito.anyString());
        Mockito.verify(movieService, Mockito.times(0)).saveMovie(Mockito.any(Movie.class));
        Mockito.verify(movieTheatreRepository, Mockito.times(1)).saveAll(Mockito.any(List.class));

    }

    @Test
    public void testLoadMoviesInTheatreNewMovie() {
        Mockito.when(theatreAdapter.loadMovies(Mockito.anyInt())).thenReturn(TestUtil.getMovieInTheatreDTO());
        Mockito.when(theatreRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(TestUtil.getTheatre()));
        Mockito.doNothing().when(movieTheatreRepository).deleteAllByTheatre(Mockito.any(Theatre.class));
        Mockito.when(movieService.getMovieByName(Mockito.anyString())).thenReturn(null);
        Mockito.when(movieService.saveMovie(Mockito.any(Movie.class))).thenReturn(TestUtil.getMovie());
        Mockito.when(movieTheatreRepository.saveAll(Mockito.any(List.class))).thenReturn(null);

        theatreService.loadMoviesInTheatre(1);

        Mockito.verify(movieTheatreRepository, Mockito.times(1)).deleteAllByTheatre(Mockito.any(Theatre.class));
        Mockito.verify(movieService, Mockito.times(1)).getMovieByName(Mockito.anyString());
        Mockito.verify(movieService, Mockito.times(1)).saveMovie(Mockito.any(Movie.class));
        Mockito.verify(movieTheatreRepository, Mockito.times(1)).saveAll(Mockito.any(List.class));

    }
}
