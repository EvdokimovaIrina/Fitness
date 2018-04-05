package ru.jogging.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class DaoGeneral {

    private SessionFactory sessionFactory;

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public DaoGeneral(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public <T> T findByIdObject(Class<T> clazz, Long id) throws Exception {
        T object = null;
        Session session = sessionFactory.getCurrentSession();
        object = clazz.cast(session.load(clazz, id));
        return object;
    }

    public void saveObgectToBD(Object object) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(object);
    }

    public void deleteObgectFromBD(Class clazz, long id) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.delete(clazz.cast(session.load(clazz, id)));
        tx.commit();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
