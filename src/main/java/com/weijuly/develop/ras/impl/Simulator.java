package com.weijuly.develop.ras.impl;


import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface Simulator {

	public ResponseEntity<String> simulate(HttpServletRequest request);
}
