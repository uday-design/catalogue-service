package com.sapient.bp.catalogue.rest;

import com.sapient.bp.catalogue.dto.CityDTO;
import com.sapient.bp.catalogue.dto.ResponseDTO;
import com.sapient.bp.catalogue.dto.ResponseStatusEnum;
import com.sapient.bp.catalogue.exception.SystemException;
import com.sapient.bp.catalogue.mapper.CityMapper;
import com.sapient.bp.catalogue.service.CityService;
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
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/v1/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CityMapper cityMapper;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<CityDTO>>> getAll() {
        List<CityDTO> output = cityService.getAll().stream().map(city -> cityMapper.cityToCityDTO(city)).collect(Collectors.toList());
        return ResponseEntity.ok(ResponseDTO.<List<CityDTO>>builder().result(output).status(ResponseStatusEnum.SUCCESS).build());
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<CityDTO>> save(@RequestBody CityDTO cityDTO) {
        CityDTO output = cityMapper.cityToCityDTO(cityService.save(cityMapper.cityDTOToCity(cityDTO)));
        return ResponseEntity.ok(ResponseDTO.<CityDTO>builder().result(output).status(ResponseStatusEnum.SUCCESS).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<CityDTO>> getById(@PathVariable Integer id) throws SystemException {
        CityDTO output = cityMapper.cityToCityDTO(cityService.get(id));
        return ResponseEntity.ok(ResponseDTO.<CityDTO>builder().result(output).status(ResponseStatusEnum.SUCCESS).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<CityDTO>> delete(@PathVariable Integer id) {
        cityService.delete(cityService.get(id));
        return ResponseEntity.ok(ResponseDTO.<CityDTO>builder().status(ResponseStatusEnum.SUCCESS).build());
    }
}
