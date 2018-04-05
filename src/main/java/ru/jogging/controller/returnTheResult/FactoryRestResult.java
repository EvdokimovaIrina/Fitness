package ru.jogging.controller.returnTheResult;

/**
 *
 */
public interface FactoryRestResult {
    RestResult getSuccessResult(EventType eventType, Object object);

    RestResult getFailResult();
}
