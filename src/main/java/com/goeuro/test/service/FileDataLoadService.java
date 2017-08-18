package com.goeuro.test.service;


import com.goeuro.test.dto.ResumeDto;
import com.goeuro.test.exception.InvalidDataException;
import com.goeuro.test.model.Route;
import com.goeuro.test.model.Station;
import com.goeuro.test.repository.RouteRepository;
import com.goeuro.test.repository.StationRepository;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

@Service
public class FileDataLoadService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private StationRepository stationRepository;

    @Transactional
    public void saveLine(Integer routeId, List<Integer> stations) {
        if (routeId == null || stations == null || stations.size() < 2) {
            throw new InvalidDataException();
        }
        Route route = new Route();
        route.setId(routeId);
        route.setStations(new ArrayList<>());
        for (Integer stationId : stations) {
            Station station = new Station();
            station.setId(stationId);
            route.getStations().add(stationRepository.save(station));
        }
        routeRepository.save(route);
    }

    public void loadData(String path) {
        final ResumeDto resume = new ResumeDto();
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.forEach(each -> loadLine(each, resume));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (resume.getTotalLinesInformed() != resume.getTotalLinesFound()) {
            throw new InvalidDataException();
        }
    }

    protected void loadLine(String line, ResumeDto resume) {
        if (resume.isHeaderLoaded()) {
            StringTokenizer token = new StringTokenizer(line, " ");
            Integer routeId = null;
            List<Integer> stations = new ArrayList<>();
            while (token.hasMoreTokens()) {
                String item = token.nextToken();
                if (NumberUtils.isNumber(item)) {
                    int numberFound = Integer.parseInt(item);
                    if (routeId == null) {
                        routeId = numberFound;
                    } else {
                        stations.add(numberFound);
                    }
                }
            }
            saveLine(routeId, stations);
            resume.setTotalLinesFound(resume.getTotalLinesFound() + 1);
        } else {
            resume.setTotalLinesInformed(Integer.parseInt(line));
            resume.setHeaderLoaded(true);
        }
    }

}
