package com.we.elearning.common.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Builder
@NoArgsConstructor
@Data
public class ApiResponse<D,E> {
    @Builder
    public ApiResponse(D data, E error, String message, boolean success, Map<String, Object> meta) {
        this.data = data;
        this.error = error;
        this.message = message;
        this.success = success;
        if(Objects.isNull(meta)){
            meta = new HashMap<>();
        }
        this.meta = meta;
    }
    private D data;
    private E error;
    private String message;
    private boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> meta;
}
