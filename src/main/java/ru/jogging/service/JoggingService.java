package ru.jogging.service;

import ru.jogging.dao.DaoJogging;
import ru.jogging.exception.JoggingException;
import ru.jogging.exception.UserException;
import ru.jogging.model.MorningJogging;

import java.util.Date;
import java.util.List;

public interface JoggingService {
    MorningJogging addJogging(Date dateJogging, double numberOfMinutes) throws JoggingException, UserException;

    List<MorningJogging> getJoggingList() throws JoggingException, UserException;

    void deleteJogging(Long id) throws JoggingException, UserException;

    DaoJogging getDaoJogging();

    UserService getUserService();
}


