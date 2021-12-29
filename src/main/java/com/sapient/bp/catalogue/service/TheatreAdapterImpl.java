package com.sapient.bp.catalogue.service;

import com.sapient.bp.catalogue.dto.MovieInTheatreDTO;
import com.sapient.bp.catalogue.dto.ResponseDTO;
import com.sapient.bp.catalogue.dto.SaveTheatreDTO;
import com.sapient.bp.catalogue.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TheatreAdapterImpl implements TheatreAdapter {

    @Value("${theatreAdapterURL}")
    private String theatreAdapterURL;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void saveTheatreAdapterConfig(SaveTheatreDTO saveTheatreDTO) throws SystemException {
        ResponseDTO theatre = restTemplate.postForObject(theatreAdapterURL, saveTheatreDTO, ResponseDTO.class);
        if (theatre.getError() != null) {
            throw new SystemException("Unable to save theatre adapter config");
        }
    }

    @Override
    public void deleteTheatreAdapterConfig(Integer theatreId) {
        restTemplate.delete(theatreAdapterURL, theatreId);
    }

    @Override
    public MovieInTheatreDTO loadMovies(Integer theatreId) throws SystemException {
        ResponseDTO response = restTemplate.getForObject(theatreAdapterURL + "/" + theatreId, ResponseDTO.class);
        if (response.getError() == null) {
            throw new SystemException("Unable to load movies.");
        }
        return (MovieInTheatreDTO) response.getResult();
    }

    @Override
    public void setTheatreAdapterURL(String theatreAdapterURL) {
        this.theatreAdapterURL = theatreAdapterURL;
    }
}
