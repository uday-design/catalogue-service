package com.sapient.bp.catalogue.mapper;

import com.sapient.bp.catalogue.entity.Theatre;
import com.sapient.bp.catalogue.util.BaseTestCase;
import com.sapient.bp.catalogue.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class SaveTheatreMapperTest extends BaseTestCase {

    @Mock
    private CityIdMapper cityIdMapper;

    @InjectMocks
    private SaveTheatreMapper mapper = new SaveTheatreMapperImpl();

    @Test
    public void testSaveTheatreDTOToTheatre() {
        Mockito.when(cityIdMapper.mapCityId(Mockito.anyInt())).thenReturn(TestUtil.getCity());

        Theatre theatre = mapper.saveTheatreDTOToTheatre(TestUtil.getSaveTheatreDTO(), 1);

        Assertions.assertEquals(TestUtil.getSaveTheatreDTO().getName(), theatre.getName());
    }

}
