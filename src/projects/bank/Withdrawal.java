package projects.bank;

public class Withdrawal extends Transaction {
    public Withdrawal(String accountID, double transactionAmt) {
        super(accountID, transactionAmt);
    }

    @Override
    public TransactionType getType() {
        return TransactionType.WITHDRAWAL;
    }

    @Override
    public boolean execute(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("account cannot be null.");
        }
        account.debit(getAmount());
        return true;
    }
}
