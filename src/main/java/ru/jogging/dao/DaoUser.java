package ru.jogging.dao;

import org.hibernate.SessionFactory;
import ru.jogging.exception.JoggingException;
import ru.jogging.exception.UserException;
import ru.jogging.model.User;

import java.util.List;

public interface DaoUser {
    User getUserByName(String name) throws UserException;

    String getCurrentUsername();

}
