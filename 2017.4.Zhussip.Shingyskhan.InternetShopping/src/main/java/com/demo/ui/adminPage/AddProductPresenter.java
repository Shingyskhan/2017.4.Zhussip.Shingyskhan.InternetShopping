package com.demo.ui.adminPage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dao.entity.Price;
import com.demo.dao.entity.Product;
import com.demo.service.PriceService;
import com.demo.service.ProductService;
import com.demo.ui.AbsBasePresenter;
import com.vaadin.navigator.View;

public class AddProductPresenter extends AbsBasePresenter {

	@Autowired
	private ProductService service;
	@Autowired
	private PriceService service1;
	private IAddProduct view;

	public interface IAddProduct extends View {
		void save();
		void back();
	}

	public AddProductPresenter(IAddProduct view) {
		super();
		this.view = view;
	}

	public List<Product> findAll() {
		return service.findAll();
	}
	public void saveProduct(Product product) {
		service.save(product);
	}
	
	public void savePrice(Price price){
		service1.save(price);
	}
}
