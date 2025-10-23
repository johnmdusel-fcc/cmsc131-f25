package projects.bank;

public abstract class Transaction {
    private double amount;
    private String accountID;

    abstract boolean execute(Account account); // okay to return void, up to you

    protected Transaction(String accountNumber, double transactionAmt) {
        if (accountNumber == null) {
            throw new IllegalArgumentException("account number cannot be null");
        }
        if (transactionAmt <= 0) {
            throw new IllegalArgumentException(
                "transaction amount must be positive"
            );
        }

        amount = transactionAmt;
        accountID = accountNumber;

    }

    public double getAmount() {
        return amount;
    }

    public String getAccountNumber() {
        return accountID;
    }

    public abstract TransactionType getType();

    /*
     * Factory method to create Transaction objects from a CSV line.
     * 
     * The line should be in the format:
     * 
     * "withdrawal,<accountID>,<amount>" or "deposit,<accountID>,<amount>"
     * 
     * @param line - CSV formatted string
     * 
     * @return Transaction object (either Withdrawal or Deposit)
     * 
     * @throws IllegalArgumentException if the line is null or improperly formatted
     * 
     * or if the transaction type is unknown
     * 
     */
    public static Transaction make(String line) {
        if (line == null) {
            throw new IllegalArgumentException("line must not be null.");
        }
        String[] token = line.split(",");
        String id = token[1];
        Double amount = Double.parseDouble(token[2]);
        if (token[0].equals("withdrawal")) { // TODO use TransactionType
            Transaction wdraw = new Withdrawal(id, amount);
            return wdraw;
        }
        if (token[0].equals("deposit")) { // TODO use TransactionType and make this an else block
            Transaction dep = new Deposit(id, amount);
            return dep;
        } else { // TODO remove
            throw new IllegalArgumentException("Invalid transaction type.");
        }
    }

    /*
     * Return a string representation of this transaction.
     * 
     * @return String in CSV format: "type,accountID,amount"
     * 
     * eg, "withdrawal,wz240833,500.00"
     * 
     */
    @Override
    public String toString() {
        return String.format(
                "%s,%s,%.2f", // format double to 2 decimal places
                getType().name().toLowerCase(),
                getAccountNumber(),
                getAmount());
    }

    /**
     * Create a CSV line holding this transaction's data.
     * 
     * @return Eg, "savings,wz240833,Anna Gomez,8111.00"
     */
    public String toCSV() {
        return toString();
    }
}
