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
                    "account ID cannot be empty.");
        }
        if (name == null) {
            throw new IllegalArgumentException(
                    "account owner's name cannot be empty.");
        }
        if (type == null) {
            throw new IllegalArgumentException("type cannot be null.");
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

    // TODO javadoc
    public static Account make(String line) {
        if (line == null) {
            throw new IllegalArgumentException("line must not be null.");
        }
        String[] tokens = line.split(",");
        AccountType type = AccountType.valueOf(tokens[0].toUpperCase());
        String id = tokens[1];
        String owner = tokens[2];
        double balance = Double.parseDouble(tokens[3]);
        return new Account(id, owner, type, balance);
    }

    public static String toCSV(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("account must not be null.");
        }
        String token1 = String.valueOf(account.getType()).toLowerCase();
        String token2 = account.getID();
        String token3 = account.getOwner();
        String token4 = String.valueOf(account.getCurrentBalance());
        return String.join(",", token1, token2, token3, token4);
    }
}