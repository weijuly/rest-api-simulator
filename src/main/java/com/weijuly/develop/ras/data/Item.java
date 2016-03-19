package com.weijuly.develop.ras.data;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Item extends ResourceSupport {

	private final Long number;
	private final String message;

	@JsonCreator
	public Item(@JsonProperty("number") Long number, @JsonProperty("message") String message) {
		this.number = number;
		this.message = message;
	}

	public Long getNumber() {
		return number;
	}

	public String getMessage() {
		return message;
	}

}
