package khan.InternetShop.Server.dao.BaseDao;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<ENTITY,ID extends Serializable> {
    public ENTITY save(ENTITY entity);

    public ENTITY update(ENTITY entity);

    public ENTITY getById(ID id);
    public int delete(ENTITY entity);
    public int delete(ID id);
    public List<ENTITY> findAll();

}
