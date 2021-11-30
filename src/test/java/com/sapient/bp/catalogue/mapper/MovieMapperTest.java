package com.sapient.bp.catalogue.mapper;

import com.sapient.bp.catalogue.dto.MovieDTO;
import com.sapient.bp.catalogue.util.BaseTestCase;
import com.sapient.bp.catalogue.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

public class MovieMapperTest extends BaseTestCase {

    @Test
    public void testMovieToMovieDTO() {
        MovieMapper mapper = new MovieMapperImpl();
        MovieDTO movie = mapper.movieToMovieDTO(TestUtil.getMovie());

        Assertions.assertEquals(TestUtil.getMovie().getName(), movie.getName());
    }

    @Test
    public void testGetMovieDTOs() {
        MovieMapper mapper = new MovieMapperImpl();
        Set<MovieDTO> movieDTOS = mapper.getMovieDTOs(Collections.singleton(TestUtil.getMovie()));

        Assertions.assertEquals(TestUtil.getMovie().getName(), movieDTOS.toArray(new MovieDTO[1])[0].getName());
    }
}
