package com.demo.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface IBaseDao<ENTITY,ID extends Serializable> {

	public ENTITY save(ENTITY entity);
	
	public ENTITY update(ENTITY entity);

	public ENTITY get(ID id);
	
	public int delete(ENTITY entity);
	public int delete(ID id);
	
	public List<ENTITY> findAll();
}
