package org.tw.service;

import java.util.concurrent.Future;

import org.tw.event.AccountEvent;
import org.tw.model.Account;

public interface AccountService {

	Future<Boolean> peformActionOnAccount(AccountEvent accountEvent) throws InterruptedException;

	void printTransactionSummary(Account account);
}
