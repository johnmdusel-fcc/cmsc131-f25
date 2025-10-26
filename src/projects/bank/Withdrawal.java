package projects.bank;

public class Withdrawal extends Transaction {

    public Withdrawal(String accountID, double amount) {
        super(accountID, amount);
    }

    @Override
    public void execute(Account account) {
        if (validate(account)) {
            account.debit(getAmount()); // tested by testExecuteWithdrawal
        }
    }

    @Override 
    public boolean validate(Account account) {
        return getAmount() <= account.getBalance();
        // tested by testValidateWithdrawal
    }

    @Override
    public String toCSV() {
        return String.format("%s,%.2f-", accountID, amount);
    }

}
