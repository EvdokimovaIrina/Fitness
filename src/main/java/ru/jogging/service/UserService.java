package ru.jogging.service;

import ru.jogging.exception.UserException;
import ru.jogging.model.User;

public interface UserService {
    User getUser(String name) throws UserException;

    User getCurrentUser() throws UserException;

}
