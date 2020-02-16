package org.tw.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.tw.enums.TransactionType;
import org.tw.model.Transaction;

public class AccountHelper {

	public static void printTransactionSummary(Set<Transaction> transactions) {
		System.out.println("\n\nTransaction summary is as below:");
		List<Transaction> transactionsAsList = new ArrayList<>();
		transactionsAsList.addAll(transactions);
		Collections.sort(transactionsAsList, new Comparator<Transaction>() {

			@Override
			public int compare(Transaction o1, Transaction o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});
		System.out.println("\tTransaction Ref\t\t\tCR/DR\tAmount\t\tTransactionDate\n");
		transactionsAsList.forEach(
				(transactionReference) -> System.out.println(transactionReference.getUniqueTransactionReference() + "\t"
						+ transactionReference.getAccountActionType() + "\t   " + transactionReference.getAmount()
						+ "\t" + transactionReference.getDate()));
	}

	public static void validateForReconciliation(Set<Transaction> transactions, long originalBalance,
			long currentBalance, TransactionType transactionType, long transactionAmount) {
		Long totalCredits = transactions.stream()
				.filter((transaction) -> TransactionType.CREDIT.equals(transaction.getAccountActionType()))
				.mapToLong((transaction) -> transaction.getAmount()).sum();

		Long totalDebits = transactions.stream()
				.filter((transaction) -> TransactionType.DEBIT.equals(transaction.getAccountActionType()))
				.mapToLong((transaction) -> transaction.getAmount()).sum();

		if (currentBalance - totalCredits + totalDebits == originalBalance) {
			System.out.println("\nTransactions have been successfully reconciled for operation:["
					+ transactionType.getShortCode() + "-" + transactionAmount + "]");
		}

	}

}
