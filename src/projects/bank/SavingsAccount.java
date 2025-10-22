package projects.bank;

public class SavingsAccount extends Account {

    public SavingsAccount(String id, String ownerName, double balance) {
        super(id, ownerName, balance);
    }

    @Override
    public AccountType getType() {
        // TODO
        throw new UnsupportedOperationException("Student must implement.");
    }

}
