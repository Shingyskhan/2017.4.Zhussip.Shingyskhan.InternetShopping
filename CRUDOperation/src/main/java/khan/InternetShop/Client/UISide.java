package khan.InternetShop.Client;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import khan.InternetShop.Client.product.ProductViewImpl;

@SuppressWarnings("serial")
@SpringUI(path="/demo")
public class UISide extends UI {
    private Navigator navigator;
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        navigator = new Navigator(this, this);
        navigator.addView("", ProductViewImpl.class);
        navigator.navigateTo("");

    }
}
