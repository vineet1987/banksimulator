package org.tw.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.tw.enums.TransactionType;
import org.tw.event.AccountEvent;
import org.tw.exception.ValidationResult;
import org.tw.model.Account;

public class ValidationHelper {

	public static List<AccountEvent> validateInputAndGetListOfAccountEvents(Account savingsAccount,
			String transactionsFromCommandLine) {
		if (!transactionsFromCommandLine.contains("-")) {
			return new ArrayList<AccountEvent>();
		}
		String[] arrayOfActionWithAmount = transactionsFromCommandLine.split(",");
		if (arrayOfActionWithAmount.length == 0) {
			arrayOfActionWithAmount = new String[] { transactionsFromCommandLine };
		}
		ValidationResult validationResult = new ValidationResult();
		List<AccountEvent> accountEvents = new ArrayList<>();
		for (String actionWithAmount : arrayOfActionWithAmount) {
			String[] actionWithAmountSplitted = actionWithAmount.split("-");

			if (actionWithAmountSplitted.length != 2) {
				validationResult.addError("Invalid Input:" + actionWithAmount);
			}

			Optional<TransactionType> optionalTransactionType = TransactionType
					.getEnumFromString(actionWithAmountSplitted[0]);
			if (!optionalTransactionType.isPresent()) {
				validationResult.addError("Invalid input for action:" + actionWithAmount
						+ ", it must start with C or D, where C is for credit and D for debit");
			}

			Long amount = null;

			try {
				amount = Long.parseLong(actionWithAmountSplitted[1]);
			} catch (NumberFormatException ne) {
				validationResult.addError("Invalid input for amount in the input:" + actionWithAmount);
			}

			if (validationResult.isOk()) {
				AccountEvent accountEvent = new AccountEvent(savingsAccount, amount, optionalTransactionType.get());
				accountEvents.add(accountEvent);
			}
		}

		if (!validationResult.isOk()) {
			for (String error : validationResult.getErrors()) {
				System.out.println(error);
			}
			return new ArrayList<AccountEvent>();
		}

		return accountEvents;
	}

	public static boolean isValidAccountBalanceNotEntered(String accountBalance) {
		Long balance;
		try {
			balance = Long.parseLong(accountBalance);
		} catch (NumberFormatException ne) {
			System.out.println("Invalid balance entered:" + accountBalance);
			return true;
		}

		if (balance <= 0) {
			System.out.println("Balance should be greater than 0");
			return true;
		}
		return false;
	}

}
