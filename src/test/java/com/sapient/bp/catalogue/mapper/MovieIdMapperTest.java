package com.sapient.bp.catalogue.mapper;

import com.sapient.bp.catalogue.service.MovieService;
import com.sapient.bp.catalogue.util.BaseTestCase;
import com.sapient.bp.catalogue.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class MovieIdMapperTest extends BaseTestCase {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieIdMapper movieIdMapper;

    @Test
    public void testMapMovieId() {

        Mockito.when(movieService.getMovieByName(Mockito.anyString())).thenReturn(TestUtil.getMovie());

        Integer returnValue = movieIdMapper.mapMovieId("Test");

        Assertions.assertEquals(TestUtil.getMovie().getId(), returnValue);
    }

    @Test
    public void testMapMovieIdNull() {
        Mockito.when(movieService.getMovieByName(Mockito.anyString())).thenReturn(null);

        Integer returnValue = movieIdMapper.mapMovieId("Test");

        Assertions.assertNull(returnValue);
    }

}
