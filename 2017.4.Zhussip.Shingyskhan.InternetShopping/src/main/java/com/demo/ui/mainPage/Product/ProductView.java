package com.demo.ui.mainPage.Product;

import java.util.List;

import com.demo.dao.entity.Product;
import com.demo.ui.mainPage.Product.ProductPresenter.IProductView;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;

public class ProductView  extends VerticalLayout implements IProductView {
	public static Product product;
	Grid<Product> grid; 
	ProductPresenter presenter;
	
	public ProductView() {
		super();
		presenter=new ProductPresenter(this);
	}
	@Override
	public void enter(ViewChangeEvent event) {
		grid = new Grid<>(Product.class);
		grid.setSizeFull();
		List<Product> products = presenter.findAll();
		grid.setItems(products);
		grid.setColumns("id", "name", "quantity", "description", "price");
		grid.addColumn(person ->"Order",new ButtonRenderer<>(clickEvent->updateProduct(clickEvent.getItem())));
		Button b=new Button("Back",e->Back());
		addComponents(grid,b);
		
		
	}
	private void Back() {
		UI.getCurrent().getNavigator().navigateTo("mainpage");
	}
	private void updateProduct(Product item) {
		product=item;
		UI.getCurrent().getNavigator().navigateTo("order");
	}

}
