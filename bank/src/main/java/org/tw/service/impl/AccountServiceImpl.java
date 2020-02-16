package org.tw.service.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.tw.event.AccountEvent;
import org.tw.handler.AccountHandler;
import org.tw.helper.AccountHelper;
import org.tw.model.Account;
import org.tw.service.AccountService;

public final class AccountServiceImpl implements AccountService {

	private AccountServiceImpl() {

	}

	private static AccountService accountService;

	private static ExecutorService executorService = Executors.newFixedThreadPool(5);

	@Override
	public Future<Boolean> peformActionOnAccount(AccountEvent accountEvent) throws InterruptedException {
		return executorService.submit(() -> {
			return new AccountHandler().accept(accountEvent);
		});
	}

	@Override
	public void printTransactionSummary(Account account) {
		AccountHelper.printTransactionSummary(account.getTransactions());
	}

	public static AccountService getServiceInstance() {
		if (accountService == null) {
			return new AccountServiceImpl();
		}
		return accountService;
	}

}
