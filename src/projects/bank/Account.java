package projects.bank;

public class Account {

    private final String accountID;
    private final String accountOwnerName;
    private double currentBalance;

    public enum AccountType {
        CHECKING, SAVINGS
    };

    private AccountType accountType;

    /**
     * Each account will hold the following values:
     * 
     * @param accountID        - alphanumeric Unique account identifier, definitive
     *                         value
     * 
     * @param accountOwnerName - Name of the account owner, could be repeated across
     *                         accounts, definitive value
     * 
     * @param currentBalance   - Current balance of the account, could be negative ,
     *                         variable value
     * 
     * @param accountType      - Type of account (Checking, Savings), definitive
     *                         value
     * 
     */
    public Account(String accountNumber, String Name, AccountType type, double startingBalance) {
        // Added data validation on Account ID and Account owner name.
        if (accountNumber == null) {
            throw new IllegalArgumentException(
                    "Account ID cannot be empty.");
        }
        if (Name == null) {
            throw new IllegalArgumentException(
                    "Account owner's name cannot be empty.");
        }

        accountID = accountNumber;
        accountOwnerName = Name;
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