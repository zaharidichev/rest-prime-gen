package com.zahari.primegen.errorhandling.errors;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Zahari Dichev <zaharidichev@gmail.com>.
 */

public class ServerError implements IError {
    public static final ServerError UNKNOWN_ERROR = new ServerError("UNKNOWN_ERROR", "Unknown Issue with Service");
    @JsonProperty("error")
    private final String error;
    @JsonProperty("message")
    private final String message;

    public ServerError(String message, String error) {
        this.message = message;
        this.error = error;
    }

    public String getError() {
        return this.error;
    }

    public String getMessage() {
        return this.message;
    }

}
