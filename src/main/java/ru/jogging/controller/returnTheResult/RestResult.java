package ru.jogging.controller.returnTheResult;

public class RestResult {
    private EventType eventType;
    private Object mainObject;

    public RestResult(EventType eventType, Object mainObject) {
        this.eventType = eventType;
        this.mainObject = mainObject;
    }

    public EventType getEventType() {
        return eventType;
    }

    public Object getMainObject() {
        return mainObject;
    }

}
