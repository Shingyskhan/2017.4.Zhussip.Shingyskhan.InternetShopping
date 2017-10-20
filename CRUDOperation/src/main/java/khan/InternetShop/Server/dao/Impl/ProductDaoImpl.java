package khan.InternetShop.Server.dao.Impl;

import khan.InternetShop.Server.dao.BaseDao.AbstractBaseDao;
import khan.InternetShop.Server.dao.BaseDao.IBaseDao;
import khan.InternetShop.Server.dao.ProductDao;
import khan.InternetShop.Server.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
@Repository
public class ProductDaoImpl extends AbstractBaseDao<Product,Long> implements ProductDao{
    public ProductDaoImpl(EntityManager em) {
        super(em, Product.class);
    }
}
