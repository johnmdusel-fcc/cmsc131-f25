package projects.bank;

public class SavingsAccount extends Account {

    public SavingsAccount(
            String accountNumber,
            String name,
            double startingBalance) {
        super(accountNumber, name, startingBalance);
    }

    /**
     * Returns a Savings Account type for a given account.
     */
    @Override
    public AccountType getType() {
        return AccountType.SAVINGS;
    }

}
