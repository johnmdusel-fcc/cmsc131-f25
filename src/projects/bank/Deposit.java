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
        // TODO remove this logic
        //     amount > 0 already verified in constructor (DRY)
        //     account != null guaranteed by Accoubt.make and Account constructor
        if (account != null && getAmount() > 0) {
            account.credit(getAmount());
            return true;
        }
        return false;
    }
}
