package org.tw.handler;

import org.tw.event.AccountEvent;
import org.tw.model.Account;

public class AccountHandler {

	public Boolean accept(AccountEvent accountEvent) {
		try {
			Account accountToBeModified = accountEvent.getAccount();
			accountToBeModified.modifyBalance(accountEvent.getTransactionType(), accountEvent.getAmount());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
