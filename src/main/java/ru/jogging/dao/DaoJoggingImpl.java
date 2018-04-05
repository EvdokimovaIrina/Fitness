package ru.jogging.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.jogging.exception.JoggingException;
import ru.jogging.model.MorningJogging;
import ru.jogging.model.User;

import java.util.List;


public class DaoJoggingImpl extends DaoGeneral implements DaoJogging {

    public DaoJoggingImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void addJogging(MorningJogging jogging) throws JoggingException {
        try {
            saveObgectToBD(jogging);
        } catch (Exception e) {
            throw new JoggingException("Ошибка при добавлении данных о пробежке ", e);
        }
    }

    public void updateJoggingToBD(MorningJogging jogging) throws JoggingException {
        try {
            saveObgectToBD(jogging);
        } catch (Exception e) {
            throw new JoggingException("Ошибка при изменении данных о пробежке ", e);
        }
    }

    public MorningJogging findByIdJogging(Long id) throws JoggingException {
        try {
            return findByIdObject(MorningJogging.class, id);
        } catch (Exception e) {
            throw new JoggingException("Ошибка получения данных о пробежке ", e);
        }
    }

    public List<MorningJogging> getJoggingListUser(Long id) throws JoggingException {
        try {
            Session session = super.getSessionFactory().getCurrentSession();
            List<MorningJogging> joggingList;
            Transaction tx = session.beginTransaction();
            User user = (User) session.load(User.class, id);
            joggingList = user.getMorningJoggingList();
            joggingList.size();
            tx.commit();
            return joggingList;
        } catch (Exception e) {
            throw new JoggingException("Ошибка получения списка пробежек ", e);
        }
    }

    public void deleteJogging(Long id) throws JoggingException {
        try {
            deleteObgectFromBD(MorningJogging.class, id);
        } catch (Exception e) {
            throw new JoggingException("Ошибка при удалении данных о пробежке ", e);
        }
    }
}
