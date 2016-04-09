package com.zahari.primegen.errorhandling.validation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Zahari Dichev <zaharidichev@gmail.com>.
 */


@JsonFormat(
        shape = JsonFormat.Shape.OBJECT
)
@JsonPropertyOrder({"error", "message"})
public class ValidationError implements IError {

    @JsonProperty("error")
    private final String error;
    @JsonProperty("message")
    private final String message;

    public ValidationError(String message, String error) {
        this.message = message;
        this.error = error;
    }

    public String getError() {
        return this.message;
    }

    public String getMessage() {
        return this.error;
    }
}