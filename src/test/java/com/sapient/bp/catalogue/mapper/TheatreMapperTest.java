package com.sapient.bp.catalogue.mapper;

import com.sapient.bp.catalogue.dto.TheatreDTO;
import com.sapient.bp.catalogue.util.BaseTestCase;
import com.sapient.bp.catalogue.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class TheatreMapperTest extends BaseTestCase {

    @Test
    public void testTheatreToTheatreDTO() {
        TheatreMapper mapper = new TheatreMapperImpl();
        TheatreDTO theatre = mapper.theatreToTheatreDTO(TestUtil.getTheatre());

        Assertions.assertEquals(TestUtil.getTheatre().getName(), theatre.getName());
    }

    @Test
    public void testGetTheatreDTOs() {
        TheatreMapper mapper = new TheatreMapperImpl();
        List<TheatreDTO> movieDTOS = mapper.getTheatreDTOs(Collections.singletonList(TestUtil.getTheatre()));

        Assertions.assertEquals(TestUtil.getTheatre().getName(), movieDTOS.get(0).getName());
    }
}
