package projects.bank;

public class Bank {
    private Account[] accounts;
    private int accountCount;
    // remove this private field and inject in the constructor instead
    private int bankCapacity; // Set a default value or use a constructor parameter

    public Bank(int capacity) {
        bankCapacity = capacity;
        accounts = new Account[bankCapacity];
    }

    public void addAccount(Account account) {
        if (accountCount >= bankCapacity) {
            throw new IllegalArgumentException("Bank capacity is full: " + getNumberOfAccounts());
        } else if (findAccountById(account.getAccountId()) != null) {
            throw new IllegalArgumentException("Account with ID " + account.getAccountId() + " already exists.");
        } else {
            accounts[accountCount++] = account;
            System.out.println("Account added: " + account.getAccountId());
        }
    }

    public Account findAccountById(String accountId) {
        if (accountId != null && !accountId.isEmpty()) {
            // accountId is not null and not empty
            for (int i = 0; i < accountCount; i++) {
                if (accounts[i].getAccountId().equals(accountId)) {
                    return accounts[i];
                }
            }
            return null;
        } else {
            throw new IllegalArgumentException("Account ID cannot be null or empty.");
        }
    }

    public int getNumberOfAccounts() {
        return accountCount;
    }

    // New method to get all accounts for a given account owner name
    public Account[] getAllAccountsByOwnerName(String accountOwnerName) {
        if (accountOwnerName != null && !accountOwnerName.isEmpty()) {
            // Collect matching accounts
            // declare a temporary array to hold matches and set a match count to 100
            Account[] accountsBySameOwner = new Account[10];
            int j = 0;
            for (int i = 0; i < accountCount; i++) {
                if (accounts[i].getAccountOwnerName().equals(accountOwnerName)) {
                    accountsBySameOwner[j++] = accounts[i];
                }
            }

            return accountsBySameOwner;
        }
        throw new IllegalArgumentException("Account owner name cannot be null or empty.");

    }
}