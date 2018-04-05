package ru.jogging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.jogging.controller.returnTheResult.EventType;
import ru.jogging.controller.returnTheResult.FactoryRestResult;
import ru.jogging.controller.returnTheResult.RestResult;
import ru.jogging.exception.JoggingException;
import ru.jogging.exception.UserException;
import ru.jogging.service.JoggingService;

import java.util.Date;

@RestController
public class JoggingRestController {

    @Autowired
    private JoggingService joggingService;

    @Autowired
    @Qualifier("factoryRestResult")
    private FactoryRestResult factoryRestResult;

    @RequestMapping(value = "/jogginglist", method = RequestMethod.POST)
    public RestResult addJogging(@RequestParam(value = "date") Long date, @RequestParam(value = "minutes") double minutes) {
        try {
            return factoryRestResult.getSuccessResult(EventType.JOGGINDG, joggingService.addJogging(new Date(date), minutes));
        } catch (JoggingException | UserException e) {
            return factoryRestResult.getFailResult();
        }
    }

    @RequestMapping(value = "/jogginglist", method = RequestMethod.GET)
    public RestResult getJoggingList() {
        try {
            return factoryRestResult.getSuccessResult(EventType.JOGGINDGLIST, joggingService.getJoggingList());
        } catch (JoggingException | UserException e) {
            return factoryRestResult.getFailResult();
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public RestResult getTest() {
            return factoryRestResult.getSuccessResult(EventType.SUCCESS, true);
    }

    @RequestMapping(value = "/jogginglist/{id}", method = RequestMethod.DELETE)
    public RestResult deleteJogging(@PathVariable Long id) {
        try {
            joggingService.deleteJogging(id);
            return factoryRestResult.getSuccessResult(EventType.SUCCESS, true);
        } catch (JoggingException | UserException e) {
            return factoryRestResult.getFailResult();
        }
    }

    public JoggingService getJoggingService() {
        return joggingService;
    }

    public void setJoggingService(JoggingService joggingService) {
        this.joggingService = joggingService;
    }

    public FactoryRestResult getFactoryRestResult() {
        return factoryRestResult;
    }

    public void setFactoryRestResult(FactoryRestResult factoryRestResult) {
        this.factoryRestResult = factoryRestResult;
    }
}
