package com.goeuro.test.service;

import com.goeuro.test.dto.ResultDto;
import com.goeuro.test.model.Route;
import com.goeuro.test.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public ResultDto checkStations(Integer departure, Integer arrival) {
        List<Route> routesForStations = routeRepository.findRoutesForStations(departure, arrival);
        ResultDto result = new ResultDto();
        result.setDep_sid(departure);
        result.setArr_sid(arrival);
        result.setDirect_bus_route(routesForStations.size() > 0);
        return result;
    }

}
