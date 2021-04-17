package com.n26.vo;

import java.util.DoubleSummaryStatistics;

import org.junit.Assert;
import org.junit.Test;

public class StatisticVOTest {

	@Test
	public void shouldBeAbleToInsert() {
		StatisticVO inMemoryStatisticsDao = new StatisticVO();
		inMemoryStatisticsDao.add(StatisticVOFixture.keyInSeconds1, StatisticVOFixture.doubleSummaryStatistics1);
		Assert.assertEquals(StatisticVOFixture.doubleSummaryStatistics1,
				inMemoryStatisticsDao.get(StatisticVOFixture.keyInSeconds1));
		Assert.assertEquals(1, inMemoryStatisticsDao.getAll().size());
	}

	@Test
	public void shouldBeAbleToDelete() {
		StatisticVO inMemoryStatisticsDao = new StatisticVO();
		inMemoryStatisticsDao.add(StatisticVOFixture.keyInSeconds1, StatisticVOFixture.doubleSummaryStatistics1);
		inMemoryStatisticsDao.remove(StatisticVOFixture.keyInSeconds1);
		Assert.assertEquals(0, 
				inMemoryStatisticsDao.getAll().size());
	}

	/**
	 * Should be able to get a single record successfully.
	 */
	@Test
	public void shouldBeAbleToGet() {
		StatisticVO inMemoryStatisticsDao = new StatisticVO();
		inMemoryStatisticsDao.add(StatisticVOFixture.keyInSeconds1, StatisticVOFixture.doubleSummaryStatistics1);
		DoubleSummaryStatistics actualResult = inMemoryStatisticsDao.get(StatisticVOFixture.keyInSeconds1);
		Assert.assertEquals(StatisticVOFixture.doubleSummaryStatistics1, actualResult);
	}

	/**
	 * Should be able to get all the records successfully.
	 */
	@Test
	public void shouldBeAbleToGetAll() {
		StatisticVO inMemoryStatisticsDao = new StatisticVO();
		inMemoryStatisticsDao.add(StatisticVOFixture.keyInSeconds1, StatisticVOFixture.doubleSummaryStatistics1);
		inMemoryStatisticsDao.add(StatisticVOFixture.keyInSeconds2, StatisticVOFixture.doubleSummaryStatistics2);
		int actualResult = inMemoryStatisticsDao.getAll().size();
		Assert.assertEquals(2, actualResult);
	}

	/**
	 * When the search key is present in the map.
	 */
	@Test
	public void shouldBeAbleToSearch1() {
		StatisticVO inMemoryStatisticsDao = new StatisticVO();
		inMemoryStatisticsDao.add(StatisticVOFixture.keyInSeconds1, StatisticVOFixture.doubleSummaryStatistics1);
		boolean actualResult = inMemoryStatisticsDao.contains(StatisticVOFixture.keyInSeconds1);
		Assert.assertTrue(actualResult);
	}

	/**
	 * When the search key is not present in the map.
	 */
	@Test
	public void shouldBeAbleToSearch2() {
		StatisticVO inMemoryStatisticsDao = new StatisticVO();
		inMemoryStatisticsDao.add(StatisticVOFixture.keyInSeconds1, StatisticVOFixture.doubleSummaryStatistics1);
		boolean actualResult = inMemoryStatisticsDao.contains(StatisticVOFixture.keyInSeconds2);
		Assert.assertFalse(actualResult);
	}
}
