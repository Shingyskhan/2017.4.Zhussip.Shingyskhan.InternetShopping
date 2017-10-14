package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.PersonDao;
import com.demo.dao.entity.Person;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao dao;

	@Override
	public Person save(Person entity) {
		if (entity == null)
			throw new RuntimeException("Person entity cannot null");
		return dao.save(entity);
	}

	@Override
	public List<Person> findAll() {
		return dao.findAll();
	}

	@Override
	public Person getByName(String name) {
		if (name == null || "".equals(name))
			throw new RuntimeException("Name parameter cannot null");
		return dao.findByName(name);
	}

	@Override
	public int delete(Person person) {
		return dao.delete(person.getId());
	}

	@Override
	public int delete(Long id) {
		return dao.delete(id);
	}

	@Override
	public Person update(Person person) {
		return dao.update(person);
	}

	@Override
	public Person getByUsername(String username) {
		if (username == null || "".equals(username))
			throw new RuntimeException("Name parameter cannot null");
		return dao.findByUsername(username);
		
	}

	@Override
	public Person getByUsernameAndPassword(String username, String password) {
		return dao.findByUsernameAndPassowrd(username, password);
	}


}
