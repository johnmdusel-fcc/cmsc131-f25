package projects.bank;

public class Deposit extends Transaction {
    public Deposit(String accountID, double transactionAmt) {
        super(accountID, transactionAmt);
    }

    @Override
    public TransactionType getType() {
        return TransactionType.DEPOSIT;
    }

    @Override
    public boolean validate(Account account) {
        return true;
    }

    @Override
    public void execute(Account account) {
        account.credit(getAmount());
    }
}
