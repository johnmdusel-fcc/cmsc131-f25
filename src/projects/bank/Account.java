package projects.bank;

import projects.bank.Enum.AccountType;

public class Account {

    private final String ACCOUNTID;
    private final String ACCOUNTOWNERNAME;
    private double currentBalance;
    private final AccountType ACCOUNTTYPE;

    public Account(String accountNumber, String Name, AccountType type, double startingBalance) {

        /**
         * Each account will hold the following values:
         * 
         * @param ACCOUNTID        - alphanumeric Unique account identifier, definitive
         *                         value
         * 
         * @param ACCOUNTOWNERNAME - Name of the account owner, could be repeated across
         *                         accounts, definitive value
         * 
         * @param currentBalance   - Current balance of the account, could be negative ,
         *                         variable value
         * 
         * @param ACCOUNTTYPE      - Type of account (Checking, Savings), definitive
         *                         value
         * 
         **/
        //Added data validation on Account ID and Account owner name.
        if (accountNumber == null){
            throw new IllegalArgumentException (
                "Account ID cannot be empty."
            );
        }
        if (Name == null){
            throw new IllegalArgumentException (
                "Account owner's name cannot be empty."
            );
        }
        
        ACCOUNTID = accountNumber;
        ACCOUNTOWNERNAME = Name;
        ACCOUNTTYPE = type;
        currentBalance = startingBalance;
    }

    public String getAccountId() {
    return ACCOUNTID;
    }

    public String getAccountOwnerName() {
    return ACCOUNTOWNERNAME;
    }

    public double getCurrentBalance() {
    return currentBalance;
    }

    public AccountType getAccountType() {
    return ACCOUNTTYPE;
    }
}