package com.sapient.bp.catalogue.service;

import com.sapient.bp.catalogue.dto.ResponseDTO;
import com.sapient.bp.catalogue.dto.SaveTheatreDTO;
import com.sapient.bp.catalogue.entity.Theatre;
import com.sapient.bp.catalogue.util.BaseTestCase;
import com.sapient.bp.catalogue.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TheatreAdapterTest extends BaseTestCase {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TheatreAdapter theatreAdapter = new TheatreAdapterImpl();

    @Test
    public void testSaveTheatreAdapterConfig() {
        theatreAdapter.setTheatreAdapterURL("TestURL");
        when(restTemplate.postForObject(anyString(), any(SaveTheatreDTO.class), any(ResponseDTO.class.getClass()))).thenReturn(TestUtil.getMovieInTheatreResponseDTO());

        theatreAdapter.saveTheatreAdapterConfig(new SaveTheatreDTO());

        verify(restTemplate, times(1)).postForObject(anyString(), any(SaveTheatreDTO.class), any(ResponseDTO.class.getClass()));
    }

    @Test
    public void testDeleteTheatreAdapterConfig() {
        theatreAdapter.setTheatreAdapterURL("TestURL");
        doNothing().when(restTemplate).delete(anyString(), anyInt());

        theatreAdapter.deleteTheatreAdapterConfig(1);

        verify(restTemplate, times(1)).delete(anyString(), anyInt());
    }

    @Test
    public void testLoadMovies() {
        theatreAdapter.setTheatreAdapterURL("TestURL");
        doNothing().when(restTemplate).delete(anyString(), anyInt());

        theatreAdapter.deleteTheatreAdapterConfig(1);

        verify(restTemplate, times(1)).delete(anyString(), anyInt());
    }
}
