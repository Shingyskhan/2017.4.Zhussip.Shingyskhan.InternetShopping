package com.demo.dao;

import com.demo.dao.base.IBaseDao;
import com.demo.dao.entity.Person;

public interface PersonDao extends IBaseDao<Person, Long> {
	public Person findByName(String name);
	public Person findByUsername(String username );
	public Person findByUsernameAndPassowrd(String username,String password);
	
}