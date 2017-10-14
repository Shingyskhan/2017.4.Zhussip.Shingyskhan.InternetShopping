package com.demo.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.demo.dao.base.AbsBaseDao;
import com.demo.dao.entity.Price;
@Repository
public class PriceDaoImpl extends AbsBaseDao<Price, Integer> implements PriceDao {

	public PriceDaoImpl(EntityManager em) {
		super(em, Price.class);
	}
}
