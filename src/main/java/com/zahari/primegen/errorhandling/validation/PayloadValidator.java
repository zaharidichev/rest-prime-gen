package com.zahari.primegen.errorhandling.validation;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Zahari Dichev <zaharidichev@gmail.com>.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
@Scope(
        proxyMode = ScopedProxyMode.TARGET_CLASS,
        value = "prototype"
)
public @interface PayloadValidator {
    String value() default "";
}