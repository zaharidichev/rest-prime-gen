package com.zahari.primegen.errorhandling.errors;

/**
 * @author Zahari Dichev <zaharidichev@gmail.com>.
 */
public class ValidationException extends RuntimeException {
    private ValidationResult validationResult;

    private ValidationException() {
        this.validationResult = new ValidationResult();
    }

    public ValidationException(ValidationError validationError) {
        this();
        this.validationResult.addError(validationError);
    }

    public ValidationException(ValidationResult validationResult) {
        this.validationResult = validationResult;
    }

    public ValidationResult getValidationResult() {
        return this.validationResult;
    }

}
