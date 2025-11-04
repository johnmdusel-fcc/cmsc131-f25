/* comments
 * 
 * add auditing inside execute method
 * 
 * move auditing from bank class into the validate method
*/
package projects.bank;

public class Withdrawal extends Transaction {

    public Withdrawal(String accountID, double transactionAmt) {
        super(accountID, transactionAmt);
    }

    /**
     * /**
     * Returns Withdrawal for a given transaction.
     */
    @Override
    public TransactionType getType() {
        return TransactionType.WITHDRAWAL;
    }

    /**
     *
     * Returns boolean
     * true - to allow the withdrawal to proceed without hold.
     * false - to halt the transaction due to insufficient funds.
     * 
     */
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

    /**
     * Trigger the execution of the withdrawal and
     * affects the account balance.
     */
    @Override
    public void execute(Account account) {
        account.debit(getAmount());
    }
}
