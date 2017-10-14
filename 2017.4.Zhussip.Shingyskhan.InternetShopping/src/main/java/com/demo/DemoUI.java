package com.demo;

import com.demo.ui.adminPage.AddProductView;
import com.demo.ui.adminPage.AdminView;
import com.demo.ui.adminPage.UpdateProductView;
import com.demo.ui.login.Login;
import com.demo.ui.mainPage.mainPage;
import com.demo.ui.mainPage.Product.BasketView;
import com.demo.ui.mainPage.Product.OrderView;
import com.demo.ui.mainPage.Product.ProductView;
import com.demo.ui.signin.SigninView;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
@SuppressWarnings("serial")
@SpringUI(path="/demo")
public class DemoUI extends UI{
	Navigator navigator;
	protected static final String MAINVIEW = "main";

	@Override
	protected void init(VaadinRequest request) {
		
		navigator = new Navigator(this, this);
		navigator.addView("", Login.class);
		navigator.addView("mainpage", mainPage.class);
		navigator.addView("signin", SigninView.class);
		navigator.addView("adminpage", AdminView.class);
		navigator.addView("createproduct", AddProductView.class);
		navigator.addView("updateproduct", UpdateProductView.class);
		navigator.addView("product", ProductView.class);
		navigator.addView("order", OrderView.class);
		navigator.addView("basket", BasketView.class);
		
        navigator.navigateTo("");
	
		
	}
	
	}


