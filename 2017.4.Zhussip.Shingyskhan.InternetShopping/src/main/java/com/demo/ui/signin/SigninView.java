package com.demo.ui.signin;

import java.util.List;
import com.demo.dao.entity.Gender;
import com.demo.dao.entity.Person;
import com.demo.ui.login.Login;
import com.demo.ui.signin.SignInViewPresenter.INewPersonView;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class SigninView extends VerticalLayout implements INewPersonView {

	private TextField name;
	private TextField surname;
	private TextField username;
	private PasswordField password, confirm;
	private DateField birthDate;
	private TextField age;
	private ComboBox<Gender> gender;

	private Grid<Person> grid;
	private HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
	private SignInViewPresenter presenter;
	private Button btnSave;
	private Binder<Person> binder;
	private Button btnBack;
	private Label error;

	public SigninView() {
		super();
		presenter = new SignInViewPresenter(this);
		Notification.show("On enter run");
	}

	@Override
	public void enter(ViewChangeEvent event) {
		FormLayout form = new FormLayout();
		name = new TextField("First Name");
		surname = new TextField("Last Name");
		username = new TextField("UserName");
		password = new PasswordField("Password");
		confirm = new PasswordField("Confirm");
		birthDate = new DateField("Date of Birth");
		age = new TextField("Age");

		name.setRequiredIndicatorVisible(true);
		surname.setRequiredIndicatorVisible(true);
		username.setRequiredIndicatorVisible(true);
		password.setRequiredIndicatorVisible(true);
		gender = new ComboBox<>("Gender");
		gender.setItems(Gender.male, Gender.female);

		binder = new Binder<>(Person.class);
		binder.forField(age).withConverter(new StringToIntegerConverter("Must be a number value")).bind("age");
		binder.bindInstanceFields(this);

		btnSave = new Button("Registrate", e -> doSave());
		btnBack = new Button("Back", e -> returnMain());
		grid = new Grid<>(Person.class);
		grid.setSizeFull();
		List<Person> customers = presenter.findAll();
		grid.setItems(customers);
		grid.setColumns("id", "name", "surname", "username", "password", "age", "birthDate", "gender");

		error = new Label("*We have such user. You need to change your Username");
		error.setVisible(false);

		form.addComponents(name, surname, username, password, confirm, birthDate, age, gender);
		VerticalLayout vl = new VerticalLayout();
		vl.addComponents(form, new HorizontalLayout(btnSave, btnBack), error);

		splitPanel.setSizeFull();
		splitPanel.setFirstComponent(vl);
		splitPanel.setSecondComponent(grid);
		addComponent(splitPanel);

	}

	@Override
	public void doSave() {
		Person person = new Person();
		// Person person2=presenter.FindUsername(username.getValue());
		boolean writeBeanIfValid = binder.writeBeanIfValid(person);
		if (writeBeanIfValid) {
			try {
				if (person.getName().equals("") || person.getSurname().equals("") || person.getUsername().equals("")
						|| person.getPassword().equals("")) {
					Notification.show("You need to fill needed fields");
				} else if (!password.getValue().equals(confirm.getValue())) {
					Notification.show("Confirm your password correctly");
				} else {
					presenter.save(person);
					error.setVisible(false);
					Login.person=person;
					UI.getCurrent().getNavigator().navigateTo("mainpage");
				}
			} catch (org.springframework.dao.DataIntegrityViolationException e) {
				Notification.show("Change your username!");
				error.setVisible(true);
			}

		} else {
			Notification.show("Check form");
		}

	}

	@Override
	public void returnMain() {
		UI.getCurrent().getNavigator().navigateTo("");

	}

	public void updateList() {
		List<Person> l = presenter.findAll();
		grid.setItems(l);

	}

}
