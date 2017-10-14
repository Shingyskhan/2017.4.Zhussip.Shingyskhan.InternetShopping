package com.demo.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbsBaseDao<ENTITY, ID extends Serializable> implements IBaseDao<ENTITY, ID> {

	protected EntityManager em;
	
	protected Class<ENTITY> clazz;

	public AbsBaseDao(EntityManager em, Class<ENTITY> clazz) {
		this.em = em;
		this.clazz = clazz;
	}

	@Override
	public ENTITY save(ENTITY entity) {
		em.persist(entity);
		return entity;
	}


	@Override
	public ENTITY get(ID id) {
		return em.find(this.clazz, id);
	}

	@Override
	public ENTITY update(ENTITY entity) {
		em.merge(entity);
		return entity;
	}



	@Override
	public int delete(ENTITY entity) {
		em.remove(entity);
		return 1;
	}

	

	@Override
	public int delete(ID id) {
		ENTITY existEntity = em.find(this.clazz, id);
		if (existEntity != null) {
			em.remove(existEntity);
		}
		return 1;
	}

	@Override
	public List<ENTITY> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ENTITY> query = cb.createQuery(this.clazz);
		query.from(this.clazz);
		return em.createQuery(query).getResultList();
	}


}
