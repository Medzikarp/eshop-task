package com.example.eshop.product.rest.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class RestApiValidationError extends RestApiSubError {

    private String object;

    private String field;

    private Object rejectedValue;

    private String message;

    RestApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
