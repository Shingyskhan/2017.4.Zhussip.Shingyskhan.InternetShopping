package com.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.ProductDao;
import com.demo.dao.entity.Product;
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao dao;

	@Override
	public Product save(Product product) {
		if (product == null)
			throw new RuntimeException("Product entity cannot null");
		return dao.save(product);
	}

	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}

	@Override
	public int delete(Product product) {
		return dao.delete(product.getId());

	}

	@Override
	public int delete(Long id) {
		return dao.delete(id);

	}

	@Override
	@javax.transaction.Transactional
	public Product update(Product product) {
		return dao.update(product);
	}

}
