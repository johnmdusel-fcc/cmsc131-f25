package projects.bank;

public class CheckingAccount extends Account {

    public CheckingAccount(String id, String ownerName, double balance) {
        super(id, ownerName, balance);
    }

    @Override
    public AccountType getType() {
        // TODO
        throw new UnsupportedOperationException("Student must implement.");
    }

}
