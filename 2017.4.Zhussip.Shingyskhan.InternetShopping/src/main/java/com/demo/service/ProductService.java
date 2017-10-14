package com.demo.service;

import java.util.List;

import com.demo.dao.entity.Product;

public interface ProductService {
	public Product save(Product product);
	public List<Product> findAll();

	public int delete(Product product);
	public int delete(Long id);
	

	public Product update(Product product);
}
