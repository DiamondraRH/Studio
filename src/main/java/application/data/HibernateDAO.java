package application.data;

import application.data.generic.Order;
import application.models.Scene;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;
import application.data.generic.*;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HibernateDAO implements InterfaceDAO {
    public static final String RECHERCHE_RESULTS_KEY="results";
    public static final String NUMBER_PAGES="nbrpage";
    private SessionFactory sessionFactory;
    private Transaction transaction;

    private static final int PAGE_SIZE = 10;

    public HibernateDAO() {
    }

    public HibernateDAO(SessionFactory sessionFactory, Transaction transaction) {
        this.sessionFactory = sessionFactory;
        this.transaction = transaction;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    private void initSessionFactory() {
        Configuration config = new Configuration();
        this.sessionFactory = config.configure().buildSessionFactory();
    }

    private Session getSession() {
//        if (this.sessionFactory==null) initSessionFactory();
        return getSessionFactory().openSession();
    }

    /*private static String retrieveTableNameFromClass(Object obj) throws Exception {
        Table annot = obj.getClass().getAnnotation(Table.class);
        if (!annot.name().isBlank()) return annot.name();
        throw new Exception("No table name in the annotaion Table for the class " + obj.getClass());
    }

    public static String getTableName(Object obj) throws Exception {
        if (obj.getClass().isAnnotationPresent(Table.class)) return retrieveTableNameFromClass(obj);
        throw new Exception("No annotation Table for the class " + obj.getClass());
    }*/

    private static String getEntityName(Object obj) {
        return obj.getClass().getSimpleName();
    }

    @Override
    public <T> ArrayList<T> findAll(T obj) throws Exception {
        Session session = null;
        CriteriaBuilder cb = null;
        CriteriaQuery<?> criteria = null;
        ArrayList<T> list = null;
        try {
            Class<?> c = obj.getClass();
            session = getSession();
            cb = session.getCriteriaBuilder();
            criteria = cb.createQuery(c);
            criteria.from(c);
            TypedQuery<?> tq = session.createQuery(criteria);
            list = (ArrayList<T>) tq.getResultList();
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public <T> ArrayList<T> findAll(T obj, int pagination, int pageSize) throws Exception {
        Session session = null;
        ArrayList<T> list = null;
        int begin_row = pagination * pageSize - 1;
        try {
            Class<?> c = obj.getClass();
            session = getSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<?> criteria = cb.createQuery(c);
            criteria.from(c);
            TypedQuery<?> tq = session.createQuery(criteria);
            tq.setFirstResult(begin_row);
            tq.setMaxResults(pageSize);
            list = (ArrayList<T>) tq.getResultList();
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public <T> ArrayList<T> findAll(T obj, int pagination) throws Exception {
        return findAll(obj, pagination, PAGE_SIZE);
    }

    @Override
    public <T> T findOneById(T obj, Object id) throws Exception {
        Session s = null;
        try {
            s = getSession();
            return (T) s.get(obj.getClass(), (Serializable) id);
        } catch (Exception e) {
            throw e;
        } finally {
            if (s != null) s.close();
        }
    }

    @Override
    public <T> int save(T obj) throws Exception {
        Session s = null;
        Transaction t = null;
        try {
            s = getSession();
            t = s.beginTransaction();
            int n = (Integer) s.save(obj);
            t.commit();
            return n;
        } catch (Exception e) {
            if (t != null) t.rollback();
            throw e;
        } finally {
            if (s != null) s.close();
        }
    }

    @Override
    public <T> int delete(T obj, Object id) throws Exception {
        Session s = null;
        Transaction t = null;
        try {
            s = getSession();
            t = s.beginTransaction();
            T ob = findOneById(obj, id);
            if (ob == null) return 0;
            s.delete(ob);
            t.commit();
            return 1;
        } catch (Exception e) {
            if (t != null) t.rollback();
            throw e;
        } finally {
            if (s != null) s.close();
        }
    }

    @Override
    public <T> int update(T newObj) throws Exception {

        Session s = null;
        Transaction t = null;
        try {
            s = getSession();
            t = s.beginTransaction();
            s.saveOrUpdate(newObj);
            t.commit();
            return 1;
        } catch (Exception e) {
            if (t != null) t.rollback();
            throw e;
        } finally {
            if (s != null) s.close();
        }
    }

    @Override
    public <T> ArrayList<T> find(T obj, boolean orClause) throws Exception {
        Session s = null;
        Example example = null;
        try {
            s = getSession();
            example = Example.create(obj).ignoreCase().excludeZeroes();
            if (orClause) example = example.enableLike(MatchMode.ANYWHERE);
            return (ArrayList<T>) s.createCriteria(obj.getClass()).add(example).list();
        } catch (Exception e) {
            throw e;
        } finally {
            if (s != null) s.close();
        }
    }

    @Override
    public <T> ArrayList<T> find(T obj) throws Exception {
        return find(obj, false);
    }

    @Override
    public <T> ArrayList<T> findOrClause(T obj, String search, String... cols) throws Exception {
        Session s = getSession();
        Criterion[] criterions = new Criterion[cols.length];
        for (int i = 0; i < cols.length; i++) {
            criterions[i] = Restrictions.like(cols[i], "%" + search + "%");
        }
        Criterion criterion = Restrictions.or(criterions);
        return (ArrayList<T>) s.createCriteria(obj.getClass()).add(criterion).list();
    }


    @Override
    public <T> ArrayList<T> findOrClause(T obj, int page, int pageSize, String search, String... cols) throws Exception {
        Session s = null;
        try {
            s=getSession();
            int begin_row = page * pageSize - 1;
            Criteria criteria=s.createCriteria(obj.getClass()).add(provideOrCriterions(search,cols));
            return (ArrayList<T>) criteria.setFirstResult(begin_row).setMaxResults(pageSize).list();
        }catch (Exception e){
            throw e;
        }finally {
            if(s!=null) s.close();
        }
    }

    private Criterion provideOrCriterions(String search,String[] cols){
        Criterion[] criterions = new Criterion[cols.length];
        for (int i = 0; i < cols.length; i++) {
            System.out.println(cols[i]+",%" + search + "%");
            criterions[i] = Restrictions.like(cols[i], "%" + search + "%");
        }
        return Restrictions.or(criterions);
    }
    @Override
    public <T> HashMap<String, Object> findOrClauseWithRowCount(T obj, int page, int pageSize, String search, String... cols) throws Exception {
        HashMap<String,Object> map=new HashMap<>();
        Session s=null;
        try{
            s=getSession();
            int begin_row = page*pageSize-(pageSize);
            System.out.println("BEGIN ROW="+begin_row);
            System.out.println("MAX RESULT="+pageSize);
            Criteria criteria_result=s.createCriteria(obj.getClass()).add(provideOrCriterions(search,cols)).setFirstResult(begin_row).setMaxResults(pageSize);
            Criteria criteria_count= s.createCriteria(obj.getClass()).add(provideOrCriterions(search,cols)).setProjection(Projections.rowCount());
            map.put(NUMBER_PAGES,((Number)criteria_count.uniqueResult()).intValue()/pageSize);
            map.put(RECHERCHE_RESULTS_KEY,(ArrayList<T>) criteria_result.list());
            return map;
        }catch (Exception e){
            throw e;
        }finally {
            if(s!=null)s.close();
        }
    }

    @Override
    public <T> HashMap<String, Object> whereClauseWithRowCount(T obj, int page, int pageSize, Object condition) throws Exception {
        HashMap<String,Object> map=new HashMap<>();
        Session s=null;
        Criterion criterion=null;
        try{
            s=getSession();
            int begin_row = page*pageSize-(pageSize);
            System.out.println("BEGIN ROW="+begin_row);
            System.out.println("MAX RESULT="+pageSize);
            if (condition instanceof Criterion) {
                criterion=(Criterion) condition;
            }
            Criteria criteria_result=s.createCriteria(obj.getClass()).add(criterion).setFirstResult(begin_row).setMaxResults(pageSize);
            Criteria criteria_count= s.createCriteria(obj.getClass()).add(criterion).setProjection(Projections.rowCount());
            map.put(NUMBER_PAGES,((Number)criteria_count.uniqueResult()).intValue()/pageSize);
            map.put(RECHERCHE_RESULTS_KEY,(ArrayList<T>) criteria_result.list());
            return map;
        }catch (Exception e){
            throw e;
        }finally {
            if(s!=null)s.close();
        }
    }


    @Override
    public <T> HashMap<String, Object> whereClauseWithRowCount(T obj, int page, int pageSize, Object condition, Order order) throws Exception {
        HashMap<String,Object> map=new HashMap<>();
        Session s=null;
        Criterion criterion=null;
        try{
            s=getSession();
            int begin_row = page*pageSize-(pageSize);
            System.out.println("BEGIN ROW="+begin_row);
            System.out.println("MAX RESULT="+pageSize);
            if (condition instanceof Criterion) {
                criterion=(Criterion) condition;
            }
            Criteria criteria_result=s.createCriteria(obj.getClass()).add(criterion).setFirstResult(begin_row).setMaxResults(pageSize);
            if (order!=null)order.setOrder(criteria_result);
            Criteria criteria_count= s.createCriteria(obj.getClass()).add(criterion).setProjection(Projections.rowCount());
            map.put(NUMBER_PAGES,((Number)criteria_count.uniqueResult()).intValue()/pageSize);
            map.put(RECHERCHE_RESULTS_KEY,(ArrayList<T>) criteria_result.list());
            return map;
        }catch (Exception e){
            throw e;
        }finally {
            if(s!=null)s.close();
        }
    }

    @Override
    public <T> Long count(T obj) throws Exception {
        Session s = getSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(obj.getClass())));
        return s.createQuery(cq).getSingleResult();
    }

    @Override
    public <T> Long count(T obj, String search, String... cols) throws Exception {
        return null;
    }

    
    public List<Scene> findAllUnplannedScene() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Scene> criteriaQuery = builder.createQuery(Scene.class);
        // init query
        Root<Scene> model = criteriaQuery.from(Scene.class);
        // condition(s)
        criteriaQuery.where(builder.isNull(model.get("debutTournage")));
        // ordering
        // criteriaQuery.orderBy(builder.desc(model.get("projet").get("id_projet")));
        
        return session.createQuery(criteriaQuery).getResultList();
    }
}
