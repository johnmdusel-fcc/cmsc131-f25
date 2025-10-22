package projects.bank;

abstract class Transaction {

    private final String accountID;
    private final double amount;

    // abstract methods, to be overridden by subclasses
    
    // TODO javadoc
    abstract void execute(Account account);
    
    // TODO javadoc
    abstract boolean validate(Account account);

    // concrete methods

    // TODO javadoc
    protected Transaction(String accountID, double amount) {
        // TODO
        throw new UnsupportedOperationException("Student must implement.");
    }

    // TODO javadoc
    protected static Transaction make(String inputLine) {
        // TODO
        throw new UnsupportedOperationException("Student must implement.");
    }

    public String getAccountID() {
        throw new UnsupportedOperationException("Student must implement.");
    }



}
