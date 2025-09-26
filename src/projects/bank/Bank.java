package projects.bank;

public class Bank {
    private Account[] accounts;
    private int accountCount = 0;
    public int index;

    // This bank will hold 392 accounts for this project.
    public Bank() {
        accounts = new Account[392];
    }

    // Confirm that an account is added to the bank
    public boolean addAccount(Account account) {
        // Check if account ID is unique
        if (findAccountById(account.getAccountId()) != -1) {
            // Account with same ID already exists
            return false;
        }
        // Resize array if needed
        if (accountCount == accounts.length) {
            Account[] newAccounts = new Account[accounts.length * 2];
            System.arraycopy(accounts, 0, newAccounts, 0, accounts.length);
            accounts = newAccounts;
            accounts[accountCount++] = account;
            return true;
        }
        // Else, simply add the account
        accounts[accountCount++] = account;
        return true;
    }

    // returns the index of the account in the array or null if not found
    public int findAccountById(String ACCOUNTID) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountId().equals(ACCOUNTID)) {
                index = i;
                return i;
            }
        }
        return -1; // Account not found
    }

    // New method to get the number of active accounts in the bank
    public int getNumberOfAccounts() {
        int count = 0;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null) {
                count++;
            }
        }
        return count;
    }

    // New method to get all accounts for a given account owner name
    public Account[] getAllAccountsByOwnerName(String accountOwnerName) {
        // Declare a temporary array to hold matches and set a match count to 100
        Account[] accountsBySameOwner = new Account[10];
        int j = 0;
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountOwnerName().equals(accountOwnerName)) {
                accountsBySameOwner[j++] = accounts[i];
            }
        }
        return accountsBySameOwner;
    }
}