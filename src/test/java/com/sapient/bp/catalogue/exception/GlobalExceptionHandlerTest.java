package com.sapient.bp.catalogue.exception;

import com.sapient.bp.catalogue.dto.ResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public class GlobalExceptionHandlerTest {

    @Test
    public void testHandleConflict() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        WebRequest webRequest = Mockito.mock(WebRequest.class);
        Mockito.doNothing().when(webRequest).setAttribute(Mockito.anyString(), Mockito.any(RuntimeException.class), Mockito.anyInt());

        ResponseEntity<Object> response = handler.handleConflict(new SystemException("Test Exception"), webRequest);

        Assertions.assertEquals("500", ((ResponseDTO<Object>) response.getBody()).getError().getCode());
    }
}
