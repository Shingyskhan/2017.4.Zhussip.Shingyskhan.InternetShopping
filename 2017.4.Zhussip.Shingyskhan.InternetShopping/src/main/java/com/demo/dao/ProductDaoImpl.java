package com.demo.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.demo.dao.base.AbsBaseDao;
import com.demo.dao.entity.Product;
@Repository
public class ProductDaoImpl extends AbsBaseDao<Product, Long> implements ProductDao{

	public ProductDaoImpl(EntityManager em) {
		super(em, Product.class);
	}

}

