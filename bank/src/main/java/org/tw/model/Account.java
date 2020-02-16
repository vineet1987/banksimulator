package org.tw.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.tw.enums.TransactionType;
import org.tw.exception.AccountBalanceException;
import org.tw.helper.AccountHelper;

public class Account {

	private final String accountNumber;

	private long balance;
	
	private long originalBalance;

	private boolean balanceInitialized;

	private final Set<Transaction> transactions = new HashSet<>();

	public Account(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public long getOriginalBalance() {
		return originalBalance;
	}

	public void setOriginalBalance(long originalBalance) {
		this.originalBalance = originalBalance;
		setBalance(originalBalance);
	}

	public boolean isBalanceInitialized() {
		return balanceInitialized;
	}

	public void setBalanceInitialized(boolean balanceInitialized) {
		this.balanceInitialized = balanceInitialized;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public synchronized void modifyBalance(TransactionType transactionType, long transactionAmount) {
		try {
			long newBalance = transactionType.getNewAmount(getBalance(), transactionAmount);
			setBalance(newBalance);

			addTransaction(transactionAmount, transactionType);
			
			AccountHelper.validateForReconciliation(getTransactions(),originalBalance,getBalance(), transactionType, transactionAmount);
		} catch (AccountBalanceException ae) {
			System.out.println("Cannot perform event:[" + transactionType.getShortCode() + "-" + transactionAmount
					+ "] as " + ae.getMessage());
		}
	}

	private void addTransaction(long transactionAmount, TransactionType transactionType) {
		String uniqueTransactionReference = UUID.randomUUID().toString();
		Transaction transaction = new Transaction(uniqueTransactionReference, transactionType, transactionAmount,
				new Date());
		getTransactions().add(transaction);
	}

}
