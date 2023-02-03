package io.codercm.playground.common.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponsePayload<D,E> {
    private D data;
    private E error;
}
