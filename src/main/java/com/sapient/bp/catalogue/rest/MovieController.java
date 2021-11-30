package com.sapient.bp.catalogue.rest;

import com.sapient.bp.catalogue.dto.MovieDTO;
import com.sapient.bp.catalogue.dto.ResponseDTO;
import com.sapient.bp.catalogue.dto.ResponseStatusEnum;
import com.sapient.bp.catalogue.mapper.MovieMapper;
import com.sapient.bp.catalogue.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/v1/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieMapper movieMapper;

    @GetMapping("/city/{cityId}")
    public ResponseEntity<ResponseDTO<Set<MovieDTO>>> getAllMoviesInCity(@PathVariable Integer cityId) {
        Set<MovieDTO> movies = movieMapper.getMovieDTOs(movieService.getAllMoviesInCity(cityId));
        return ResponseEntity.ok(ResponseDTO.<Set<MovieDTO>>builder().result(movies).status(ResponseStatusEnum.SUCCESS).build());
    }
}
