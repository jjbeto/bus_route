package com.goeuro.test.controller;

import com.goeuro.test.dto.ResultDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class ConsultController {

    @RequestMapping(value = "/direct", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResultDto direct(@RequestParam(value = "dep_sid", required = true) String departure, //
                            @RequestParam(value = "arr_sid", required = true) String arrival) {
        return new ResultDto();
    }

}
