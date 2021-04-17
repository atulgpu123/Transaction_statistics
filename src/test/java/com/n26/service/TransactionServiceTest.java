package com.n26.service;

import static org.mockito.Mockito.when;

import java.time.Instant;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.n26.model.Transaction;
import com.n26.util.TimeUtil;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {
	

    @Mock
    private TimeUtil timeUtilMock;
    @Mock
    private StatisticsService statisticsService;
    @InjectMocks
    private TransactionService transactionService;

    @Before
    public void initialize() {
        when(timeUtilMock.currentTimestamp()).thenReturn(Instant.now().toEpochMilli());
    }

    /**
     * Transaction is added as the transaction's timestamp within a minute.
     */
    @Test
    public void shouldBeAbleSuccessfullyAddTransaction() {
        Transaction transactionWithinAMinute = TransactionServiceFixture.transactionWithinAMinute;
        boolean actualResult = transactionService.addTransaction(transactionWithinAMinute);
        Assert.assertTrue(actualResult);
    }

    /**
     * Transaction is not added as it falls prior to a minute's range.
     */
    @Test
    public void shouldNotBeAbleAddTransaction1() {
        Transaction transactionOlderThanAMinute = TransactionServiceFixture.transactionOlderThanAMinute;
        boolean actualResult = transactionService.addTransaction(transactionOlderThanAMinute);
        Assert.assertFalse(actualResult);
    }

    /**
     * Transaction is not added as the transaction's timestamp falls in future.
     */
    @Test
    public void shouldNotBeAbleAddTransaction2() {
        Transaction transactionOfFuture = TransactionServiceFixture.transactionOfFuture;
        boolean actualResult = transactionService.addTransaction(transactionOfFuture);
        Assert.assertFalse(actualResult);
    }
}
