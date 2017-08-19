package com.goeuro.test.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasToString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConsultControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testFound() throws Exception {
        mockMvc.perform(get("/api/direct?dep_sid=3&arr_sid=6"))//
                .andExpect(MockMvcResultMatchers.status().isOk())//
                .andExpect(MockMvcResultMatchers.jsonPath("$.dep_sid", hasToString("3")))//
                .andExpect(MockMvcResultMatchers.jsonPath("$.arr_sid", hasToString("6")))//
                .andExpect(MockMvcResultMatchers.jsonPath("$.direct_bus_route", hasToString("true")))//
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testNotFound() throws Exception {
        mockMvc.perform(get("/api/direct?dep_sid=3&arr_sid=8"))//
                .andExpect(MockMvcResultMatchers.status().isOk())//
                .andExpect(MockMvcResultMatchers.jsonPath("$.dep_sid", hasToString("3")))//
                .andExpect(MockMvcResultMatchers.jsonPath("$.arr_sid", hasToString("8")))//
                .andExpect(MockMvcResultMatchers.jsonPath("$.direct_bus_route", hasToString("false")))//
                .andDo(MockMvcResultHandlers.print());
    }

}
