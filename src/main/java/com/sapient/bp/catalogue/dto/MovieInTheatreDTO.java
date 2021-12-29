package com.sapient.bp.catalogue.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MovieInTheatreDTO {

    private Integer theatreId;

    private List<MovieDTO> movies;
}
