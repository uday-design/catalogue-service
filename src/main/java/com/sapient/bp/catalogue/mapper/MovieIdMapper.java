package com.sapient.bp.catalogue.mapper;

import com.sapient.bp.catalogue.service.MovieService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public class MovieIdMapper {

    @Autowired
    private MovieService movieService;

    public Integer mapMovieId(String name) {
        return movieService.getMovieByName(name) != null ? movieService.getMovieByName(name).getId() : null;
    }
}
