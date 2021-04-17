package com.n26.vo;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class StatisticVO {

	private ConcurrentHashMap<Long, DoubleSummaryStatistics> secondToStats;

	StatisticVO() {
		secondToStats = new ConcurrentHashMap<>();
	}

	public int getSize() {
		return secondToStats.size();
	}

	public boolean contains(long key) {
		return secondToStats.containsKey(key);
	}

	public DoubleSummaryStatistics get(long key) {
		return secondToStats.get(key);
	}

	public List<DoubleSummaryStatistics> getAll() {
		return new ArrayList<>(secondToStats.values());
	}

	public void add(long key, DoubleSummaryStatistics value) {
		secondToStats.put(key, value);
	}

	public void remove(long key) {
		secondToStats.remove(key);
	}

	public void clear() {
		secondToStats.clear();
	}

}
