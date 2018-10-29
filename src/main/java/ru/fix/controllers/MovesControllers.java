package ru.fix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fix.models.Information;
import ru.fix.services.CountService;

@RestController
public class MovesControllers {

    @Autowired
    private Information information;

    @Autowired
    private CountService countService;

    @RequestMapping(value = "/hourse/rest/count", method = RequestMethod.GET, produces = "application/json")
    public Information getCountMoves(@RequestParam(name = "width") String width,
                                     @RequestParam(name = "height") String height,
                                     @RequestParam(name = "start") String start,
                                     @RequestParam(name = "end") String end) {

        information.setAnswer(countService.calculateMoves(width, height, start, end));
        return information;
    }
}
