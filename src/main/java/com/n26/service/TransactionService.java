package com.n26.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.n26.enums.ErrorEnum;
import com.n26.exception.ApplicationException;
import com.n26.model.Transaction;
import com.n26.util.TimeUtil;

@Service
public class TransactionService {

	private StatisticsService statisticsService;
	private TimeUtil timeUtil;

	@Value("${transaction-time-in-seconds:60}")
	private long transactionTime;

	@Autowired
	public TransactionService(StatisticsService statisticsService, TimeUtil timeUtil) {
		this.statisticsService = statisticsService;
		this.timeUtil = timeUtil;
	}

	/**
	 * If transaction timestamp is older than configured then return false and if
	 * transaction is future date return error with 422 status
	 * 
	 * @param transaction
	 * @return
	 */
	private boolean isValidTransaction(Transaction transaction) {

		long currentTimestamp = timeUtil.currentTimestamp();
		long transactionTimestamp = transaction.getTimestamp();
		long differenceInMilliseconds = currentTimestamp - transactionTimestamp;

		// checks if the difference in seconds lies within a minute's range.
		if (differenceInMilliseconds < 0)
			throw new ApplicationException(ErrorEnum.ERROR_INVALID_TYPE, HttpStatus.UNPROCESSABLE_ENTITY.value());
		return differenceInMilliseconds < timeUtil.convertSecondsToMilliseconds(transactionTime);
	}

	/**
	 * If transaction is valid, then it makes call to update the statistics.
	 * 
	 * @param transaction
	 * @return
	 */
	public boolean addTransaction(Transaction transaction) {

		// If transaction is older than 60 seconds, then return false.
		if (!isValidTransaction(transaction))
			return false;

		statisticsService.upsertStatistics(transaction);
		return Boolean.TRUE;
	}

	public void deleteTransaction() {
		statisticsService.clearStatistics();

	}
}
