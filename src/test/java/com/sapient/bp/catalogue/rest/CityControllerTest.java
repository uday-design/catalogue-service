package com.sapient.bp.catalogue.rest;

import com.sapient.bp.catalogue.dto.CityDTO;
import com.sapient.bp.catalogue.dto.ResponseDTO;
import com.sapient.bp.catalogue.dto.ResponseStatusEnum;
import com.sapient.bp.catalogue.entity.City;
import com.sapient.bp.catalogue.mapper.CityMapper;
import com.sapient.bp.catalogue.service.CityService;
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

public class CityControllerTest extends BaseTestCase {

    @Mock
    private CityService cityService;

    @Mock
    private CityMapper cityMapper;

    @InjectMocks
    private CityController cityController;

    @Test
    public void testGetAll() {
        Mockito.when(cityService.getAll()).thenReturn(Collections.singletonList(TestUtil.getCity()));
        Mockito.when(cityMapper.cityToCityDTO(Mockito.any(City.class))).thenReturn(TestUtil.getCityDTO());

        ResponseEntity<ResponseDTO<List<CityDTO>>> response = cityController.getAll();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseStatusEnum.SUCCESS, response.getBody().getStatus());
        Assertions.assertNull(response.getBody().getError());
        Assertions.assertEquals(1, response.getBody().getResult().size());
        Assertions.assertEquals(TestUtil.getCity().getId(), response.getBody().getResult().get(0).getId());
    }

    @Test
    public void testSave() {
        Mockito.when(cityService.save(Mockito.any(City.class))).thenReturn(TestUtil.getCity());
        Mockito.when(cityMapper.cityToCityDTO(Mockito.any(City.class))).thenReturn(TestUtil.getCityDTO());
        Mockito.when(cityMapper.cityDTOToCity(Mockito.any(CityDTO.class))).thenReturn(TestUtil.getCity());

        ResponseEntity<ResponseDTO<CityDTO>> response = cityController.save(TestUtil.getCityDTO());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseStatusEnum.SUCCESS, response.getBody().getStatus());
        Assertions.assertNull(response.getBody().getError());
        Assertions.assertEquals(TestUtil.getCity().getId(), response.getBody().getResult().getId());
    }

    @Test
    public void testGetById() {
        Mockito.when(cityService.get(Mockito.anyInt())).thenReturn(TestUtil.getCity());
        Mockito.when(cityMapper.cityToCityDTO(Mockito.any(City.class))).thenReturn(TestUtil.getCityDTO());

        ResponseEntity<ResponseDTO<CityDTO>> response = cityController.getById(1);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseStatusEnum.SUCCESS, response.getBody().getStatus());
        Assertions.assertNull(response.getBody().getError());
        Assertions.assertEquals(TestUtil.getCity().getId(), response.getBody().getResult().getId());
    }

    @Test
    public void testDelete() {
        Mockito.doNothing().when(cityService).delete(Mockito.any(City.class));

        ResponseEntity<ResponseDTO<CityDTO>> response = cityController.delete(1);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(ResponseStatusEnum.SUCCESS, response.getBody().getStatus());
        Assertions.assertNull(response.getBody().getError());
    }
}
