package com.weijuly.develop.ras.data;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HTTPResponse {
	private Integer code;
	private Map<String, String> headers;
	private String body;

	@JsonCreator
	public HTTPResponse(@JsonProperty("code") Integer code, @JsonProperty("headers") Map<String, String> headers,
			@JsonProperty("body") String body) {
		this.code = code;
		this.headers = headers;
		this.body = body;
	}

	public Integer getCode() {
		return code;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public String getBody() {
		return body;
	}

}
