package com.demo.ui.adminPage;




import java.time.LocalDate;

import com.demo.dao.entity.Product;
import com.demo.ui.adminPage.AddProductPresenter.IAddProduct;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AddProductView extends VerticalLayout implements IAddProduct {

	private TextField name;
	private TextField quantity;
	private TextArea description;
	private TextField price;
	private Binder<Product> binder;
	private Button save;
	private AddProductPresenter presenter;
	private DateField date;
	private Button back;
	public AddProductView() {
		super();
		presenter=new AddProductPresenter(this);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		FormLayout form =new FormLayout();
		form.setSizeFull();
		name=new TextField("name");
		quantity=new TextField("quantity");
		description=new TextArea("description");
		price=new TextField("price");
		date=new DateField("Date of price");
		date.setValue(LocalDate.now());
		date.setTextFieldEnabled(false);
		
		
		binder = new Binder<>(Product.class);
		binder.forField(quantity).withConverter(new StringToIntegerConverter("Must be a number value")).bind("quantity");
		binder.forField(price).withConverter(new StringToIntegerConverter("Must be a number value")).bind("price");
		binder.bindInstanceFields(this);
		
		/*binder1 = new Binder<>(Price.class);
		binder1.forField(price).withConverter(new StringToIntegerConverter("Must be a number value")).bind("price");
		binder1.bindInstanceFields(this);*/
		
		form.addComponents(name,quantity,description,price,date);
		save=new Button("Add",e -> save());
		back=new Button("Back",e->back());
		
		addComponents(form,new HorizontalLayout(save,back));
	}

	@Override
	public void save() {
		Product product=new Product();
		boolean writeBeanIfValid = binder.writeBeanIfValid(product);
		if (writeBeanIfValid) {
			presenter.saveProduct(product);
			UI.getCurrent().getNavigator().navigateTo("adminpage");
		} else {
			Notification.show("Check form");
		}
		
	}

	@Override
	public void back() {
		UI.getCurrent().getNavigator().navigateTo("adminpage");
		
	}

}
