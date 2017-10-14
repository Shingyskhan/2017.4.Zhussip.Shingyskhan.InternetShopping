package com.demo.ui.mainPage.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dao.entity.Person;
import com.demo.dao.entity.Product;
import com.demo.service.PersonService;
import com.demo.service.ProductService;
import com.demo.ui.AbsBasePresenter;
import com.demo.ui.signin.SignInViewPresenter.INewPersonView;
import com.vaadin.navigator.View;

public class ProductPresenter extends AbsBasePresenter{

	@Autowired
	private ProductService service;

	private IProductView view;
	

	public interface IProductView extends View {
		
	}

	

	public ProductPresenter(IProductView view) {
		super();
		this.view = view;
	}




	public List<Product> findAll() {
		return service.findAll();
	}
	


}
