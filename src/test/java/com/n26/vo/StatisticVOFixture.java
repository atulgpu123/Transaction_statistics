package com.n26.vo;

import java.time.Instant;
import java.util.DoubleSummaryStatistics;

public class StatisticVOFixture {

    static final long keyInSeconds1 = Instant.now().toEpochMilli() / 1000;
    static final DoubleSummaryStatistics doubleSummaryStatistics1 = new DoubleSummaryStatistics();

    static final long keyInSeconds2 = (Instant.now().toEpochMilli() + 5000) / 1000;
    static final DoubleSummaryStatistics doubleSummaryStatistics2 = new DoubleSummaryStatistics();
}
