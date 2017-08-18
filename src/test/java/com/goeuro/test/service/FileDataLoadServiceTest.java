package com.goeuro.test.service;

import com.goeuro.test.dto.ResumeDto;
import com.goeuro.test.exception.InvalidDataException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FileDataLoadServiceTest {

    @Autowired
    private FileDataLoadService fileDataLoadService;

    @Test(expected = InvalidDataException.class)
    public void saveNewRouteWithoutStations() {
        fileDataLoadService.saveLine(100, null);
    }

    @Test(expected = InvalidDataException.class)
    public void saveNewRouteWithoutId() {
        List<Integer> stations = new ArrayList<>();
        stations.add(100);
        fileDataLoadService.saveLine(null, stations);
    }

    @Test(expected = InvalidDataException.class)
    public void saveNewRouteWithOnly1Station() {
        List<Integer> stations = new ArrayList<>();
        stations.add(100);
        fileDataLoadService.saveLine(100, stations);
    }

    @Test
    public void saveNewRoute() {
        List<Integer> stations = new ArrayList<>();
        stations.add(100);
        stations.add(101);
        fileDataLoadService.saveLine(100, stations);
    }

    @Test
    public void loadCommonLine() {
        ResumeDto resumeDto = new ResumeDto();
        resumeDto.setHeaderLoaded(true);
        String line = "100 200 300";
        fileDataLoadService.loadLine(line, resumeDto);
        Assert.assertEquals(1, resumeDto.getTotalLinesFound());
    }

    @Test
    public void loadHeaderLine() {
        ResumeDto resumeDto = new ResumeDto();
        String line = "10";
        fileDataLoadService.loadLine(line, resumeDto);
        Assert.assertEquals(10, resumeDto.getTotalLinesInformed());
    }

    @Test
    public void loadFile() {
        URL resource = getClass().getClassLoader().getResource("test_data_1.txt");
        fileDataLoadService.loadData(resource.getFile());
    }

}
