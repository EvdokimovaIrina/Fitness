package ru.jogging.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.jogging.exception.UserException;
import ru.jogging.model.User;

import java.util.List;


public class DaoUserImpl extends DaoGeneral implements DaoUser {

    public DaoUserImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User getUserByName(String name) throws UserException {
        User user;
        Session session = super.getSessionFactory().getCurrentSession();

        try {
            Query query = session.createQuery("from User  where login = :paramName");
            query.setParameter("paramName", name);
            List<User> userList = (List<User>) query.list();
            if (userList.size() > 0) {
                user = userList.get(0);
            } else {
                user = null;
            }
            return user;
        } catch (Exception e) {
            throw new UserException("Ошибка получения данных пользователя ", e);
        }
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
