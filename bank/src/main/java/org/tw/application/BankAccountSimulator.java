package org.tw.application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Future;

import org.tw.event.AccountEvent;
import org.tw.helper.AccountHelper;
import org.tw.helper.DataHelper;
import org.tw.helper.MenuHelper;
import org.tw.helper.ValidationHelper;
import org.tw.model.Account;
import org.tw.service.AccountService;
import org.tw.service.impl.AccountServiceImpl;

public class BankAccountSimulator {

	/**
	 * Thread pool size used by executor service to simulate transactions in a
	 * concurrent manner
	 */
	private static final int DEFAULT_NUMBER_OF_TRANSACTONS = 10;

	private static final long DEFAULT_INITIAL_BALANCE = 10000;

	private static final AccountService accountService = AccountServiceImpl.getServiceInstance();

	/**
	 * This method is the main method which is a menu driven program to simulate
	 * data
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String args[]) throws InterruptedException {

		Scanner input = new Scanner(System.in);

		Account savingsAccount = DataHelper.createAccount(accountService);

		while (true) {

			MenuHelper.printMenu();

			try {

				int choice = input.nextInt();

				switch (choice) {
				case 1:
					if (!savingsAccount.isBalanceInitialized()) {
						System.out.println("Enter initial account balance");
						String accountBalance = input.next();
						if (ValidationHelper.isValidAccountBalanceNotEntered(accountBalance)) {
							break;
						}
						savingsAccount.setOriginalBalance(Long.parseLong(accountBalance));
						savingsAccount.setBalanceInitialized(true);
					} else {
						MenuHelper.printOperateWithCurrentBalance(savingsAccount.getBalance());
					}
					MenuHelper.printCreditDebitInstruction();
					String transactionsFromCommandLine = input.next();
					List<AccountEvent> accountEventsToBePerformed = ValidationHelper
							.validateInputAndGetListOfAccountEvents(savingsAccount, transactionsFromCommandLine);
					while (accountEventsToBePerformed.isEmpty()) {
						MenuHelper.printCreditDebitInstruction();
						transactionsFromCommandLine = input.next();
						accountEventsToBePerformed = ValidationHelper
								.validateInputAndGetListOfAccountEvents(savingsAccount, transactionsFromCommandLine);
					}
					fireEvents(savingsAccount, accountEventsToBePerformed);
					AccountHelper.printTransactionSummary(savingsAccount.getTransactions());
					break;

				case 2:
					if (!savingsAccount.isBalanceInitialized()) {
						MenuHelper.printUseOfDefaultBalanceForSimulation(DEFAULT_INITIAL_BALANCE);
						savingsAccount.setOriginalBalance(DEFAULT_INITIAL_BALANCE);
						savingsAccount.setBalanceInitialized(true);
					} else {
						MenuHelper.printOperateWithCurrentBalance(savingsAccount.getBalance());
					}
					List<AccountEvent> accountEventsToBeDone = DataHelper
							.prepareRandomListOfActionsForAccount(savingsAccount, DEFAULT_NUMBER_OF_TRANSACTONS);
					fireEvents(savingsAccount, accountEventsToBeDone);
					AccountHelper.printTransactionSummary(savingsAccount.getTransactions());
					break;

				case 3:
					System.out.println("Account balance is: Rs " + savingsAccount.getBalance() + "/-");
					break;

				case 4:
					accountService.printTransactionSummary(savingsAccount);
					break;

				case 5:
					input.close();
					System.exit(0);

				default:
					System.out.println("Wrong entry");
					break;
				}
			} catch (InputMismatchException ime) {
				System.out.println("Invalid input");
			}
		}
	}

	private static void fireEvents(Account savingsAccount, List<AccountEvent> accountEventsToBeDone)
			throws InterruptedException {
		List<Future<Boolean>> futures = new ArrayList<>();
		for (AccountEvent accountEvent : accountEventsToBeDone) {
			futures.add(accountService.peformActionOnAccount(accountEvent));
		}

		boolean allCompleted = false;
		while (!allCompleted) {
			for (Future<Boolean> future : futures) {
				allCompleted = future.isDone();
				if(allCompleted == false) {
					break;
				}
			}
		}

	}

}
