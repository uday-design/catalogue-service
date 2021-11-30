package com.sapient.bp.catalogue.mapper;

import com.sapient.bp.catalogue.dto.CityDTO;
import com.sapient.bp.catalogue.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CityMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "id", target = "id")
    CityDTO cityToCityDTO(City city);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "id", target = "id")
    City cityDTOToCity(CityDTO cityDTO);
}
