package com.sapient.bp.catalogue.mapper;

import com.sapient.bp.catalogue.dto.TheatreDTO;
import com.sapient.bp.catalogue.entity.Theatre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TheatreMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "city.name", target = "city")
    TheatreDTO theatreToTheatreDTO(Theatre theatre);

    List<TheatreDTO> getTheatreDTOs(List<Theatre> theatres);
}
