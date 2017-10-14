package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.PriceDao;
import com.demo.dao.entity.Price;
@Service
@Transactional
public class PriceServiceImpl implements PriceService{

	@Autowired
	private PriceDao dao;
	@Override
	public Price save(Price entity) {
		if (entity == null)
			throw new RuntimeException("Product entity cannot null");
		return dao.save(entity);
	}

	@Override
	public List<Price> findAll() {
		return dao.findAll();
	}

	@Override
	public int delete(Price price) {
		return dao.delete(price);
	
	}

	@Override
	public int delete(Long id) {
		return 0;//dao.delete(id);
	}

	@Override
	public Price update(Price price) {
		return dao.update(price);
	}

}
