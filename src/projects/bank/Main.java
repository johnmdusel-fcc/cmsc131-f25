
package projects.bank;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        phase1();
        phase2();
        phase3();
    }

    public static void phase1() {
        String logName = "data/phase1.log";
        try {
            FileWriter writer = new FileWriter(new File(logName));

            Account acct = new SavingsAccount(
                    "id1",
                    "Owner Name",
                    1.0);

            writer.write(
                    String.format(
                            "Account setup: acct id=%s, owner=%s, balance=%f",
                            acct.getID(),
                            acct.getOwner(),
                            acct.getCurrentBalance()) + System.lineSeparator());

            Bank bank = new Bank();
            int numAccounts0 = bank.getCount();
            int findAcct0 = bank.find(acct.getID());

            boolean addResult = bank.add(acct);
            int numAccounts1 = bank.getCount();
            int findAcct1 = bank.find(acct.getID());

            writer.write(
                    String.format(
                            "Bank init: getCount=%d, find=%d",
                            numAccounts0,
                            findAcct0) + System.lineSeparator());

            writer.write(
                    String.format(
                            "After add: result=%b, getCount=%d, find=%d",
                            addResult,
                            numAccounts1,
                            findAcct1) + System.lineSeparator());

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void phase2() {
        String accountsFilename = "data/accounts.csv";
        Bank bank = new Bank();
        boolean result = bank.loadAccounts(accountsFilename);

        System.out.println("Result of loading account: " + result);
        System.out.println("Number of accounts: " + bank.getCount());

        String outputFilename = "data/accounts-out.csv";
        bank.writeAccounts(outputFilename);
    }

    public static void phase3() {
        Bank bank = new Bank();
        bank.loadAccounts("data/accounts.csv"); // ignore output
        Transaction[] step1 = bank.loadTransactions("data/transactions.csv");
        int step2 = bank.processTransactions(step1);
        boolean step3 = bank.writeAccounts("data/accounts-out.csv");
        System.out.println("Transactions processed: " + step2);
        System.out.println("Result of Accounts write: " + step3);
    }

}
