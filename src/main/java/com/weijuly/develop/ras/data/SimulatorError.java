package com.weijuly.develop.ras.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SimulatorError {
	
	private String type;
	private String message;

	@JsonCreator
	public SimulatorError(@JsonProperty("type") String type, @JsonProperty("message") String message) {
		this.type = type;
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

}
