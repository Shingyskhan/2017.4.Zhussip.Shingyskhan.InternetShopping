package khan.InternetShop.Server.service.Impl;

import khan.InternetShop.Server.dao.ProductDao;
import khan.InternetShop.Server.entity.Product;
import khan.InternetShop.Server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao dao;

    @Override
    public Product save(Product product) {
        if (product == null)
            throw new RuntimeException("Product entity cannot null");
        return dao.save(product);
    }

    @Override
    public List<Product> findAll() {
        return dao.findAll();
    }

    @Override
    public int delete(Product product) {
        return dao.delete(product.getId());
    }

    @javax.transaction.Transactional
    @Override
    public int delete(Long id) {
        return dao.delete(id);
    }

    @Override
    public Product update(Product product) {
        return dao.update(product);
    }

    @Override
    public Product getById(Long id) {
        return dao.getById(id);
    }

    @javax.transaction.Transactional
    @Override
    public void deleteMany(Set<Product> products) {
        for (Product product : products) {
           dao.delete(product.getId());
        }

    }

}
