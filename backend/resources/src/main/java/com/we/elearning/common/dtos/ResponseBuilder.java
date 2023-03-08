package com.we.elearning.common.dtos;

import java.util.Map;

public class ResponseBuilder {

    public static ApiResponse success() {
        return ApiResponse.builder()
                .data(null)
                .success(true)
                .build();
    }

    public static ApiResponse success(Object data) {
        return ApiResponse.builder()
                .data(data)
                .success(true)
                .build();
    }

    public static ApiResponse success(Object data, String message) {
        return ApiResponse.builder()
                .data(data)
                .message(message)
                .success(true)
                .build();
    }

    public static ApiResponse success(Object data, String message, Map<String, Object> meta) {
        return ApiResponse.builder()
                .data(data)
                .message(message)
                .success(true)
                .meta(meta)
                .build();
    }

    public static ApiResponse error() {
        return ApiResponse.builder()
                .error(null)
                .success(false)
                .build();
    }

    public static ApiResponse error(Object error) {
        return ApiResponse.builder()
                .error(error)
                .success(false)
                .build();
    }

    public static ApiResponse error(Object error, String message) {
        return ApiResponse.builder()
                .error(error)
                .message(message)
                .success(false)
                .build();
    }

    public static ApiResponse error(Object error, String message, Map<String, Object> meta) {
        return ApiResponse.builder()
                .error(error)
                .message(message)
                .success(false)
                .meta(meta)
                .build();
    }
}
