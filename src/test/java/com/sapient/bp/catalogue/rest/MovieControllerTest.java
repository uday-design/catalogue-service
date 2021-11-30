package com.sapient.bp.catalogue.rest;

import com.sapient.bp.catalogue.dto.MovieDTO;
import com.sapient.bp.catalogue.dto.ResponseDTO;
import com.sapient.bp.catalogue.dto.ResponseStatusEnum;
import com.sapient.bp.catalogue.mapper.MovieMapper;
import com.sapient.bp.catalogue.service.MovieService;
import com.sapient.bp.catalogue.util.BaseTestCase;
import com.sapient.bp.catalogue.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Set;

public class MovieControllerTest extends BaseTestCase {

    @Mock
    private MovieService movieService;

    @Mock
    private MovieMapper movieMapper;

    @InjectMocks
    private MovieController movieController;

    @Test
    public void testGetAllMoviesInCity() {
        Mockito.when(movieService.getAllMoviesInCity(Mockito.anyInt())).thenReturn(Collections.singleton(TestUtil.getMovie()));
        Mockito.when(movieMapper.getMovieDTOs(Mockito.any())).thenReturn(Collections.singleton(TestUtil.getMovieDTO()));

        ResponseEntity<ResponseDTO<Set<MovieDTO>>> response = movieController.getAllMoviesInCity(1);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseStatusEnum.SUCCESS, response.getBody().getStatus());
        Assertions.assertNull(response.getBody().getError());
        Assertions.assertEquals(TestUtil.getMovieDTO().getName(), response.getBody().getResult().toArray(new MovieDTO[1])[0].getName());
    }

}
