package com.demo.dao;


import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.demo.dao.base.AbsBaseDao;
import com.demo.dao.entity.Person;


@Repository
public class PersonDaoImpl extends AbsBaseDao<Person, Long> implements PersonDao {

	public PersonDaoImpl(EntityManager em) {
		super(em, Person.class);
	}

	//select * from Person where name = ?
	@Override
	public Person findByName(String name) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Person> query = cb.createQuery(Person.class);
		Root<Person> root = query.from(Person.class);
		query.where(cb.equal(root.get("name"), name));
		return em.createQuery(query).getSingleResult();
	}

	@Override
	public Person findByUsernameAndPassowrd(String username, String password) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Person> query = cb.createQuery(Person.class);
		Root<Person> root = query.from(Person.class);
		query.where(cb.and(cb.equal(root.get("username"), username),cb.equal(root.get("password"), password)));
		return em.createQuery(query).getSingleResult();
	}

	@Override
	public Person findByUsername(String username) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Person> query = cb.createQuery(Person.class);
		Root<Person> root = query.from(Person.class);
		query.where(cb.equal(root.get("username"), username));
		return em.createQuery(query).getSingleResult();
	}


	
}
