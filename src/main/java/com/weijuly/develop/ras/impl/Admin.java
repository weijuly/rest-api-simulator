package com.weijuly.develop.ras.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.weijuly.develop.ras.data.InOutConfiguration;

public interface Admin {

	public ResponseEntity<String> create(InOutConfiguration config);

	public List<InOutConfiguration> list();

}
