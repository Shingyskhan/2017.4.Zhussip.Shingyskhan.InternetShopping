package com.demo.ui.adminPage;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dao.entity.Product;
import com.demo.service.ProductService;
import com.vaadin.navigator.View;

public class UpdateProductPresenter {

	@Autowired
	private ProductService service;

	private IUpdateView view;

	public interface IUpdateView extends View {
		void update();

		void updateList();
	}

	public UpdateProductPresenter(IUpdateView view) {
		super();
		this.view = view;
	}

	public List<Product> findAll() {
		return service.findAll();
	}

	public void delete(Product product) {
		service.delete(product.getId());
		view.updateList();
	}
	
	public void update(Product product){
		 service.update(product);
	}

}
