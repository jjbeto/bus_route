package com.goeuro.test.repository;

import com.goeuro.test.model.Route;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RouteRepositoryTest {

    @Autowired
    private RouteRepository routeRepository;

    @Test
    public void findOneExistingRoute() {
        List<Route> routesForStations = routeRepository.findRoutesForStations(0, 3);
        Assert.assertEquals(1, routesForStations.size());
    }

    @Test
    public void findTwoExistingRoutes() {
        List<Route> routesForStations = routeRepository.findRoutesForStations(0, 4);
        Assert.assertEquals(2, routesForStations.size());
    }

}
