package org.tw.helper;

public class MenuHelper {

	public static void printMenu() {
		System.out.println("\n\nPress 1 if you want to perform simulation by manually entering data");
		System.out.println("Press 2 if you want to perform simulation by automated data preparation");
		System.out.println("Press 3 to check account balance");
		System.out.println("Press 4 to print transaction summary");
		System.out.println("Press 5 to exit");
	}

	public static void printCreditDebitInstruction() {
		System.out.println("\nEnter credit/debit actions to be done on account, example: C-10,D-20,C-30,D-40.\n This "
				+ "means a total of four operations will be done"
				+ "\nwith two credit actions of 10/- and 30/- and two debit actions of 20/- and 40/-.\n"
				+ "If you have one transaction please end it with a comma example: C-10,");
		System.out.println("Comma as a separator must be used to separate transactions\n"
				+ "Hyphen must be used to separate transaction type and amount");
		System.out.println("*****************\nNote transactions will be executed to simulate concurrent behavior"
				+ "\n*****************");
	}
	
	public static void printOperateWithCurrentBalance(long currentBalance) {
		System.out.println("As your savings account already has balance:"+currentBalance+","
				+ "\nwe will use the same for future operations");
	}

	public static void printUseOfDefaultBalanceForSimulation(long defaultInitialBalance) {
		System.out.println("**********\nInitial Balance used will be:"+defaultInitialBalance+"\n**********");
		
	}
}
