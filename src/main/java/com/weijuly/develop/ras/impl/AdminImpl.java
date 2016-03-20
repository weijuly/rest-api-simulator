package com.weijuly.develop.ras.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weijuly.develop.ras.data.InOutConfiguration;

public class AdminImpl implements Admin {
	
	final Logger logger = LoggerFactory.getLogger(AdminImpl.class);

	public InOutConfiguration create(InOutConfiguration config) {
		logger.info(">>create>>");
		logger.info("<<create<<");
		return config;
	}
	
	public List<InOutConfiguration> list(){
		List<InOutConfiguration> configs = new ArrayList<InOutConfiguration>();
		return configs;
	}

}
