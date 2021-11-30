package com.sapient.bp.catalogue.mapper;

import com.sapient.bp.catalogue.dto.MovieDTO;
import com.sapient.bp.catalogue.entity.Movie;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(uses = {MovieIdMapper.class}, componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface MovieMapper {

    @Mapping(source = "name", target = "name")
    MovieDTO movieToMovieDTO(Movie movie);

    Set<MovieDTO> getMovieDTOs(Set<Movie> movies);
}
