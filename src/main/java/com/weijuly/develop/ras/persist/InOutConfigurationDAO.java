package com.weijuly.develop.ras.persist;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface InOutConfigurationDAO
		extends CrudRepository<InOutConfigurationDO, Long> {

	InOutConfigurationDO findByName(String name);

}
