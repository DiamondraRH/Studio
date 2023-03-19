package application.data.generic;

import java.util.ArrayList;
import java.util.HashMap;
import application.data.generic.Order;

public interface InterfaceDAO {

    public <T> ArrayList<T> findAll(T obj) throws Exception;

    public <T> ArrayList<T> findAll(T obj, int pagination, int pageSize) throws Exception;

    public <T> ArrayList<T> findAll(T obj, int pagination) throws Exception;

    public <T> T findOneById(T obj, Object id) throws Exception;

    public <T> int save(T obj) throws Exception;

    public <T> int delete(T obj, Object id) throws Exception;

    public <T> int update(T newObj) throws Exception;

    public <T> ArrayList<T> find(T obj) throws Exception;
    public <T> ArrayList<T> find(T obj,boolean orClause) throws Exception;
    public <T> ArrayList<T> findOrClause(T obj,String search,String... cols) throws Exception;
    public <T> ArrayList<T> findOrClause(T obj,int page,int pageSize,String search,String... cols) throws Exception;
    public <T> HashMap<String,Object> findOrClauseWithRowCount(T obj, int page, int pageSize, String search, String... cols) throws Exception;
    public <T> HashMap<String,Object> whereClauseWithRowCount(T obj, int page, int pageSize, Object condition) throws Exception;
    public <T> HashMap<String,Object> whereClauseWithRowCount(T obj, int page, int pageSize, Object condition, Order order) throws Exception;

    public <T> Long count(T obj) throws Exception;
    public <T> Long count(T obj,String search,String... cols) throws Exception;

}
