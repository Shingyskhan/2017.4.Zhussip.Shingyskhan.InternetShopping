package com.demo.ui;

import com.demo.util.SpringBeanProvider;

public abstract class AbsBasePresenter {
	public AbsBasePresenter() {
		SpringBeanProvider.Inject(this);
	}
}
