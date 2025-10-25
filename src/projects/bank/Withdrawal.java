package projects.bank;

public class Withdrawal extends Transaction {

    public Withdrawal(String accountID, double amount) {
        super(accountID, amount);
    }

    @Override
    public void execute(Account account) {
        account.debit(getAmount()); // tested by testExecuteWithdrawal
    }

    @Override 
    public boolean validate(Account account) {
        return getAmount() <= account.getBalance();
        // tested by testValidateWithdrawal
    }

}
