package com.sapient.bp.catalogue.util;

import com.sapient.bp.catalogue.dto.CityDTO;
import com.sapient.bp.catalogue.dto.MovieDTO;
import com.sapient.bp.catalogue.dto.MovieInTheatreDTO;
import com.sapient.bp.catalogue.dto.ResponseDTO;
import com.sapient.bp.catalogue.dto.SaveTheatreDTO;
import com.sapient.bp.catalogue.dto.TheatreDTO;
import com.sapient.bp.catalogue.entity.City;
import com.sapient.bp.catalogue.entity.Movie;
import com.sapient.bp.catalogue.entity.MovieTheatre;
import com.sapient.bp.catalogue.entity.Theatre;

import java.util.Collections;

public class TestUtil {

    public static City getCity() {
        City city = new City();
        city.setId(1);
        city.setName("Test");
        return city;
    }

    public static CityDTO getCityDTO() {
        CityDTO city = new CityDTO();
        city.setId(1);
        city.setName("Test");
        return city;
    }

    public static Movie getMovie() {
        Movie movie = new Movie();
        movie.setId(1);
        movie.setName("Test Movie");
        return movie;
    }

    public static MovieDTO getMovieDTO() {
        MovieDTO movie = new MovieDTO();
        movie.setName("Test Movie");
        return movie;
    }

    public static Theatre getTheatre() {
        Theatre theatre = new Theatre();
        theatre.setId(1);
        theatre.setName("Test Theatre");
        theatre.setCity(TestUtil.getCity());
        return theatre;
    }

    public static TheatreDTO getTheatreDTO() {
        TheatreDTO theatre = new TheatreDTO();
        theatre.setId(1);
        theatre.setName("Test Theatre");
        theatre.setCity(TestUtil.getCity().getName());
        return theatre;
    }

    public static SaveTheatreDTO getSaveTheatreDTO() {
        SaveTheatreDTO theatre = new SaveTheatreDTO();
        theatre.setId(1);
        theatre.setName("Test Theatre");
        return theatre;
    }

    public static MovieTheatre getMovieTheatre() {
        MovieTheatre theatre = new MovieTheatre();
        theatre.setId(1);
        theatre.setTheatre(TestUtil.getTheatre());
        theatre.setMovie(TestUtil.getMovie());
        return theatre;
    }

    public static MovieInTheatreDTO getMovieInTheatreDTO() {
        return MovieInTheatreDTO.builder().theatreId(1).movies(Collections.singletonList(getMovieDTO())).build();
    }

    public static ResponseDTO<MovieInTheatreDTO> getMovieInTheatreResponseDTO() {
        return ResponseDTO.<MovieInTheatreDTO>builder().result(getMovieInTheatreDTO()).build();
    }
}
