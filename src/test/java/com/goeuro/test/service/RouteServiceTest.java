package com.goeuro.test.service;

import com.goeuro.test.dto.ResultDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RouteServiceTest {

    @Autowired
    private RouteService routeService;

    @Test
    public void testStationsFound() {
        int departure = 0;
        int arrival = 2;
        ResultDto resultDto = routeService.checkStations(departure, arrival);

        Assert.assertEquals(departure, resultDto.getDep_sid());
        Assert.assertEquals(arrival, resultDto.getArr_sid());
        Assert.assertTrue(resultDto.isDirect_bus_route());
    }

    @Test
    public void testStationsNotFound() {
        int departure = 0;
        int arrival = 7;
        ResultDto resultDto = routeService.checkStations(departure, arrival);

        Assert.assertEquals(departure, resultDto.getDep_sid());
        Assert.assertEquals(arrival, resultDto.getArr_sid());
        Assert.assertFalse(resultDto.isDirect_bus_route());
    }

}
