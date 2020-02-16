package org.tw.service;

import java.util.UUID;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.tw.enums.TransactionType;
import org.tw.event.AccountEvent;
import org.tw.model.Account;
import org.tw.service.impl.AccountServiceImpl;

public class AccountServiceTest {
	
	private AccountService accountService;
	
	@Before
	public void init() {
		this.accountService = AccountServiceImpl.getServiceInstance();
	}
	
	@Test
	public void testPerformActionOnAccount() throws InterruptedException {
		Account savingsAccount = getNewAccount();
		AccountEvent accountEvent = new AccountEvent(savingsAccount,1000,TransactionType.DEBIT);
		Future<Boolean> future = accountService.peformActionOnAccount(accountEvent);
		while(!future.isDone()) {
			//do nothing
		}
		Assert.assertEquals(savingsAccount.getBalance(), 9000);
	}
	
	@Test
	public void testNumberOfTransactions() throws InterruptedException {
		Account savingsAccount = getNewAccount();
		AccountEvent accountEvent = new AccountEvent(savingsAccount,1000,TransactionType.DEBIT);
		accountService.peformActionOnAccount(accountEvent);
		accountEvent = new AccountEvent(savingsAccount,1000,TransactionType.DEBIT);
		accountService.peformActionOnAccount(accountEvent);
		Assert.assertEquals(savingsAccount.getBalance(),8000);
		Assert.assertEquals(savingsAccount.getTransactions().size(), 2);
	}
	
	private Account getNewAccount() {
		Account savingsAccount = new Account(UUID.randomUUID().toString());
		savingsAccount.setBalance(10000);
		return savingsAccount;
		
	}

}
