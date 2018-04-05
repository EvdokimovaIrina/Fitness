package ru.jogging.service;

import ru.jogging.dao.DaoUser;
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

    public User getCurrentUser() throws UserException {
        synchronized (this) {
            return daoUser.getUserByName(getCurrentUserName());
        }
    }


    public User getUser(String name) throws UserException {
        synchronized (this) {
            return daoUser.getUserByName(name);
        }
    }


    public DaoUser getDaoUser() {
        return daoUser;
    }

}
