package com.weijuly.develop.ras.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InOutConfiguration {

	private final String name;
	private final String request;
	private final String response;

	@JsonCreator
	public InOutConfiguration(@JsonProperty("name") String name,
			@JsonProperty("request") String request,
			@JsonProperty("response") String response) {
		this.name = name;
		this.request = request;
		this.response = response;
	}

	public String getName() {
		return name;
	}

	public String getRequest() {
		return request;
	}

	public String getResponse() {
		return response;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((request == null) ? 0 : request.hashCode());
		result = prime * result
				+ ((response == null) ? 0 : response.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InOutConfiguration other = (InOutConfiguration) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (request == null) {
			if (other.request != null)
				return false;
		} else if (!request.equals(other.request))
			return false;
		if (response == null) {
			if (other.response != null)
				return false;
		} else if (!response.equals(other.response))
			return false;
		return true;
	}

}
