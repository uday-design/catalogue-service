package com.sapient.bp.catalogue.mapper;

import com.sapient.bp.catalogue.entity.City;
import com.sapient.bp.catalogue.service.CityService;
import com.sapient.bp.catalogue.util.BaseTestCase;
import com.sapient.bp.catalogue.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class CityIdMapperTest extends BaseTestCase {

    @Mock
    private CityService cityService;

    @InjectMocks
    private CityIdMapper cityIdMapper;

    @Test
    public void testMapCityId() {
        Mockito.when(cityService.get(Mockito.anyInt())).thenReturn(TestUtil.getCity());

        City returnValue = cityIdMapper.mapCityId(1);

        Assertions.assertEquals(TestUtil.getCity().getId(), returnValue.getId());
        Assertions.assertEquals(TestUtil.getCity().getName(), returnValue.getName());
    }

}
