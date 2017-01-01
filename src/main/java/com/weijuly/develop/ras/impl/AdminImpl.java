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
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

public class AdminImpl implements Admin {

	@Autowired
	private DataTransformer xformer;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private InOutConfigurationDAO dao;

	final private Logger logger = LoggerFactory.getLogger(AdminImpl.class);

	@Override
	public ResponseEntity<String> create(InOutConfiguration request) {
		try {
			InputValidator.validate(request);
			if (exists(request)) {
				logger.warn("duplicate entry for name {}", request.getName());
				return ResponseEntity.status(CONFLICT).body(toString(xformer.xform(dao.findByName(request.getName()))));
			}
			InOutConfigurationDO dataObject = xformer.xform(request);
			dataObject = dao.save(dataObject);
			logger.info("saved configuration successfully with id: {}", dataObject.getId());
			InOutConfiguration response = xformer.xform(dataObject);
			return ResponseEntity.status(CREATED).body(toString(response));
		} catch (ConfigException | IOException ex) {
			SimulatorError error = new SimulatorError("INPUT", ex.getMessage());
			return ResponseEntity.status(BAD_REQUEST).body(toString(error));
		}
	}

	@Override
	public ResponseEntity<String> list() {
		try {
			List<InOutConfiguration> configurations = new ArrayList<>();
			for (InOutConfigurationDO dataObj : dao.findAll()) {
				configurations.add(xformer.xform(dataObj));
			}
			String response = toString(configurations);
			return ResponseEntity.status(OK).body(response);
		} catch (IOException ex) {
			SimulatorError error = new SimulatorError("INPUT", ex.getMessage());
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(toString(error));
		}
	}

	@Override
	public ResponseEntity<String> get(Long id) {
		try {
			logger.info("got request for:{}", id);
			InOutConfigurationDO dataObject = dao.findOne(id);
			if (dataObject == null) {
				return ResponseEntity.status(NOT_FOUND).body("");
			}
			InOutConfiguration response = xformer.xform(dataObject);
			return ResponseEntity.status(OK).body(toString(response));
		} catch (IOException ex) {
			SimulatorError error = new SimulatorError("INPUT", ex.getMessage());
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(toString(error));
		}

	}

	@Override
	public ResponseEntity<String> delete(Long id) {
		try {
			if (dao.findOne(id) == null) {
				SimulatorError error = new SimulatorError("INPUT", "cannot delete a non-existent entry");
				return ResponseEntity.status(NOT_FOUND).body(toString(error));
			}
			InOutConfiguration response = xformer.xform(dao.findOne(id));
			dao.delete(id);
			return ResponseEntity.status(OK).body(toString(response));
		} catch (IOException ex) {
			SimulatorError error = new SimulatorError("INPUT", ex.getMessage());
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(toString(error));
		}

	}

	private String toString(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException jpEx) {
			return null;
		}
	}

	private boolean exists(InOutConfiguration configuration) {
		return dao.findByName(configuration.getName()) != null;
	}

}
