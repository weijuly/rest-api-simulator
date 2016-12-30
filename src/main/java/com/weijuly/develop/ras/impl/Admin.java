package com.weijuly.develop.ras.impl;

import com.weijuly.develop.ras.data.InOutConfiguration;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Admin {

	public ResponseEntity<String> create(InOutConfiguration config);

	public List<InOutConfiguration> list();

	public ResponseEntity<String> get(Long id);

}
