package org.tw.helper;

import java.util.UUID;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.tw.enums.TransactionType;
import org.tw.event.AccountEvent;
import org.tw.model.Account;
import org.tw.service.AccountService;
import org.tw.service.impl.AccountServiceImpl;

public class AccountHelperTest {

	private AccountService accountService;

	@Before
	public void init() {
		this.accountService = AccountServiceImpl.getServiceInstance();
	}

	@Test
	public void testValidateForReconciliation() throws InterruptedException {
		Account savingsAccount = getNewAccount();
		AccountEvent accountEvent = new AccountEvent(savingsAccount, 1000, TransactionType.DEBIT);
		Future<Boolean> future = accountService.peformActionOnAccount(accountEvent);
		while (!future.isDone()) {
			// do nothing
		}
		AccountHelper.validateForReconciliation(savingsAccount.getTransactions(), savingsAccount.getOriginalBalance(),
				savingsAccount.getBalance(), TransactionType.DEBIT, 1000);
	}

	private Account getNewAccount() {
		Account savingsAccount = new Account(UUID.randomUUID().toString());
		savingsAccount.setBalance(10000);
		return savingsAccount;

	}

}
