package com.demo.service;

import java.util.List;
import com.demo.dao.entity.Person;

public interface PersonService {
	public Person save(Person entity);
	public List<Person> findAll();
	public Person getByName(String name);
	public int delete(Person person);
	public int delete(Long id);
	public Person getByUsername(String username);
	public Person getByUsernameAndPassword(String username,String password);

	public Person update(Person person);
}