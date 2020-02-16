package org.tw.event;

import org.tw.enums.TransactionType;
import org.tw.model.Account;

public class AccountEvent {

	private final Account account;

	private final long amount;

	private final TransactionType transactionType;

	public AccountEvent(Account account, long amount, TransactionType transactionType) {
		this.account = account;
		this.amount = amount;
		this.transactionType = transactionType;
	}

	public Account getAccount() {
		return account;
	}

	public long getAmount() {
		return amount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(transactionType.getShortCode());
		sb.append("-");
		sb.append(amount);
		sb.append("]");
		return sb.toString();
	}

}
