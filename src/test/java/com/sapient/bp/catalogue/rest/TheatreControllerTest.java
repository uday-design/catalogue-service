package com.sapient.bp.catalogue.rest;

import com.sapient.bp.catalogue.dto.ResponseDTO;
import com.sapient.bp.catalogue.dto.ResponseStatusEnum;
import com.sapient.bp.catalogue.dto.SaveTheatreDTO;
import com.sapient.bp.catalogue.dto.TheatreDTO;
import com.sapient.bp.catalogue.entity.Theatre;
import com.sapient.bp.catalogue.mapper.MovieMapper;
import com.sapient.bp.catalogue.mapper.SaveTheatreMapper;
import com.sapient.bp.catalogue.mapper.TheatreMapper;
import com.sapient.bp.catalogue.service.TheatreService;
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
import java.util.List;

public class TheatreControllerTest extends BaseTestCase {

    @Mock
    private TheatreService theatreService;

    @Mock
    private MovieMapper movieMapper;

    @Mock
    private TheatreMapper theatreMapper;

    @Mock
    private SaveTheatreMapper saveTheatreMapper;

    @InjectMocks
    private TheatreController theatreController;

    @Test
    public void testGetAllTheatresForMovieInCity() {
        Mockito.when(theatreService.getTheatresForMovieAndCity(Mockito.anyInt(), Mockito.anyInt())).thenReturn(Collections.singletonList(TestUtil.getTheatre()));
        Mockito.when(theatreMapper.getTheatreDTOs(Mockito.any())).thenReturn(Collections.singletonList(TestUtil.getTheatreDTO()));

        ResponseEntity<ResponseDTO<List<TheatreDTO>>> response = theatreController.getAllTheatresForMovieInCity(1, 1);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseStatusEnum.SUCCESS, response.getBody().getStatus());
        Assertions.assertNull(response.getBody().getError());
        Assertions.assertEquals(TestUtil.getTheatreDTO().getName(), response.getBody().getResult().get(0).getName());
    }

    @Test
    public void testGetAllTheatresInCity() {
        Mockito.when(theatreService.getTheatreByCity(Mockito.anyInt())).thenReturn(Collections.singletonList(TestUtil.getTheatre()));
        Mockito.when(theatreMapper.getTheatreDTOs(Mockito.any())).thenReturn(Collections.singletonList(TestUtil.getTheatreDTO()));

        ResponseEntity<ResponseDTO<List<TheatreDTO>>> response = theatreController.getAllTheatresInCity(1);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseStatusEnum.SUCCESS, response.getBody().getStatus());
        Assertions.assertNull(response.getBody().getError());
        Assertions.assertEquals(TestUtil.getTheatreDTO().getName(), response.getBody().getResult().get(0).getName());
    }

    @Test
    public void testSaveTheatre() {
        Mockito.when(theatreService.saveTheatre(Mockito.any(SaveTheatreDTO.class), Mockito.anyInt())).thenReturn(TestUtil.getTheatre());
        Mockito.when(theatreMapper.theatreToTheatreDTO(Mockito.any(Theatre.class))).thenReturn(TestUtil.getTheatreDTO());
        Mockito.when(saveTheatreMapper.saveTheatreDTOToTheatre(Mockito.any(SaveTheatreDTO.class), Mockito.anyInt())).thenReturn(TestUtil.getTheatre());

        ResponseEntity<ResponseDTO<TheatreDTO>> response = theatreController.saveTheatre(TestUtil.getSaveTheatreDTO(), 1);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseStatusEnum.SUCCESS, response.getBody().getStatus());
        Assertions.assertNull(response.getBody().getError());
        Assertions.assertEquals(TestUtil.getTheatreDTO().getName(), response.getBody().getResult().getName());
    }

    @Test
    public void testDeleteTheatre() {
        Mockito.when(theatreService.getTheatre(Mockito.anyInt())).thenReturn(TestUtil.getTheatre());
        Mockito.doNothing().when(theatreService).deleteTheatre(Mockito.anyInt());

        ResponseEntity<ResponseDTO<Object>> response = theatreController.deleteTheatre(1);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseStatusEnum.SUCCESS, response.getBody().getStatus());
        Assertions.assertNull(response.getBody().getError());
    }

    @Test
    public void testLoadMovies() {
        Mockito.when(theatreService.getTheatre(Mockito.anyInt())).thenReturn(TestUtil.getTheatre());
        Mockito.doNothing().when(theatreService).loadMoviesInTheatre(Mockito.anyInt());

        ResponseEntity<ResponseDTO<Object>> response = theatreController.loadMovies(1);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseStatusEnum.SUCCESS, response.getBody().getStatus());
        Assertions.assertNull(response.getBody().getError());
    }

}
