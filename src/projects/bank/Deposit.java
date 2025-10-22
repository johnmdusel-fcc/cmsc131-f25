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
    public boolean execute(Account account) {
        if (account != null && getAmount() > 0) {
            account.credit(getAmount());
            return true;
        }
        return false;
    }
}
