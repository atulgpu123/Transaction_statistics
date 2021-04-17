package com.n26.controller;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.n26.model.Transaction;

public class TransactionControllerFixture {

	private static final long timestampWithinAMinuteInMilliseonds = Instant.now().toEpochMilli() - 10000;
	static final Transaction transactionWithinAMinute = new Transaction(new BigDecimal(10),
			DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneOffset.UTC)
					.format(Instant.ofEpochMilli(timestampWithinAMinuteInMilliseonds)));

	private static final long timestampOlderThanAMinuteInMilliseonds = Instant.now().toEpochMilli() - 65000;
	static final Transaction transactionOlderThanAMinute = new Transaction(new BigDecimal(20),
			DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneOffset.UTC)
					.format(Instant.ofEpochMilli(timestampOlderThanAMinuteInMilliseonds)));
}
