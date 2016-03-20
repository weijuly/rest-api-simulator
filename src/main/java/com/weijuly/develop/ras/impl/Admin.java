package com.weijuly.develop.ras.impl;

import java.util.List;

import com.weijuly.develop.ras.data.InOutConfiguration;

public interface Admin {

	public InOutConfiguration create (InOutConfiguration config );
	
	public List<InOutConfiguration> list();
	
}
