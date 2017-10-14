package com.demo.ui.signin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.ui.AbsBasePresenter;
import com.demo.dao.entity.Person;
import com.demo.service.PersonService;
import com.vaadin.navigator.View;

public class SignInViewPresenter extends AbsBasePresenter {

	@Autowired
	private PersonService service;

	private INewPersonView view;
	

	public interface INewPersonView extends View {
		
		void doSave();
		void returnMain();
		void updateList();

	}

	

	public SignInViewPresenter(INewPersonView view) {
		super();
		this.view = view;
	}


	public void save(Person person) {
		service.save(person);
		view.updateList();
	}

	public List<Person> findAll() {
		return service.findAll();
	}
	
	public Person FindUsername(String s){
		Person p =service.getByUsername(s);
		return p;
	}

}
