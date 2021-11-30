package com.sapient.bp.catalogue.repository;

import com.sapient.bp.catalogue.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Integer> {
    List<Theatre> findByCityId(Integer cityId);
}
