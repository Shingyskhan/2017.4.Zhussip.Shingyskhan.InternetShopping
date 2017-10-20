package khan.InternetShop.Client.product;

import com.vaadin.navigator.View;
import khan.InternetShop.Client.product.util.AbsBasePresenter;
import khan.InternetShop.Server.entity.Product;
import khan.InternetShop.Server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class ProductPresenter extends AbsBasePresenter {
    @Autowired
    private ProductService service;
    private IProductView view;

    public void delete(long id) {
        service.delete(id);
    }

    public interface IProductView extends View {
        void updateList();
    }

    public ProductPresenter(IProductView view) {
        super();
        this.view = view;
    }

    public List<Product>findAll(){
        return service.findAll();
    }

    public void save(Product bean){
        service.save(bean);
        view.updateList();
    }

    public void update(Product bean){
        service.update(bean);
        view.updateList();

    }

    public void delete(Product bean){
        service.delete(bean);
        view.updateList();
    }
    public Product findById(Long id){
        return service.getById(id);
    }

    public void deleteMany(Set<Product> products){
        service.deleteMany(products);
        view.updateList();
    }
}
