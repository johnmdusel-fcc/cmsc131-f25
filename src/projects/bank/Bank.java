package projects.bank;

public class Bank {
    private Account[] accounts;
    private int accountCount;

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
        if (find(account.getID()) != -1) {
            // Account with same ID already exists
            return false;
        }
        // Otherwise, add the new account
        // what if accountCount is already at max capacity?
        if (accountCount >= accounts.length) {
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
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i] != null && (accounts[i].getID().equals(accountID))) {
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

}