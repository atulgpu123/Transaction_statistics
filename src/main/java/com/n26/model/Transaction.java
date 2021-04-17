package com.n26.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.http.HttpStatus;

import com.n26.enums.ErrorEnum;
import com.n26.exception.ApplicationException;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

	@NotNull
	private BigDecimal amount;
	@NotNull
	private String timestamp;

	public long getTimestamp() {
		try {
			return Instant.parse(timestamp).toEpochMilli();
		} catch (Exception e) {
			throw new ApplicationException(ErrorEnum.ERROR_INVALID_TYPE, HttpStatus.UNPROCESSABLE_ENTITY.value());
		}

	}

	public double getAmount() {
		try {
			return  amount.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
		} catch (Exception e) {
			throw new ApplicationException(ErrorEnum.ERROR_INVALID_TYPE, HttpStatus.UNPROCESSABLE_ENTITY.value());
		}
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
