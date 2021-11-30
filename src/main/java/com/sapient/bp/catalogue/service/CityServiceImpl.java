package com.sapient.bp.catalogue.service;

import com.sapient.bp.catalogue.entity.City;
import com.sapient.bp.catalogue.exception.SystemException;
import com.sapient.bp.catalogue.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City get(Integer id) throws SystemException {
        return cityRepository.findById(id).orElseThrow(() -> new SystemException("City with id " + id + " not found"));
    }

    @Override
    public City findByName(String name) {
        return cityRepository.findByNameIgnoreCase(name);
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public void delete(City city) {
        cityRepository.delete(city);
    }
}
