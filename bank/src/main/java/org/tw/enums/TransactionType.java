package org.tw.enums;

import java.util.Optional;

import org.tw.exception.AccountBalanceException;

public enum TransactionType {

	CREDIT("C") {
		@Override
		public long getNewAmount(long balance, long amount) {
			return balance + amount;
		}
	},
	DEBIT("D") {
		@Override
		public long getNewAmount(long balance, long amount) throws AccountBalanceException {
			long newBalance = balance - amount;
			if(newBalance < 0) {
				throw new AccountBalanceException("debit amount:"+amount
						+"/- is more than account balance:"+balance+"/-");
			}
			return newBalance;
		}
	};

	private final String shortCode;

	TransactionType(String shortCode) {
		this.shortCode = shortCode;
	}

	public abstract long getNewAmount(long balance, long amount) throws AccountBalanceException;

	public String getShortCode() {
		return shortCode;
	}

	public static Optional<TransactionType> getEnumFromString(String transactionTypeShortCode) {
		for (TransactionType transactionType : TransactionType.values()) {
			if (transactionType.getShortCode().equalsIgnoreCase(transactionTypeShortCode)) {
				return Optional.of(transactionType);
			}
		}
		return Optional.empty();
	}
}
