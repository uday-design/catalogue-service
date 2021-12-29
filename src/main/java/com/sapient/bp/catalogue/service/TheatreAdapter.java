package com.sapient.bp.catalogue.service;

import com.sapient.bp.catalogue.dto.MovieInTheatreDTO;
import com.sapient.bp.catalogue.dto.SaveTheatreDTO;
import com.sapient.bp.catalogue.exception.SystemException;

public interface TheatreAdapter {
    void saveTheatreAdapterConfig(SaveTheatreDTO saveTheatreDTO) throws SystemException;

    void deleteTheatreAdapterConfig(Integer theatreId);

    MovieInTheatreDTO loadMovies(Integer theatreId) throws SystemException;

    void setTheatreAdapterURL(String theatreAdapterURL);
}
