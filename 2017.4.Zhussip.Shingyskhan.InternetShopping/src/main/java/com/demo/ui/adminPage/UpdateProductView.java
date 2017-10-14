package com.demo.ui.adminPage;

import com.demo.dao.entity.Product;
import com.demo.ui.adminPage.UpdateProductPresenter.IUpdateView;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class UpdateProductView extends VerticalLayout implements IUpdateView {

	UpdateProductPresenter presenter;
	private Binder<Product> binder;
	Product p = AdminView.product;

	private TextField name;
	private TextField quantity;
	private TextArea description;
	private TextField price;
	private Button updateBtn;

	public UpdateProductView() {
		super();
		presenter = new UpdateProductPresenter(this);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		VerticalLayout vl = new VerticalLayout();
		Label update = new Label("UPDATE");
		Label idl = new Label("product ID:   ");
		Label idp = new Label();
		idp.setCaption(p.getId().toString());

		Label namel = new Label("Product Name:   ");
		Label namep = new Label();
		namep.setCaption(p.getName());

		Label quantityl = new Label("Product Quantity:  ");
		Label quantityp = new Label();
		quantityp.setCaption(p.getQuantity().toString());

		Label descriptionl = new Label("Product Description:  ");
		Label descriptionp = new Label();
		descriptionp.setCaption(p.getDescription());

		Label pricel = new Label("Product Price:  ");
		Label pricep = new Label();
		pricep.setCaption(p.getPrice().toString());

		vl.addComponents(update, new HorizontalLayout(idl, idp), new HorizontalLayout(namel, namep),
				new HorizontalLayout(quantityl, quantityp), new HorizontalLayout(descriptionl, descriptionp),
				new HorizontalLayout(pricel, pricep));
		vl.setComponentAlignment(update, Alignment.MIDDLE_CENTER);

		name = new TextField("name");
		quantity = new TextField("quantity");
		description = new TextArea("description");
		price = new TextField("price");

		binder = new Binder<>(Product.class);
		binder.forField(quantity).withConverter(new StringToIntegerConverter("Must be a number value"))
				.bind("quantity");
		binder.forField(price).withConverter(new StringToIntegerConverter("Must be a number value")).bind("price");
		binder.bindInstanceFields(this);
		binder.setBean(AdminView.product);
		updateBtn = new Button("Update", e -> update());
		VerticalLayout vl2 = new VerticalLayout();
		vl2.addComponents(name, quantity, description, price, updateBtn);

		addComponent(new HorizontalLayout(vl, vl2));

	}

	@Override
	public void update() {
		Product g = new Product();
		g = binder.getBean();
		System.out.println("Id= " + g.getId());
		System.out.println("name= " + g.getName());
		System.out.println("price= " + g.getPrice());
		System.out.println("quantity= " + g.getQuantity());
		try {
			presenter.update(g);
		} catch (NullPointerException e) {
			Notification.show("Null");
		}

	}

	@Override
	public void updateList() {
		// TODO Auto-generated method stub

	}

}
