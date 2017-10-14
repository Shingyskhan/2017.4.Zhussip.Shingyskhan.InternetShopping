package com.demo.service;

import java.util.List;
import com.demo.dao.entity.Price;

public interface PriceService {
	public Price save(Price entity);

	public List<Price> findAll();

	public int delete(Price price);

	public int delete(Long id);

	public Price update(Price person);
}
