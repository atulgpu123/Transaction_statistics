package com.n26.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.DoubleSummaryStatistics;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Statistics {

	@NotNull
	private BigDecimal sum = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);
	@NotNull
	private BigDecimal avg= new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);;
	@NotNull
	private BigDecimal max= new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);;
	@NotNull
	private BigDecimal min= new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);;
	@NotNull
	private long count;

	
	public Statistics(DoubleSummaryStatistics doubleSummaryStatistics) {
		sum = new BigDecimal(doubleSummaryStatistics.getSum()).setScale(2, RoundingMode.HALF_EVEN);
		avg = new BigDecimal(doubleSummaryStatistics.getAverage()).setScale(2, RoundingMode.HALF_EVEN);
		max = new BigDecimal(doubleSummaryStatistics.getMax()).setScale(2, RoundingMode.HALF_EVEN);
		min = new BigDecimal(doubleSummaryStatistics.getMin()).setScale(2, RoundingMode.HALF_EVEN);
		count = doubleSummaryStatistics.getCount();
	}

}
