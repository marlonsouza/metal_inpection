package com.marlon.desafiopro.validation;

import javax.json.JsonArray;
import javax.json.JsonValue.ValueType;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidSpotValidator implements ConstraintValidator<ValidSpot, JsonArray> {

	@Override
	public boolean isValid(JsonArray spot, ConstraintValidatorContext context) {
		return !spot.stream().anyMatch(vector ->{
			
			JsonArray listJsonArray;
			
			try {
				 listJsonArray = vector.asJsonArray(); 
			}catch (ClassCastException e) {
				return true;
			}
			
			return listJsonArray.stream().anyMatch(value ->!(value.getValueType() == ValueType.NUMBER));
		});
	}

}
