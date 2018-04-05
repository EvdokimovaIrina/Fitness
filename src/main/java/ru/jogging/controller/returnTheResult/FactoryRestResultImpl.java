package ru.jogging.controller.returnTheResult;

import ru.jogging.model.MorningJogging;

import java.util.List;

public class FactoryRestResultImpl implements FactoryRestResult {

    public FactoryRestResultImpl() {
    }

    public RestResult getSuccessResult(EventType eventType, Object object) {
        try {
            if (object != null) {
                switch (eventType) {
                    case JOGGINDG:
                        MorningJogging jogging = (MorningJogging) object;
                        return new RestResult(eventType, jogging);
                    case JOGGINDGLIST:
                        List<MorningJogging> joggingList = (List<MorningJogging>) object;
                        return new RestResult(eventType, joggingList);
                    case SUCCESS:
                        return new RestResult(eventType, true);
                    case ERROR:
                        return getFailResult();
                }
            }
        } catch (Exception e) {
            return getFailResult();
        }
        return getFailResult();
    }


    public RestResult getFailResult() {
        return new RestResult(EventType.ERROR, "Ошибка получения данных");
    }
}
