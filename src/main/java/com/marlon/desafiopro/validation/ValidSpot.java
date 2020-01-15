package com.marlon.desafiopro.validation;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = ValidSpotValidator.class)
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface ValidSpot {

	String message() default "Invalid Matrix Format";
	
	Class<?>[] groups() default {};
    
	Class<? extends Payload>[] payload() default {};
	
}
