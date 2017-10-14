package com.demo.ui.mainPage.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dao.entity.Product;
import com.demo.service.ProductService;
import com.demo.ui.mainPage.Product.OrderPresenter.IOrederView;
import com.vaadin.navigator.View;

public class BasketPresenter {
	@Autowired
	private ProductService service;

	private IBasketView view;

	public interface IBasketView extends View {
		void updatelist();
	}

	public BasketPresenter(IBasketView view) {
		super();
		this.view = view;
	}

	public List<Product> findAll() {
		return service.findAll();
	}
	
	public void delete(Product product){
		OrderView.products.remove(product);
		view.updatelist();
	}
}
