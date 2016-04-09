package com.zahari.primegen.errorhandling.validation;

import com.zahari.primegen.domain.PrimeGeneratorAlgo;
import com.zahari.primegen.domain.PrimesRequestDTO;
import com.zahari.primegen.errorhandling.errors.ValidationError;
import com.zahari.primegen.errorhandling.errors.ValidationException;
import com.zahari.primegen.errorhandling.errors.ValidationResult;

import java.util.Arrays;

/**
 * @author Zahari Dichev <zaharidichev@gmail.com>.
 */

@PayloadValidator
public class PrimeNumbersRequestValidator {

    private ValidationResult validationResult = new ValidationResult();


    private void doValidate(PrimesRequestDTO params) {

        if(params.getLimit() != null) {

            if(params.getLimit() < 0) {
                validationResult.addError(new ValidationError("The limit parameter should be positive", "PARAM_INVALID"));
            }
        } else {
            validationResult.addError(new ValidationError("The limit parameter is mandatory", "MISSING_PARAM"));

        }

        if(params.getAlgoType() != null) {
            if(Arrays.stream(PrimeGeneratorAlgo.values()).map(x -> x.toString()).noneMatch(x -> x.equals(params.getAlgoType()))) {
                validationResult.addError(new ValidationError("The algoType parameter should be one of {serial,parallel}", "PARAM_INVALID"));
            }
        } else {
            validationResult.addError(new ValidationError("The algoType parameter is mandatory", "MISSING_PARAM"));

        }

    }

    public void validate(PrimesRequestDTO request) {
        this.doValidate(request);
        if(this.validationResult.hasErrors()) {
            throw new ValidationException(this.validationResult);
        }
    }

}
