package com.weijuly.develop.ras.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InOutConfiguration {

	private final String name;
	private final HTTPRequest request;
	private final HTTPResponse response;

	@JsonCreator
	public InOutConfiguration(@JsonProperty("name") String name,
			@JsonProperty("request") HTTPRequest request,
			@JsonProperty("response") HTTPResponse response) {
		this.name = name;
		this.request = request;
		this.response = response;
	}

	public String getName() {
		return name;
	}

	public HTTPRequest getRequest() {
		return request;
	}

	public HTTPResponse getResponse() {
		return response;
	}
}
