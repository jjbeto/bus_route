package com.goeuro.test.controller;

import com.goeuro.test.dto.ResultDto;
import com.goeuro.test.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class ConsultController {

    @Autowired
    private RouteService routeService;

    @RequestMapping(value = "/direct", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResultDto direct(@RequestParam(value = "dep_sid", required = true) Integer departure, //
                            @RequestParam(value = "arr_sid", required = true) Integer arrival) {
        return routeService.checkStations(departure, arrival);
    }

}
