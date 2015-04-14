package com.ase.dao;

import com.ase.domain.Base;
import com.ase.hibernate.HibernateUtil;
import com.ase.query.BaseQuery;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import java.util.Date;
import java.util.List;

public abstract class BaseDAO<E extends Base, Q extends BaseQuery> implements IGenericDAO<E, Long> {

    private Session _session;
    private FlushMode flushMode = FlushMode.COMMIT; // default flush mode

    @SuppressWarnings("unchecked")
    public E findByID(Long id) {
        try {
            return (E) getSession().get(getEntityClass(), id);
        } catch (ObjectNotFoundException e) {
            return null;
        }

    }

    public Long save(E newInstance) {
        newInstance.setLastUpdateTime(new Date());
        return (Long) getSession().save(newInstance);
    }

    public void update(E transientObject) {
        transientObject.setLastUpdateTime(new Date());
        getSession().update(transientObject);
    }

    public void saveOrUpdate(E transientObject) {
        //FIXME: This has to be the username of the actor performing the action.
        transientObject.setLastUpdatedBy("System");
        transientObject.setLastUpdateTime(new Date());
        getSession().saveOrUpdate(transientObject);
    }

    public void delete(E persistentObject) {
        HibernateUtil.getCurrentSession().delete(persistentObject);

    }

    protected Session getSession() {
        if (_session == null || !_session.isOpen()) {
            _session = HibernateUtil.getSessionFactory().getCurrentSession();
            if (null != flushMode)
                _session.setFlushMode(flushMode);
        }

        return _session;
    }

    protected abstract Class<E> getEntityClass();

    /**
     * Most implementation will need to Override this method
     *
     * @param query
     * @return
     */
    protected Criteria makeCriteria(Q query) {
        Criteria c = createCriteria();
        return c;
    }

    protected Criteria createCriteria() {
        return getSession().createCriteria(getEntityClass());
    }

    public void setFlushMode(FlushMode flushMode) {
        this.flushMode = flushMode;
    }

    public FlushMode getFlushMode() {
        return flushMode;
    }

    @SuppressWarnings("unchecked")
    public List<E> getAll() {
        Criteria c = createCriteria();
        return c.list();
    }

    public int getCount() {
        Criteria c = createCriteria();
        c.setProjection(Projections.rowCount());
        Long count = (Long) c.uniqueResult();
        return count.intValue();
    }

    public List<E> get(int start, int max) {
        Criteria c = createCriteria();

        c.addOrder(Order.desc("id"));
        c.setFirstResult(start);
        c.setMaxResults(max);

        return (List<E>) c.list();

    }

    public int getCount(Q query) {
        Criteria c = makeCriteria(query);
        c.setProjection(Projections.rowCount());
        Long count = (Long) c.uniqueResult();
        return (count != null) ? count.intValue() : 0;
    }

    public List<E> get(Q query) {
        Criteria c = makeCriteria(query);
        c.addOrder(Order.desc("id"));

        if (null != query.getStart())
            c.setFirstResult(query.getStart());

        if (null != query.getLimit())
            c.setMaxResults(query.getLimit());
        else
            c.setMaxResults(1);

        return (List<E>) c.list();
    }

    public List<E> getAll(Q query) {
        Criteria c = makeCriteria(query);
        c.addOrder(Order.desc("id"));
        return (List<E>) c.list();
    }


}
