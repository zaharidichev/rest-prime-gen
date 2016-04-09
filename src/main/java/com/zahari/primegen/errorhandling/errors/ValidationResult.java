package com.zahari.primegen.errorhandling.errors;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Zahari Dichev <zaharidichev@gmail.com>.
 */

public class ValidationResult {
    @JsonProperty("errors")
    private Set<ValidationError> errors;

    public ValidationResult() {
    }

    public void addError(ValidationError error) {
        if (this.errors == null) {
            this.errors = new HashSet();
        }

        this.errors.add(error);
    }

    public boolean hasErrors() {
        return this.errors != null;
    }

}