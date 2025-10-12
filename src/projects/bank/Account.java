package projects.bank;

public class Account {

    private final String id;
    private final String ownerName;
    private double balance;
    private final AccountType type;

    /**
     * 
     * @param id Unique alphanumeric id for this account.
     * @param ownerName Name of this account's owner.
     * @param balance Initial balance of this account.
     * @param type Type of account this is.
     * 
     * @throws IllegalArgumentException If id is null or ownerName is null.
     */
    public Account(
        String id,
        String ownerName,
        double balance,
        AccountType type
    ) {
        if (id != null) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("id cannot be null");
        }

        if (ownerName != null) {
            this.ownerName = ownerName;
        } else {
            throw new IllegalArgumentException("ownerName cannot be null");
        }

        if (type != null) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("type cannot be null");
        }
        
        this.balance = balance;
    }

    public String getID() { return id; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() {return balance; }
    public AccountType getType() { return type; }

    /**
     * Factory method for constructing an Account object from a CSV line.
     * @param inputLine Eg, "savings,wz240833,Anna Gomez,8111.00"
     * @return - new Account from supplied values.
     * @throws {@code IllegalArgumentException}  if null {@code input} is null.
     */
    public static Account make(String inputLine) {
        if (inputLine == null) {
            throw new IllegalArgumentException("inputLine cannot be null");
        }
        String[] tokens = inputLine.split(",");
        AccountType type = AccountType.valueOf(tokens[0].toUpperCase());
        String id = tokens[1];
        String ownerName = tokens[2];
        double balance = (double) Double.valueOf(tokens[3]);
        return new Account(id, ownerName, balance, type);
    }

    @Override 
    public String toString() {
        double balancedFormatted = (int) (getBalance() * 100) / 100.0;
        return String.format(
            "%s,%s,%s,%s",
            getType().name().toLowerCase(),
            getID(),
            getOwnerName(),
            balancedFormatted
        );
    }

    /**
     * CSV line holding this account's data.
     * @return Eg, "savings,wz240833,Anna Gomez,8111.00"
     */
    public String toCSV() { return toString(); }

}