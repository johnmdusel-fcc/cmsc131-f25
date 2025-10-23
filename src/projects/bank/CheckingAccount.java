package projects.bank;

public class CheckingAccount extends Account {

    public CheckingAccount(
        String accountNumber,
        String name,
        double startingBalance
    ) {
        super(accountNumber, name, startingBalance);
    }

    @Override
    public AccountType getType() {
        return AccountType.CHECKING;
    }

}
