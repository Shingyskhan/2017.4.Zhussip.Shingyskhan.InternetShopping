package com.demo.ui.login;
import com.demo.dao.entity.Person;
import com.demo.ui.login.LoginPresenter.ILoginView;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class Login extends VerticalLayout implements ILoginView {
	private Button signin;
	private LoginPresenter presenter;
	public static Person person;
	private TextField username;
	private PasswordField password;
	private Button login;

	public Login() {
		super();
		presenter = new LoginPresenter(this);
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		setSizeFull();
		VerticalLayout layout = new VerticalLayout();
		
		signin = new Button("sign out", e -> Signin());
		username = new TextField("Username");
		password = new PasswordField("Password");
		login = new Button("login", e -> login());
		layout.addComponents(username, password, new HorizontalLayout(login, signin));
		addComponent(layout);
		setComponentAlignment(layout, Alignment.MIDDLE_RIGHT);
	}

	@Override
	public void login() {
		try {
			person = presenter.getUsernameAndPassword(username.getValue(), password.getValue());
			System.out.println(person.getId());
			if (person.getUsername().equals("khan") && person.getPassword().equals("qwerty")) {
				UI.getCurrent().getNavigator().navigateTo("adminpage");
			} else {
				UI.getCurrent().getNavigator().navigateTo("mainpage");
			}
			 }catch (Exception e) {
			System.out.println(username.getValue() + " " + password.getValue());
			Notification.show("Chek your username and password");
		}

	}

	@Override
	public void Signin() {
		UI.getCurrent().getNavigator().navigateTo("signin");

	}

}
