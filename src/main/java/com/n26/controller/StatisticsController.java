package com.n26.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.n26.model.Statistics;
import com.n26.service.StatisticsService;

@RestController
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    /**
     * Gets the statistics .
     * @return
     */
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public Statistics getStatistics() {
        return statisticsService.getStatistics();
    }
}
