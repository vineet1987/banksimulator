package org.tw.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
	
	private List<String> errors = new ArrayList<>();

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public void addError(String error) {
		getErrors().add(error);
	}

	public boolean isOk() {
		return getErrors().isEmpty();
	}

}
