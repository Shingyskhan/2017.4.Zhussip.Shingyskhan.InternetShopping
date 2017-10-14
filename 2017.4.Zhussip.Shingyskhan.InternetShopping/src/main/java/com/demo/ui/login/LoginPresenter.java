package com.demo.ui.login;

import org.springframework.beans.factory.annotation.Autowired;
import com.demo.ui.AbsBasePresenter;
import com.demo.dao.entity.Person;
import com.demo.service.PersonService;
import com.vaadin.navigator.View;

public class LoginPresenter extends AbsBasePresenter {
	@Autowired
	private PersonService service;
	private ILoginView view;

	public interface ILoginView extends View {
		void login();
		void Signin();

	}

	public LoginPresenter(ILoginView view) {
		super();
		this.view = view;
	}

	public Person getUsernameAndPassword(String username, String password) {
		return service.getByUsernameAndPassword(username, password);
	}



}
