package projects.bank;

// TODO remove following unnecessary line
//import projects.bank.Enum.AccountType;  

public class Account {

    // TODO style: change variable names to camelCase
    private final String accountId;
    private final String accountOwnerName;
    private double currentBalance; // variable name OK

    public enum AccountType {
        CHECKING, SAVINGS
    };

    private AccountType accountType;

    // TODO move the javadoc to line 13. Also add @throws entry.
    /**
     * Each account will hold the following values:
     * 
     * @param accountId        - alphanumeric Unique account identifier, definitive
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

        accountId = accountNumber;
        accountOwnerName = Name;
        accountType = type;
        currentBalance = startingBalance;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountOwnerName() {
        return accountOwnerName;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public AccountType getAccountType() {
        return accountType;
    }
}