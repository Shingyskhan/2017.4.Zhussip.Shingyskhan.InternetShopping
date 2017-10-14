package com.demo.ui.mainPage.Product;

import java.util.List;

import com.demo.dao.entity.Product;
import com.demo.ui.mainPage.Product.BasketPresenter.IBasketView;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;


public class BasketView extends VerticalLayout implements IBasketView {

	BasketPresenter presenter;
	Grid<Product> grid;
	List<Product> products = OrderView.products;
	private TextField Total = new TextField("You will pay:");;
	private TextField WithTax=new TextField("With Tax");
	double tax=0;
	int total = 0;

	public BasketView() {
		presenter = new BasketPresenter(this);
		for (int i = 0; i < OrderView.products.size(); i++) {
			total = total + OrderView.products.get(i).getPrice() * OrderView.products.get(i).getQuantity();
			
		}
		tax=(Integer)total+total*0.12;
		Total.setValue(String.valueOf(total));
		WithTax.setValue(String.valueOf(tax));
	}

	@Override
	public void enter(ViewChangeEvent event) {

		grid = new Grid<>(Product.class);
		grid.setSizeFull();
		grid.setItems(OrderView.products);
		grid.setColumns("id", "name", "quantity", "description", "price");
		grid.addColumn(person -> "Delete", new ButtonRenderer<>(clickEvent -> Delete(clickEvent.getItem())));
		Button b = new Button("Back", e -> Back());
		Button buy = new Button("Buy", e -> Buy());
		Label taxLabel=new Label("Tax is 12%");
		Total.setEnabled(false);
		WithTax.setEnabled(false);
		addComponents(grid,new HorizontalLayout(Total ,WithTax,taxLabel) ,new HorizontalLayout(buy, b));
	}

	private void Buy() {

	}

	private void Back() {
		UI.getCurrent().getNavigator().navigateTo("mainpage");
	}

	private void Delete(Product item) {
		presenter.delete(item);
	}

	@Override
	public void updatelist() {
		int tot=0;
		for (int i = 0; i < OrderView.products.size(); i++) {
			tot = tot + OrderView.products.get(i).getPrice() * OrderView.products.get(i).getQuantity();
		}
		total=tot;
		tax=(Integer)total+total*0.12;
		WithTax.setValue(String.valueOf(tax));
		Total.setValue(String.valueOf(total));

		grid.setItems(OrderView.products);
	}

}
