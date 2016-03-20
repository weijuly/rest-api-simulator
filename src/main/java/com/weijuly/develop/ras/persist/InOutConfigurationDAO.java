package com.weijuly.develop.ras.persist;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface InOutConfigurationDAO
		extends CrudRepository<InOutConfigurationDO, Long> {

	List<InOutConfigurationDO> findByName(String name);
	
}
