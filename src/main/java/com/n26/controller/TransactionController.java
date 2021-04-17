package com.n26.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.n26.model.Transaction;
import com.n26.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/transactions", method = RequestMethod.POST)
	public ResponseEntity<?> addStatistics(@RequestBody @Valid Transaction transaction) {

		if (transactionService.addTransaction(transaction))
			return new ResponseEntity(HttpStatus.CREATED);
		else
			return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/transactions", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteStatistics(@RequestBody Transaction transaction) {
		transactionService.deleteTransaction();
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
