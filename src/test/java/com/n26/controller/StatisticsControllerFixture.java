package com.n26.controller;

import java.math.BigDecimal;

import com.n26.model.Statistics;

public class StatisticsControllerFixture {

    static final Statistics statistics1 = new Statistics(new BigDecimal(10),
    		new BigDecimal(10), new BigDecimal(10), new BigDecimal(10), 1);
}
