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
    public boolean validate(Account account) {
        if (getAmount() < account.getCurrentBalance()) {
            // Account can't debit more than the current balance
            // on the account as overdrafts are not allowed in this bank.
            return true;
        } else {
            System.out.println("Non-Sufficient Funds in this account.");
            return false;
        }
    }

    @Override
    public void execute(Account account) {
        account.debit(getAmount());
    }
}
