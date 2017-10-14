package com.demo.ui.mainPage.Product;

import java.util.ArrayList;
import java.util.List;

import com.demo.dao.entity.Product;
import com.demo.ui.mainPage.Product.OrderPresenter.IOrederView;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class OrderView extends VerticalLayout implements IOrederView {

	private TextField quantity;
	private TextField Total;
	public static List<Product> products = new ArrayList<Product>();
	OrderPresenter presenter;
	int a=0;

	public OrderView() {
		presenter = new OrderPresenter(this);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		VerticalLayout vl = new VerticalLayout();
		Label name = new Label("name: " + ProductView.product.getName());
		Label price = new Label("price: " + ProductView.product.getPrice());
		Label quantity1 = new Label("quantity: " + ProductView.product.getQuantity());
		Label description = new Label("description: " + ProductView.product.getDescription());
		vl.addComponents(name, price, quantity1, description);
		quantity = new TextField("How much you will buy");
		Total = new TextField("Total will be");
		quantity.addValueChangeListener(e -> {
			try {
				int b = Integer.parseInt(quantity.getValue());
				System.out.println(b);
				a = b;
				int total = a * ProductView.product.getPrice();
				Total.setValue(String.valueOf(total));
			} catch (Exception e2) {
				// TODO: handle exception
			}
			

		});
		
		Total.setEnabled(false);

		Button order = new Button("ToBasket", e -> order());
		Button back=new Button("Back",e->back());
		VerticalLayout vl2=new VerticalLayout();
		vl2.addComponents(quantity, Total, new HorizontalLayout(order,back));
		addComponents(new HorizontalLayout(vl, vl2));
	}

	private void back() {
		UI.getCurrent().getNavigator().navigateTo("product");
	}

	private void order() {
		System.out.println(ProductView.product.getId() + " " + ProductView.product.getName());
		Product product = new Product(ProductView.product.getId(), ProductView.product.getName(), a,
				ProductView.product.getDescription(), ProductView.product.getPrice());
		if(product.getQuantity()>ProductView.product.getQuantity()){Notification.show("We don't have such amount of "+product.getName());}
		else{
			products.add(product);
			for (int i = 0; i < products.size(); i++) {
				for (int j = 0; j < products.size(); j++) {
					if (products.get(i).getId() == products.get(j).getId()&& i!=j) {
						
						int a = products.get(i).getQuantity() + products.get(j).getQuantity();
						products.add(new Product(products.get(i).getId(), products.get(i).getName(), a,
								products.get(i).getDescription(), products.get(i).getPrice()));
						
						products.remove(i);
						products.remove(j);
					}
				}
			}
			UI.getCurrent().getNavigator().navigateTo("product");
		}

	}

}
