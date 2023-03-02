package com.we.elearning.common.dtos;

import java.util.Map;

public class ResponseBuilder {

    public static <D, E> ApiResponse<D, E> success() {
        return ApiResponse.<D, E>builder()
                .data(null)
                .success(true)
                .build();
    }
    public static <D, E> ApiResponse<D, E> success(D data) {
        return ApiResponse.<D, E>builder()
                .data(data)
                .success(true)
                .build();
    }

    public static <D, E> ApiResponse<D, E> success(D data, String message) {
        return ApiResponse.<D, E>builder()
                .data(data)
                .message(message)
                .success(true)
                .build();
    }

    public static <D, E> ApiResponse<D, E> success(D data, String message, Map<String, Object> meta) {
        return ApiResponse.<D, E>builder()
                .data(data)
                .message(message)
                .success(true)
                .meta(meta)
                .build();
    }

    public static <D, E> ApiResponse<D, E> error() {
        return ApiResponse.<D, E>builder()
                .error(null)
                .success(false)
                .build();
    }
    public static <D, E> ApiResponse<D, E> error(E error) {
        return ApiResponse.<D, E>builder()
                .error(error)
                .success(false)
                .build();
    }

    public static <D, E> ApiResponse<D, E> error(E error, String message) {
        return ApiResponse.<D, E>builder()
                .error(error)
                .message(message)
                .success(false)
                .build();
    }

    public static <D, E> ApiResponse<D, E> error(E error, String message, Map<String, Object> meta) {
        return ApiResponse.<D, E>builder()
                .error(error)
                .message(message)
                .success(false)
                .meta(meta)
                .build();
    }
}
