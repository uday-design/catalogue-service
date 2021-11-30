package com.sapient.bp.catalogue.mapper;

import com.sapient.bp.catalogue.dto.CityDTO;
import com.sapient.bp.catalogue.entity.City;
import com.sapient.bp.catalogue.util.BaseTestCase;
import com.sapient.bp.catalogue.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CityMapperTest extends BaseTestCase {

    @Test
    public void testCityToCityDTO() {
        CityMapperImpl mapper = new CityMapperImpl();
        CityDTO cityDTO = mapper.cityToCityDTO(TestUtil.getCity());

        Assertions.assertEquals(TestUtil.getCity().getId(), cityDTO.getId());
        Assertions.assertEquals(TestUtil.getCity().getName(), cityDTO.getName());
    }

    @Test
    public void testCityDTOToCity() {
        CityMapperImpl mapper = new CityMapperImpl();
        City city = mapper.cityDTOToCity(TestUtil.getCityDTO());

        Assertions.assertEquals(TestUtil.getCityDTO().getId(), city.getId());
        Assertions.assertEquals(TestUtil.getCityDTO().getName(), city.getName());
    }
}
