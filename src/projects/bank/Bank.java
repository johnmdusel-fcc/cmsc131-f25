package projects.bank;

public class Bank {
    private Account[] accounts;
    private int accountCount = 0;
    public int index;  // is this attribute necessary? consider removing

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
        // style: clearer to increment accountCount on separate line
        accounts[accountCount++] = account;
        return true;
    }

    
    // TODO convert line below into javadoc
    // returns the index of the account in the array or null if not found
    public int findAccountById(String ACCOUNTID) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountId().equals(ACCOUNTID)) {
                index = i;  // what's the intent of this line?
                return i;
            }
        }
        // TODO method does *not* return null. please correct javadoc.
        return -1; // Account not found
    }

    // TODO convert line below to javadoc
    // New method to get the number of active accounts in the bank
    public int getNumberOfAccounts() {
        // TODO use accountCount instead of doing this loop
        int count = 0;
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null) {
                count++;
            }
        }
        return count;
    }

    // This method wasn't included in the specs for phase 1
    // TODO convert line below to javadoc
    // New method to get all accounts for a given account owner name
    public Account[] getAllAccountsByOwnerName(String accountOwnerName) {
        // Declare a temporary array to hold matches and set a match count to 100
        // TODO why 10? consider using accounts.length
        Account[] accountsBySameOwner = new Account[10];
        int j = 0;
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountOwnerName().equals(accountOwnerName)) {
                // style: it's clearer to increment j on a separate line
                accountsBySameOwner[j++] = accounts[i];
            }
        }
        return accountsBySameOwner;
    }
}