package projects.bank;

public class Account {

    private final String accountID;
    private final String accountOwnerName;
    private AccountType accountType;
    private double currentBalance;

    /**
     * Each account will hold the following values:
     * 
     * @param accountNumber   - alphanumeric Unique account identifier, definitive
     *                        value
     * 
     * @param name            - Name of the account owner, could be repeated across
     *                        accounts, definitive value
     * 
     * @param type            - Type of account (Checking, Savings), definitive
     *                        value
     * 
     * @param startingBalance - Current balance of the account, could be negative ,
     *                        variable value
     * 
     */
    public Account(String accountNumber,
            String name,
            AccountType type,
            double startingBalance) {
        if (accountNumber == null) {
            throw new IllegalArgumentException(
                    "Account ID cannot be empty.");
        }
        if (name == null) {
            throw new IllegalArgumentException(
                    "Account owner's name cannot be empty.");
        }

        accountID = accountNumber;
        accountOwnerName = name;
        accountType = type;
        currentBalance = startingBalance;
    }

    public String getID() {
        return accountID;
    }

    public String getOwner() {
        return accountOwnerName;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public AccountType getType() {
        return accountType;
    }
}