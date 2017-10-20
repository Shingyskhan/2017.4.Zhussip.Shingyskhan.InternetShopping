package khan.InternetShop.Client.product.util;

import khan.InternetShop.Client.product.util.SpringBeanProvider;

public abstract class AbsBasePresenter {
	public AbsBasePresenter() {
		SpringBeanProvider.Inject(this);
	}
}
