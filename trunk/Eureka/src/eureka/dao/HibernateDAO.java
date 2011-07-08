package eureka.dao;

import eureka.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;

public abstract class HibernateDAO<ID extends Serializable, E> implements
        AbstractDAO<ID, E> {


    private Class<E> persistentClass;

    public HibernateDAO(Class<E> persistentClass) {
        this.persistentClass = persistentClass;
    }

    protected Session getSession() {
        return HibernateUtil.getCurrentSession();
    }
    
    @Override
    public void saveOrUpdate(E obj) {
        getSession().saveOrUpdate(obj);
    }

    @Override
    public void remove(E obj) {
        getSession().delete(obj);
    }

    @Override
    public List<E> findAll() {
        Criteria crit = getSession().createCriteria(persistentClass);
        return crit.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public E findById(ID id) {
        return (E) getSession().get(persistentClass, id);
    }
}