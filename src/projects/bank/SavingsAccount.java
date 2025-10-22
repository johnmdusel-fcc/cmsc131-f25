package projects.bank;

public class SavingsAccount extends Account {

    public SavingsAccount(String accountNumber,
            String name,
            double startingBalance) {
        super(accountNumber, name, startingBalance);
    }

    @Override
    public AccountType getType() {
        return AccountType.SAVINGS;
    }

}
