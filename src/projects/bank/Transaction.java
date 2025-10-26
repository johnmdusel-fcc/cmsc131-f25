package projects.bank;

abstract class Transaction {

    protected final String accountID;
    public String getAccountID() { return accountID; }
    
    protected final double amount;
    public double getAmount() { return amount; }

    // abstract methods, to be overridden by subclasses
    
    abstract void execute(Account account);
    abstract String toCSV();
    
    /**
     * Determines whether this transaction can execute on the target account.
     * @param account - Target account.
     * @return true if and only if this transaction can execute.
     */
    abstract boolean validate(Account account);

    // concrete methods

    /**
     * Create a new transaction object.
     * @param accountID - Target account ID.
     * @param amount - Amount of this transaction.
     */
    protected Transaction(String accountID, double amount) {
        if (accountID == null) {
            throw new IllegalArgumentException(
                "Parameter accountID cannot be null."
            ); // tested by testConstructorDataValidation
        }
        else if (amount <= 0) {
            throw new IllegalArgumentException(
                "Parameter amount must be positive."
            ); // tested by testConstructorDataValidation
        } else {
            this.accountID = accountID;
            this.amount = amount;
        } 
    }

    /**
     * Create a new Transaction object from CSV line,
     * eg "withdrawal,rp332960,267.57"
     * @param inputLine - Line from transactions.csv.
     * @return Transaction object.
     */
    protected static Transaction make(String inputLine) {
        if (inputLine == null) {
            throw new IllegalArgumentException(
                "Parameter inputLine cannot be null."
            ); // tested by testMakeThrowsOnNullInput
        }
        String[] tokens = inputLine.split(",");
        TransactionType type = TransactionType.valueOf(
            tokens[0].toUpperCase()
        );
        String id = tokens[1];
        double amount = (double) Double.valueOf(tokens[2]);
        if (type == TransactionType.DEPOSIT) {
            return new Deposit(id, amount);
            // tested by testMakePreservesData
        } else {
            return new Withdrawal(id, amount);
            // tested by testMakePreservesData
        }
    }

}
