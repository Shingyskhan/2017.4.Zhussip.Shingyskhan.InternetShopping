package khan.InternetShop.Server.service;

import khan.InternetShop.Server.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface ProductService {
    public Product save(Product product);
    public List<Product> findAll();
    public int delete(Product product);
    public int delete(Long id);
    public Product update(Product product);

    public Product getById(Long id);

    void deleteMany(Set<Product> products);
}
