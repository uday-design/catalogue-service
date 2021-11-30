package com.sapient.bp.catalogue.mapper;

import com.sapient.bp.catalogue.entity.City;
import com.sapient.bp.catalogue.service.CityService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public class CityIdMapper {

    @Autowired
    private CityService cityService;

    public City mapCityId(Integer cityId) {
        return cityService.get(cityId);
    }
}
