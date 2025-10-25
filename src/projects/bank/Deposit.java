package projects.bank;

public class Deposit extends Transaction {

    public Deposit(String accountID, double amount) {
        super(accountID, amount);
    }

    @Override
    public void execute(Account account) {
        account.credit(getAmount()); // tested by testExecuteDeposit
    }

    @Override 
    public boolean validate(Account account) {
        return true; // tested by testValidateDeposit
    }

}
