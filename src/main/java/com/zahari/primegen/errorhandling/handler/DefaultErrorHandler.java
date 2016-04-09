package com.zahari.primegen.errorhandling.handler;

import com.zahari.primegen.errorhandling.errors.ServerError;
import com.zahari.primegen.errorhandling.errors.ValidationException;
import com.zahari.primegen.errorhandling.errors.ValidationResult;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Zahari Dichev <zaharidichev@gmail.com>.
 */
@ControllerAdvice
public class DefaultErrorHandler {

    protected static final Logger logger = LogManager.getLogger(DefaultErrorHandler.class);


    // in case we get a validation exception
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ValidationException.class})
    private ValidationResult handleException(ValidationException ex) {
        return ex.getValidationResult();
    }

    // in case we get any other exception
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public ServerError handleException(Throwable ex) {
        logger.error(ex); // log it to sonsole for sure..
        return ServerError.UNKNOWN_ERROR;
    }


}
