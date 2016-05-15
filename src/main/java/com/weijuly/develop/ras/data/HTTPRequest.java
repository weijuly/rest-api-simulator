package com.weijuly.develop.ras.data;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HTTPRequest {

	private String method;
	private String path;
	private Map<String, String> headers;
	private String body;

	@JsonCreator
	public HTTPRequest(@JsonProperty("method") String method, @JsonProperty("path") String path,
			@JsonProperty("headers") Map<String, String> headers, @JsonProperty("body") String body) {
		this.method = method;
		this.path = path;
		this.headers = headers;
		this.body = body;
	}

	public String getMethod() {
		return method;
	}

	public String getPath() {
		return path;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public String getBody() {
		return body;
	}

}
