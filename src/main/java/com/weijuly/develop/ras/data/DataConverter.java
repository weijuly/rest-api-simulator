package com.weijuly.develop.ras.data;

import com.weijuly.develop.ras.persist.InOutConfigurationDO;

public class DataConverter {

	public static InOutConfigurationDO convert(InOutConfiguration in) {
		return new InOutConfigurationDO(in.getName(), in.getRequest(),
				in.getResponse());
	}
	
	public static InOutConfiguration convert(InOutConfigurationDO in){
		return new InOutConfiguration(in.getName(), in.getRequest(),
				in.getResponse());
	}

}
