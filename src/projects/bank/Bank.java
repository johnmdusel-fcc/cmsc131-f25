package projects.bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Bank {

    private Account[] accounts;
    private int idxNextAccount;
    private final int newAccountsIncrement = 100;

    public Bank() {
        accounts = new Account[newAccountsIncrement];
    }

    /**
     * Add an account to this Bank.
     * @param acct - Account to add.
     * @return - true if successful, false if unsuccessful.
     * @throws IllegalArgumentException - If acct is null.
     */
    public boolean add(Account acct) {
        if (acct == null) {
            throw new IllegalArgumentException("account must not be null.");
        }
        if (find(acct.getID()) == -1) {
            try {
                accounts[idxNextAccount] = acct;
            } catch(ArrayIndexOutOfBoundsException e) {
                Account[] accountsExtended = new Account[
                    accounts.length + newAccountsIncrement
                ];
                for (int idx = 0; idx < accounts.length; idx++) {
                    accountsExtended[idx] = accounts[idx];
                }
                accountsExtended[idxNextAccount] = acct;
                accounts = accountsExtended;
            }
            idxNextAccount++;
            return true;
        } else {
            return false;
        }

    }

    /**
     * Find an account in this Bank using its unique ID.
     * @param accountID - Unique ID.
     * @return - Reference to account in this Bank, or -1 if no match.
     * @throws IllegalArgumentException if accountID is null.
     */
    public int find(String accountID) {
        if (accountID == null) {
            throw new IllegalArgumentException("accountID must not be null.");
        }
        for (int idxAcct = 0; idxAcct < idxNextAccount; idxAcct++) {
            if (accounts[idxAcct].getID().equals(accountID)) {
                return idxAcct;
            }
        }
        return -1;
    }

    /**
     * @return - Number of accounts in this Bank.
     */
    public int getCount() {
        return idxNextAccount;
    }

    /**
     * Load accounts into this Bank from a CSV file. 
     * 
     * Assumes each row follows the format savings,wz240833,Anna Gomez,8111.00
     * @param filename - Name of source CSV file.
     * @return - {@code true} if and only if the operation is successful
     */
    public boolean loadAccounts(String filename) {
        boolean result = true;
        File inputFile = new File(filename);
        Scanner scan;
        try {
            scan = new Scanner(inputFile);
            while (scan.hasNextLine()) {
                String csvString = scan.nextLine();
                Account account = Account.make(csvString);
                add(account);
            }
            scan.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * Write accounts in this Bank to CSV file.
     * @param filename - Name of destination CSV file.
     */
    public boolean writeAccounts(String filename) {
        File file = new File(filename);
        FileWriter writer;
        try {
            writer = new FileWriter(file);
            for (int idx = 0; idx < idxNextAccount; idx++) {
                Account account = accounts[idx];
                String accountCsv = account.toCSV();
                writer.write(accountCsv + System.lineSeparator());
            }
            writer.close();
            return true;
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Instrumentation for phase 2 unit testing
     * @return - Array of accounts for this bank.
     */
    public Account[] getAccounts() { return accounts; }

    /**
     * Method stub. TODO fill in javadoc
     * @param filename
     * @return
     */
    public boolean processTransactions(String filename) {
        return false; // TODO fill in body
    }

}

