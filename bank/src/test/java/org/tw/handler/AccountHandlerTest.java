package org.tw.handler;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.tw.enums.TransactionType;
import org.tw.event.AccountEvent;
import org.tw.model.Account;

public class AccountHandlerTest {

	@Test
	public void testAccountHandler() throws InterruptedException {
		Account savingsAccount = getNewAccount();
		AccountEvent accountEvent = new AccountEvent(savingsAccount, 1000, TransactionType.DEBIT);
		boolean result = new AccountHandler().accept(accountEvent);
		Assert.assertEquals(true, result);
	}

	private Account getNewAccount() {
		Account savingsAccount = new Account(UUID.randomUUID().toString());
		savingsAccount.setBalance(10000);
		return savingsAccount;

	}

}
