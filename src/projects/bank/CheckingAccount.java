package projects.bank;

public class CheckingAccount extends Account {

    public CheckingAccount(
            String accountNumber,
            String name,
            double startingBalance) {
        super(accountNumber, name, startingBalance);
    }

    /**
     * Returns Checking account type for a given account.
     */
    @Override
    public AccountType getType() {
        return AccountType.CHECKING;
    }

}
