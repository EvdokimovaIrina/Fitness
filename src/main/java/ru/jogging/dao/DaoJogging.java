package ru.jogging.dao;

import org.hibernate.SessionFactory;
import ru.jogging.exception.JoggingException;
import ru.jogging.model.MorningJogging;

import java.util.List;

public interface DaoJogging {

    /**
     * Запись нового объекта в БД
     * @param jogging объект для записи
     */
    void addJogging(MorningJogging jogging) throws JoggingException;

    /**
     * Запись нового объекта в БД
     * @param jogging объект для записи
     */
    void updateJoggingToBD(MorningJogging jogging) throws JoggingException;

    MorningJogging findByIdJogging(Long id) throws JoggingException;

    List<MorningJogging> getJoggingListUser(Long id) throws JoggingException;

    void deleteJogging (Long id) throws JoggingException;

}
