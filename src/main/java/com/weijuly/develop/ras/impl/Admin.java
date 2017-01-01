package com.weijuly.develop.ras.impl;

import com.weijuly.develop.ras.data.InOutConfiguration;
import org.springframework.http.ResponseEntity;

public interface Admin {

	public ResponseEntity<String> create(InOutConfiguration config);

	public ResponseEntity<String> list();

	public ResponseEntity<String> get(Long id);

	public ResponseEntity<String> delete(Long id);

}
