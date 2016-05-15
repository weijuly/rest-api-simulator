package com.weijuly.develop.ras.persist;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class InOutConfigurationDAOImpl implements InOutConfigurationDAO {

	@PersistenceUnit
	private EntityManager factory;

	private final String ENTITY = InOutConfigurationDO.class.getName();

	private final String FIND_BY_NAME = "SELECT x FROM " + ENTITY
			+ " x WHERE NAME =:name";

	@Override
	public List<InOutConfigurationDO> findByName(String name) {
		List<InOutConfigurationDO> dos = new ArrayList<InOutConfigurationDO>();
		return dos;
	}

	@Override
	public void create(InOutConfigurationDO config) {
	}

}
