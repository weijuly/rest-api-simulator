package com.weijuly.develop.ras.xform;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weijuly.develop.ras.data.HTTPRequest;
import com.weijuly.develop.ras.data.HTTPResponse;
import com.weijuly.develop.ras.data.InOutConfiguration;
import com.weijuly.develop.ras.persist.InOutConfigurationDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class DataTransformer {

	@Autowired
	ObjectMapper mapper;

	//TODO: Need to see why autowiring doesn't work here...
//	public DataTransformer(){
//		mapper = new ObjectMapper();
//	}

	public InOutConfigurationDO xform(final InOutConfiguration input) throws JsonProcessingException {
		return  new InOutConfigurationDO(input.getName(),
				mapper.writeValueAsString(input.getRequest()), mapper.writeValueAsString(input.getResponse()));
	}

	public InOutConfiguration xform(final InOutConfigurationDO input) throws IOException {
		return new InOutConfiguration(input.getName(), mapper.readValue(input.getRequest(), HTTPRequest.class),
				mapper.readValue(input.getResponse(), HTTPResponse.class));
	}



}
