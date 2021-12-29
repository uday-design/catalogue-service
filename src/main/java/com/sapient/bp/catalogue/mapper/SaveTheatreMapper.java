package com.sapient.bp.catalogue.mapper;

import com.sapient.bp.catalogue.dto.SaveTheatreDTO;
import com.sapient.bp.catalogue.entity.Theatre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = CityIdMapper.class, componentModel = "spring")
public interface SaveTheatreMapper {

    @Mapping(source = "saveTheatreDTO.id", target = "id")
    @Mapping(source = "saveTheatreDTO.name", target = "name")
    @Mapping(source = "cityId", target = "city")
    Theatre saveTheatreDTOToTheatre(SaveTheatreDTO saveTheatreDTO, Integer cityId);
}
