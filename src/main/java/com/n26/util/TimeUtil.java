package com.n26.util;

import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TimeUtil {

	/**
	 * get current time in Epoch
	 * 
	 * @return
	 */
	public long currentTimestamp() {
		return Instant.now().toEpochMilli();
	}

	/**
	 * Convert milliseconds to second
	 * 
	 * @param milliseconds
	 * @return
	 */
	public long convertMillisecondsToSeconds(long milliseconds) {
		return milliseconds / 1000;
	}

	/**
	 * Convert seconds to milliseconds
	 * 
	 * @param milliseconds
	 * @return
	 */
	public long convertSecondsToMilliseconds(long seconds) {
		return seconds * 1000;
	}

	/**
	 * get current time in second
	 * 
	 * @return
	 */
	public long currentTimeInSeconds() {
		long timestamp = currentTimestamp();
		return convertMillisecondsToSeconds(timestamp);
	}
}
