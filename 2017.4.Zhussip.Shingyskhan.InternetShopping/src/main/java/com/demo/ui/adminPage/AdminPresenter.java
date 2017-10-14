package com.demo.ui.adminPage;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dao.entity.Person;
import com.demo.dao.entity.Product;
import com.demo.service.PersonService;
import com.demo.service.ProductService;
import com.demo.ui.AbsBasePresenter;
import com.vaadin.navigator.View;

public class AdminPresenter extends AbsBasePresenter {
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private PersonService service2;
	
	private IAdminView view;

	public interface IAdminView extends View {
		void add();

		void update();

		void delete(Set<Product> selected);
		
		void updateList();
		void updatePerson();
	}

	public AdminPresenter(IAdminView view) {
		super();
		this.view = view;
	}

	public List<Product>findAll(){
		return service.findAll();
	}
	public void delete(Product product){
		service.delete(product.getId());
		view.updateList();
	}
	
	public List<Person>findAllPerson(){
		return service2.findAll();
	}
	public void deletePerson (Person person){
		service2.delete(person.getId());
		view.updatePerson();
	}
	public void saveProduct(Product product) {
		service.save(product);
		view.updateList();
	}

}
