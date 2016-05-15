package com.weijuly.develop.ras.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weijuly.develop.ras.data.InOutConfiguration;
import com.weijuly.develop.ras.data.SimulatorError;
import com.weijuly.develop.ras.persist.InOutConfigurationDAO;
import com.weijuly.develop.ras.persist.InOutConfigurationDO;
import com.weijuly.develop.ras.validator.InputValidator;
import com.weijuly.develop.ras.xform.DataTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class AdminImpl implements Admin {

	@Autowired
	DataTransformer xformer;

	@Autowired
	ObjectMapper mapper;

	@Autowired
	InOutConfigurationDAO dao;

	final Logger logger = LoggerFactory.getLogger(AdminImpl.class);

	public ResponseEntity<String> create(InOutConfiguration inOutConfiguration) {
		logger.info(">>create>>");
		try {
			System.out.println("admin >> mapper" + mapper);
			InputValidator.validate(inOutConfiguration);
			InOutConfigurationDO inOutConfigurationDO = xformer.xform(inOutConfiguration);
			inOutConfigurationDO = dao.save(inOutConfigurationDO);
			logger.info("saved configuration successfully" + inOutConfigurationDO.getId());

		} catch (ConfigException | JsonProcessingException ex) {
			SimulatorError error = new SimulatorError("INPUT", ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(toString(error));
		}  finally {
			System.out.println("aqua");
		}

		logger.info("<<create<<");
		return ResponseEntity.status(HttpStatus.CREATED).body(toString(inOutConfiguration));
	}

	public List<InOutConfiguration> list() {
		List<InOutConfiguration> configs = new ArrayList<InOutConfiguration>();
		return configs;
	}

	private String toString(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException jpEx) {
			return null;
		}
	}

}
