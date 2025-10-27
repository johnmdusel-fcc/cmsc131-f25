package projects.bank;

public class Withdrawal extends Transaction {

    public Withdrawal(String accountID, double amount) {
        super(accountID, amount);
    }

    @Override
    public void execute(Account account, Audit audit) {
        account.debit(getAmount()); // tested by testExecuteWithdrawal
        audit.recordValid(this, account);
    }

    @Override 
    public boolean validate(Account account, Audit audit) {
        boolean boolNSF = getAmount() <= account.getBalance();
        if (!boolNSF) {
            audit.recordNSF(this, account);
        }
        return boolNSF;
        // tested by testValidateWithdrawal
    }

    @Override // overrides Object definition, not Transaction definition
    public String toString() {
        return String.format(
            "withdraw $%.2f from account %s", amount, accountID
        );
    }

}
