package com.weijuly.develop.ras.impl;

import static com.weijuly.develop.ras.data.DataConverter.convert;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weijuly.develop.ras.data.InOutConfiguration;
import com.weijuly.develop.ras.persist.InOutConfigurationDAO;

@Service
@Transactional
public class AdminImpl implements Admin {

	@Autowired
	InOutConfigurationDAO dao;

	final Logger logger = LoggerFactory.getLogger(AdminImpl.class);

	public InOutConfiguration create(InOutConfiguration config) {
		logger.info(">>create>>");
		dao.create(convert(config));
		logger.info("<<create<<");
		return config;
	}

	public List<InOutConfiguration> list() {
		List<InOutConfiguration> configs = new ArrayList<InOutConfiguration>();
		return configs;
	}

}
