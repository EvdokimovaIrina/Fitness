package ru.jogging.service;

import ru.jogging.dao.DaoJogging;
import ru.jogging.exception.JoggingException;
import ru.jogging.exception.UserException;
import ru.jogging.model.MorningJogging;
import ru.jogging.model.User;

import java.util.Date;
import java.util.List;

public class JoggingServiceImpl implements JoggingService {

    private DaoJogging daoJogging;

    private UserService userService;

    public JoggingServiceImpl(DaoJogging daoJogging, UserService userService) {
        this.daoJogging = daoJogging;
        this.userService = userService;
    }

    public MorningJogging addJogging(Date dateJogging, double numberOfMinutes) throws JoggingException, UserException {
        MorningJogging jogging = new MorningJogging();
        jogging.setUser(userService.getCurrentUser());
        jogging.setDateJogging(dateJogging);
        jogging.setNumberOfMinutes(numberOfMinutes);
        synchronized (this) {
            daoJogging.addJogging(jogging);
        }
        return jogging;
    }

    public List<MorningJogging> getJoggingList() throws JoggingException, UserException {
        User user = userService.getCurrentUser();
        synchronized (this) {
            return daoJogging.getJoggingListUser(user.getId());
        }
    }

    public void deleteJogging(Long id) throws JoggingException, UserException {
        synchronized (this) {
            List<MorningJogging> joggingList = getJoggingList();
            for (MorningJogging jogging : joggingList) {
                if (jogging.getId() != null && jogging.getId().equals(id)) {
                    daoJogging.deleteJogging(id);
                    return;
                }
            }
        }
    }

    public DaoJogging getDaoJogging() {
        return daoJogging;
    }

    public UserService getUserService() {
        return userService;
    }
}
