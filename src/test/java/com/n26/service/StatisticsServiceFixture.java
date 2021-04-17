package com.n26.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.DoubleSummaryStatistics;

import com.n26.model.Statistics;
import com.n26.model.Transaction;

public class StatisticsServiceFixture {

    static final Statistics statistics1 = new Statistics(new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), 0);

    private static final BigDecimal amount1 = new BigDecimal(10);
    private static final long timestampWithinAMinuteInMilliseonds = Instant.now().toEpochMilli() - 10000;
    static final Transaction transactionWithinAMinute = new Transaction(amount1, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .withZone(ZoneOffset.UTC)
            .format(Instant.ofEpochMilli(timestampWithinAMinuteInMilliseonds)));
    static final Statistics statistics2 = new Statistics(new BigDecimal(10), new BigDecimal(10), new BigDecimal(10), new BigDecimal(10), 1);

    private static final long amount2 = 20;
    static final DoubleSummaryStatistics doubleSummaryStatistics = new DoubleSummaryStatistics();
    static final Statistics statistics3 = new Statistics(new BigDecimal(30), new BigDecimal(10), new BigDecimal(10), new BigDecimal(10), 3);

    static final long staleTimestamp = 20l;
    static final Transaction staleTransaction = new Transaction(new BigDecimal(10), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .withZone(ZoneOffset.UTC)
            .format(Instant.ofEpochMilli(staleTimestamp)));
    static final Statistics statistics4 = new Statistics(new BigDecimal(20), new BigDecimal(10), new BigDecimal(10), new BigDecimal(10), 2);

    static {
        doubleSummaryStatistics.accept(amount2);
    }
}
