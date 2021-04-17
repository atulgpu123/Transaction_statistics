package com.n26.service;

import java.util.DoubleSummaryStatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.n26.model.Statistics;
import com.n26.model.Transaction;
import com.n26.util.TimeUtil;
import com.n26.vo.StatisticVO;

@Service
public class StatisticsService {

	private StatisticVO statisticVO;
	private TimeUtil timeUtil;
	@Value("${transaction-time-in-seconds:60}")
	private long transactionTime;

	@Autowired
	public StatisticsService(StatisticVO statisticVO, TimeUtil timeUtil) {
		this.statisticVO = statisticVO;
		this.timeUtil = timeUtil;
	}

	private DoubleSummaryStatistics statsResult = new DoubleSummaryStatistics();;

	/**
	 * For the first time, newly initialized stats is return. Hence forth it returns
	 * precomputed statistics which is stored in memory This is done in O(1) time &
	 * space.
	 * 
	 * @return
	 */
	public Statistics getStatistics() {

		if (statisticVO.getSize() == 0)
			return new Statistics();

		return new Statistics(statsResult);
	}

	/**
	 * Uses the stats on per second basis
	 * 
	 * @param transaction
	 * @return
	 */
	public Statistics upsertStatistics(Transaction transaction) {

		long key = timeUtil.convertMillisecondsToSeconds(transaction.getTimestamp());
		DoubleSummaryStatistics doubleSummaryStatistics;

		if (statisticVO.contains(key))
			doubleSummaryStatistics = statisticVO.get(key);
		else
			doubleSummaryStatistics = new DoubleSummaryStatistics();
		doubleSummaryStatistics.accept(transaction.getAmount());
		statisticVO.add(key, doubleSummaryStatistics);
		synchronized (this) {
			statsResult.accept(transaction.getAmount());
		}

		return new Statistics(statsResult);
	}

	/**
	 * scheduled task which runs every second and clears the stored which are older
	 * than 60 seconds
	 * 
	 * @return
	 */
	@Async
	@Scheduled(fixedRate = 1000)
	public DoubleSummaryStatistics clearStaleStatistics() {

		// get key for records which has passed 60 seconds window
		long staleKey = timeUtil.currentTimeInSeconds() - transactionTime;

		if (statisticVO.contains(staleKey)) {
			statisticVO.remove(staleKey);
			synchronized (this) {
				statsResult = calculateStatisticsResult();
			}
		}

		return statsResult;
	}

	/**
	 * Recalculates the resultant stats based upon all the stored stats from dao.
	 * 
	 * @return
	 */
	private DoubleSummaryStatistics calculateStatisticsResult() {
		DoubleSummaryStatistics newDoubleSummaryStatistics = new DoubleSummaryStatistics();
		statisticVO.getAll().stream().forEach(result -> newDoubleSummaryStatistics.combine(result));
		return newDoubleSummaryStatistics;
	}

	/**
	 * clear map element
	 * 
	 * @return
	 */
	public void clearStatistics() {
		statisticVO.clear();
		synchronized (this) {
			statsResult = new DoubleSummaryStatistics();
		}
	}
}
