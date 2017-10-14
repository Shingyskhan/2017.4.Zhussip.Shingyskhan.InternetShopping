package com.demo.ui.mainPage.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dao.entity.Product;
import com.demo.service.ProductService;
import com.vaadin.navigator.View;

public class OrderPresenter {

	@Autowired
	private ProductService service;

	private IOrederView view;
	

	public interface IOrederView extends View {
		
	}

	

	public OrderPresenter(IOrederView view) {
		super();
		this.view = view;
	}




	public List<Product> findAll() {
		return service.findAll();
	}
}
