package com.sapient.bp.catalogue.service;

import com.sapient.bp.catalogue.entity.City;
import com.sapient.bp.catalogue.exception.SystemException;

import java.util.List;

public interface CityService {

    City save(City city);

    City get(Integer id) throws SystemException;

    City findByName(String name);

    List<City> getAll();

    void delete(City city);
}
