package com.demo.ui.mainPage;

import org.vaadin.teemusa.sidemenu.SideMenu;

import com.demo.ui.login.Login;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class mainPage extends HorizontalLayout implements View {

	
	private Button product;
	private Button Basket;
	private Button Tax;
	private Button Show;

	@Override
	public void enter(ViewChangeEvent event) {
		
	
	/*product=new Button("Product",e->Product());
	Basket=new Button("Basket",e->Basket());
	Tax=new Button("Tax");
	Show=new Button("Show");
	addComponents(product,Basket,Tax,Show);*/
	
		SideMenu menu = new SideMenu();
		menu.setSizeFull();
		menu.setMenuCaption("Customer Page");
		menu.addMenuItem("Hello "+Login.person.getName(),()->{});
		menu.addMenuItem("Products",()->{UI.getCurrent().getNavigator().navigateTo("product");});
		menu.addMenuItem("Basket", ()->{UI.getCurrent().getNavigator().navigateTo("basket");});
		menu.addMenuItem("Log Out", ()->{UI.getCurrent().getNavigator().navigateTo("");});
		addComponent(menu);
	
	}

	private void Basket() {
		UI.getCurrent().getNavigator().navigateTo("basket");
	}

	private void Product() {
		UI.getCurrent().getNavigator().navigateTo("product");
	}

}
