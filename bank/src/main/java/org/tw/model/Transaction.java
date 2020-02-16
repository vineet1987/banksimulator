package org.tw.model;

import java.util.Date;

import org.tw.enums.TransactionType;

public class Transaction {

	private final String uniqueTransactionReference;

	private final TransactionType accountActionType;

	private final long amount;

	private final Date date;

	public Transaction(String uniqueTransactionReference, TransactionType accountActionType, long amount, Date date) {
		this.uniqueTransactionReference = uniqueTransactionReference;
		this.accountActionType = accountActionType;
		this.amount = amount;
		this.date = date;
	}

	public String getUniqueTransactionReference() {
		return uniqueTransactionReference;
	}

	public TransactionType getAccountActionType() {
		return accountActionType;
	}

	public long getAmount() {
		return amount;
	}

	public Date getDate() {
		return date;
	}

	public int hashCode() {
		return uniqueTransactionReference.hashCode();
	}

	public boolean equals(Object transaction) {
		return getUniqueTransactionReference().equals(((Transaction) transaction).getUniqueTransactionReference());
	}

}
