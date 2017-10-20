package khan.InternetShop.Client.product.util;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

public class SpringBeanProvider {
    public static void Inject(final Object object) {
        final AutowireCapableBeanFactory beanFactory = BeanUtil.getAutowiredBeanFactory();
        beanFactory.autowireBean(object);
    }
}
