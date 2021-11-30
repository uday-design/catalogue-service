package com.sapient.bp.catalogue.repository;

import com.sapient.bp.catalogue.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    City findByNameIgnoreCase(String name);
}
