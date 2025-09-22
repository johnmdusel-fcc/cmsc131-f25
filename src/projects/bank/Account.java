package projects.bank;

import projects.bank.Enum.AccountType;

public class Account {
    private String accountId;
    private String accountOwnerName;
    private double currentBalance;
    private AccountType accountType;

    public Account(String accountNumber, AccountType type, double startingBalance) {
        accountId = accountNumber;
        accountType = type;
        currentBalance = startingBalance;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountOwnerName() {
        return accountOwnerName;
    }

    public void setAccountOwnerName(String name) {
        this.accountOwnerName = name;
    }

    // update method name to getCurrentBalance()
    public double getBalance() {
        return currentBalance;
    }

    public AccountType getAccountType() {
        return accountType;
    }
}