package org.tw.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.tw.enums.TransactionType;
import org.tw.event.AccountEvent;
import org.tw.model.Account;
import org.tw.service.AccountService;

public class DataHelper {

	public static Account createAccount(AccountService accountService) {
		Account savingsAccount = new Account("Demo-Account");
		return savingsAccount;
	}

	public static List<AccountEvent> prepareRandomListOfActionsForAccount(Account savingsAccount,
			int defaultNumberOfTransactions) {
		List<AccountEvent> actionsToBeDoneOnAccount = new ArrayList<>();
		AccountEvent accountEvent;
		for (int i = 0; i < defaultNumberOfTransactions; i++) {
			long transactionAmount = Math.round(new Random().nextFloat() * 100);
			if (transactionAmount == 0) {
				transactionAmount++;
			}
			if (i % 2 == 0) {
				accountEvent = new AccountEvent(savingsAccount, transactionAmount, TransactionType.CREDIT);
			} else {
				accountEvent = new AccountEvent(savingsAccount, transactionAmount, TransactionType.DEBIT);
			}
			actionsToBeDoneOnAccount.add(accountEvent);
		}
		return actionsToBeDoneOnAccount;
	}

}
