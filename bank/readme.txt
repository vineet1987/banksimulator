This project is used to simulate the below actions for a bank account:

a) Deposit
b) Withdrawal
c) Balance-checking
d) Listing-all transactions.

Steps/Working:
The console is menu driven. 

The logic consists of event driven methodology where there is a single dispatcher class
"AccountService" which is a singleton class and has a fixed thread pool to dispatch events to AccountHandler in separate threads.

Multiple events are triggered from the main class "BankAccountSimulator.java" using AccountService. 
This service class then redirects the event to the AccountHandler class which then invokes a synchronized method modifyBalance.

Sample simulation with option 1:

Press 1 if you want to perform simulation by manually entering data
Press 2 if you want to perform simulation by automated data preparation
Press 3 to check account balance
Press 4 to print transaction summary
Press 5 to exit
=> 1
Enter initial account balance
=> 1000

Enter credit/debit actions to be done on account, example: C-10,D-20,C-30,D-40.
 This means a total of four operations will be done
with two credit actions of 10/- and 30/- and two debit actions of 20/- and 40/-.
If you have one transaction please end it with a comma example: C-10,
Comma as a separator must be used to separate transactions
Hyphen must be used to separate transaction type and amount
*****************
Note transactions will be executed to simulate concurrent behavior
*****************
=> D-100,D-900,D-999

Transactions have been successfully reconciled for operation:[D-100]
Cannot perform event:[D-999] as debit amount:999/- is more than account balance:900/-

Transactions have been successfully reconciled for operation:[D-900]


Transaction summary is as below:
		Transaction Ref					CR/DR	Amount		TransactionDate

1e80d10d-2fac-4df6-b257-9aaa9f9250dd	DEBIT	   100	Mon Feb 17 04:20:16 IST 2020
e6b993c4-ff72-4e23-8ff2-de8d87c1ec73	DEBIT	   900	Mon Feb 17 04:20:16 IST 2020


Press 1 if you want to perform simulation by manually entering data
Press 2 if you want to perform simulation by automated data preparation
Press 3 to check account balance
Press 4 to print transaction summary
Press 5 to exit

=>3
Account balance is: Rs 0/-


Press 1 if you want to perform simulation by manually entering data
Press 2 if you want to perform simulation by automated data preparation
Press 3 to check account balance
Press 4 to print transaction summary
Press 5 to exit

=>4


Transaction summary is as below:
	Transaction Ref			CR/DR	Amount		TransactionDate

1e80d10d-2fac-4df6-b257-9aaa9f9250dd	DEBIT	   100	Mon Feb 17 04:20:16 IST 2020
e6b993c4-ff72-4e23-8ff2-de8d87c1ec73	DEBIT	   900	Mon Feb 17 04:20:16 IST 2020


Press 1 if you want to perform simulation by manually entering data
Press 2 if you want to perform simulation by automated data preparation
Press 3 to check account balance
Press 4 to print transaction summary
Press 5 to exit
=>5