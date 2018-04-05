package ru.jogging.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.jogging.dao.DaoUser;
import ru.jogging.exception.JoggingException;
import ru.jogging.exception.UserException;
import ru.jogging.model.User;

public class UserServiceImpl implements UserService {

    private DaoUser daoUser;

    public UserServiceImpl(DaoUser daoUser) {
        this.daoUser = daoUser;
    }


    private String getCurrentUserName() {
        synchronized (this) {
            return daoUser.getCurrentUsername();
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public User getCurrentUser() throws UserException {
        synchronized (this) {
            return daoUser.getUserByName(getCurrentUserName());
        }
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public User getUser(String name) throws UserException {
        synchronized (this) {
            return daoUser.getUserByName(name);
        }
    }


    public DaoUser getDaoUser() {
        return daoUser;
    }

}
