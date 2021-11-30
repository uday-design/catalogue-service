package com.sapient.bp.catalogue.rest;

import com.sapient.bp.catalogue.dto.ResponseDTO;
import com.sapient.bp.catalogue.dto.ResponseStatusEnum;
import com.sapient.bp.catalogue.dto.SaveTheatreDTO;
import com.sapient.bp.catalogue.dto.TheatreDTO;
import com.sapient.bp.catalogue.mapper.MovieMapper;
import com.sapient.bp.catalogue.mapper.SaveTheatreMapper;
import com.sapient.bp.catalogue.mapper.TheatreMapper;
import com.sapient.bp.catalogue.service.TheatreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/theatre")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private TheatreMapper theatreMapper;

    @Autowired
    private SaveTheatreMapper saveTheatreMapper;

    @GetMapping("/city/{cityId}/movie/{movieId}")
    public ResponseEntity<ResponseDTO<List<TheatreDTO>>> getAllTheatresForMovieInCity(@PathVariable Integer cityId, @PathVariable Integer movieId) {
        List<TheatreDTO> theatres = theatreMapper.getTheatreDTOs(theatreService.getTheatresForMovieAndCity(movieId, cityId));
        return ResponseEntity.ok(ResponseDTO.<List<TheatreDTO>>builder().result(theatres).status(ResponseStatusEnum.SUCCESS).build());
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<ResponseDTO<List<TheatreDTO>>> getAllTheatresInCity(@PathVariable Integer cityId) {
        List<TheatreDTO> theatres = theatreMapper.getTheatreDTOs(theatreService.getTheatreByCity(cityId));
        return ResponseEntity.ok(ResponseDTO.<List<TheatreDTO>>builder().result(theatres).status(ResponseStatusEnum.SUCCESS).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<TheatreDTO>> getTheatre(@PathVariable Integer id) {
        TheatreDTO theatreDTO = theatreMapper.theatreToTheatreDTO(theatreService.getTheatre(id));
        return ResponseEntity.ok(ResponseDTO.<TheatreDTO>builder().result(theatreDTO).status(ResponseStatusEnum.SUCCESS).build());
    }

    @PostMapping("/city/{cityId}")
    public ResponseEntity<ResponseDTO<TheatreDTO>> saveTheatre(@RequestBody SaveTheatreDTO theatre, @PathVariable Integer cityId) {
        TheatreDTO theatreDTO = theatreMapper.theatreToTheatreDTO(theatreService.saveTheatre(saveTheatreMapper.saveTheatreDTOToTheatre(theatre, cityId)));
        return ResponseEntity.ok(ResponseDTO.<TheatreDTO>builder().result(theatreDTO).status(ResponseStatusEnum.SUCCESS).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Object>> deleteTheatre(@PathVariable Integer theatreId) {
        theatreService.deleteTheatre(theatreService.getTheatre(theatreId));
        return ResponseEntity.ok(ResponseDTO.builder().result(null).status(ResponseStatusEnum.SUCCESS).build());
    }

    @PostMapping("/{theatreId}/load-movies")
    public ResponseEntity<ResponseDTO<Object>> loadMovies(@PathVariable Integer theatreId) {
        theatreService.loadMoviesInTheatre(theatreService.getTheatre(theatreId));
        return ResponseEntity.ok(ResponseDTO.builder().result(null).status(ResponseStatusEnum.SUCCESS).build());
    }
}
