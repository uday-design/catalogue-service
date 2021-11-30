package com.sapient.bp.catalogue.service;

import com.sapient.bp.catalogue.entity.City;
import com.sapient.bp.catalogue.exception.SystemException;
import com.sapient.bp.catalogue.repository.CityRepository;
import com.sapient.bp.catalogue.util.BaseTestCase;
import com.sapient.bp.catalogue.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CityServiceTest extends BaseTestCase {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService = new CityServiceImpl();

    @Test
    public void testSave() {
        Mockito.when(cityRepository.save(Mockito.any(City.class))).thenReturn(TestUtil.getCity());

        City city = cityService.save(TestUtil.getCity());

        Assertions.assertEquals(TestUtil.getCity().getId(), city.getId());
    }

    @Test
    public void testGet() {
        Mockito.when(cityRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(TestUtil.getCity()));

        City city = cityService.get(1);

        Assertions.assertEquals(TestUtil.getCity().getId(), city.getId());
        Assertions.assertEquals(TestUtil.getCity().getName(), city.getName());
    }

    @Test
    public void testGetNull() {
        Mockito.when(cityRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(SystemException.class, () -> {
            City city = cityService.get(1);
        });

        Assertions.assertTrue(exception.getMessage().contains("City with id 1 not found"));
    }

    @Test
    public void testFindByName() {
        Mockito.when(cityRepository.findByNameIgnoreCase(Mockito.anyString())).thenReturn(TestUtil.getCity());

        City city = cityService.findByName("Test");

        Assertions.assertEquals(TestUtil.getCity().getId(), city.getId());
        Assertions.assertEquals(TestUtil.getCity().getName(), city.getName());
    }

    @Test
    public void testGetAll() {
        Mockito.when(cityRepository.findAll()).thenReturn(Collections.singletonList(TestUtil.getCity()));

        List<City> cities = cityService.getAll();

        Assertions.assertEquals(1, cities.size());
        Assertions.assertEquals(TestUtil.getCity().getId(), cities.get(0).getId());
        Assertions.assertEquals(TestUtil.getCity().getName(), cities.get(0).getName());
    }

    @Test
    public void testDelete() {
        Mockito.doNothing().when(cityRepository).delete(Mockito.any(City.class));

        cityService.delete(TestUtil.getCity());

        Mockito.verify(cityRepository, Mockito.times(1)).delete(Mockito.any(City.class));

    }
}
