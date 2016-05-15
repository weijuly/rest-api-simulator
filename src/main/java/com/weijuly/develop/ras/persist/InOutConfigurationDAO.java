package com.weijuly.develop.ras.persist;

import java.util.List;

public interface InOutConfigurationDAO {

	List<InOutConfigurationDO> findByName(String name);

	public void create(InOutConfigurationDO config);

}
