package com.sapient.bp.catalogue.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO<T> {
    private ResponseStatusEnum status;
    private T result;
    private ErrorDTO error;
}
