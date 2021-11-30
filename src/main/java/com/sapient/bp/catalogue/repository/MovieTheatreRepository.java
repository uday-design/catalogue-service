package com.sapient.bp.catalogue.repository;

import com.sapient.bp.catalogue.entity.City;
import com.sapient.bp.catalogue.entity.Movie;
import com.sapient.bp.catalogue.entity.MovieTheatre;
import com.sapient.bp.catalogue.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieTheatreRepository extends JpaRepository<MovieTheatre, Integer> {

    boolean existsByMovieAndTheatre(Movie movie, Theatre theatre);

    List<MovieTheatre> findAllByMovie(Movie movie);

    List<MovieTheatre> findAllByMovieAndTheatreCity(Movie movie, City city);

    List<MovieTheatre> findAllByTheatre(Theatre theatre);

    void deleteAllByTheatre(Theatre theatre);
}
