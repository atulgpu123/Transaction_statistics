package com.n26.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.n26.service.StatisticsService;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsControllerTest {

	
    private ObjectMapper mapper;
    private MockMvc mockMvc;
    @Mock
    private StatisticsService statisticsService;
    @InjectMocks
    private StatisticsController statisticsController;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(statisticsController).build();
    }

    @Test
    public void shouldGetStatsSuccessfully() throws Exception {
        when(statisticsService.getStatistics())
                .thenReturn(StatisticsControllerFixture.statistics1);

        mockMvc.perform(get("/statistics"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(StatisticsControllerFixture.statistics1)));

        verify(statisticsService, times(1)).getStatistics();
    }
}
