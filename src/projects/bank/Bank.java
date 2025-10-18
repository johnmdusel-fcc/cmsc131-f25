package projects.bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Bank {
    private static Account[] accounts;
    private static int accountCount;
    private static Scanner scanner;
    private static FileWriter writer;

    // This bank will hold 392 accounts for this project.
    public Bank() {
        accounts = new Account[392];
        accountCount = 0;
    }

    /**
     * Confirm that an account is successfully added to the bank database.
     * 
     * @param account the valid account to be added.
     * 
     * @return true if the account was added successfully, false if an account with
     *         the same ID already exists.
     * 
     * @throws ArrayIndexOutOfBoundsException if the bank has reached its maximum
     *                                        capacity.
     */
    public boolean add(Account account) {
        // Check if account ID already exist
        if (account == null) {
            throw new IllegalArgumentException("account must not be null.");
        }
        if (find(account.getID()) != -1) {
            // Account with same ID already exists
            return false;
        }
        // Otherwise, add the new account
        // what if accountCount is already at max capacity?
        if (accountCount >= accounts.length) {
            // to increase capacity by a fixed quantity each time (say add 100 new slots)
            Account[] newAccounts = new Account[accounts.length * 2];
            System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
            accounts = newAccounts;
            accountCount++;
            accounts[accountCount++] = account;
            return true;
        }
        // The bank database has space, add the account
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = account;
                accountCount++;
                break;
            }
        }
        return true;
    }

    /**
     * Find an account by its ID.
     * 
     * @param accountID the ID of the account to find.
     * 
     * @return returns the index of the account in the array or -1 if not found.
     */
    public int find(String accountID) {
        if (accountID == null) {
            throw new IllegalArgumentException("accountID must not be null.");
        }
        for (int i = 0; i < accountCount; i++) {
            // so you can simplify the condition on line 62
            if (accounts[i].getID().equals(accountID)) {
                return i;
            }
        }
        return -1; // Account not found
    }

    /**
     * Get the number of active accounts in the bank.
     * 
     * @return the number of active accounts.
     */
    public int getCount() {
        return accountCount;
    }

    /**
     * Get all accounts for a given account owner name.
     * 
     * @param accountOwnerName the name of the account owner.
     * 
     * @return an array of accounts owned by the specified account owner name.
     */
    public Account[] getAccounts(String OwnerName) {
        Account[] accountsBySameOwner = new Account[accountCount];
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null && accounts[i].getOwner().equals(OwnerName)) {
                // style: it's clearer to increment j on a separate line
                for (int j = 0; j < accountCount; j++) {
                    if (accountsBySameOwner[j] == null) {
                        accountsBySameOwner[j] = accounts[i];
                        j++;
                        break; // Exit inner loop after adding the account
                    }
                }
            }
        }
        return accountsBySameOwner;
    } // End of for loop

    /**
     * Read the file containing the bank accounts and fill the datatbase
     * 
     * @param file the file containing the bank accounts.
     * 
     * @return true if the file was read successfully, false otherwise.
     * 
     * @throws Exception if there was an error reading the file.
     * 
     */
    public static boolean loadAccounts(String fileName) {
        // Load accounts one by one into the bank

        File fileToLoad = new File(fileName);
        try {
            scanner = new Scanner(fileToLoad);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line == null || line.isEmpty()) { // TODO remove duplicate validation (see Account.java around line 65)
                    throw new IllegalArgumentException(
                            "line must not be null.");
                }
                Account account = Account.make(line);
                // this method is meant to update this bank's accounts
                Bank bank = new Bank(); // TODO remove
                bank.add(account); // TODO remove
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("file not found. " + e.getMessage());
        }
        return false;
    }

    /**
     * Write all accounts in the bank to a file.
     * 
     * @param filename the name of the file to write the accounts to.
     * 
     * @return true if the accounts were written successfully, false otherwise.
     * 
     * @throws Exception if there was an error writing to the file.
     * 
     */
    public static boolean writeAccounts(String filename) {
        try {
            writer = new FileWriter(filename);
            for (int i = 0; i < accountCount; i++) {
                String line = Account.toCSV(accounts[i]);
                writer.write(line + "\n");
            }
            writer.close();
            return true;
        } catch (IOException | IllegalArgumentException e) {
            return false;
        }
    }
}