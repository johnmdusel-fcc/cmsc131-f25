package projects.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projects.bank.Account.AccountType;

public class BankTest {

    private Bank bank = new Bank();

    /**
     * Set up a bank with a few accounts before each test.
     *
     */
    @BeforeEach
    public void setUp() {
        Account[] accounts = new Account[392];// why do we need this line?
        accounts[0] = new Account("A001", "Alice Johnson", AccountType.SAVINGS, 1000.0);
        accounts[1] = new Account("A002", "Bethy White", AccountType.SAVINGS, 1500.0);
        accounts[2] = new Account("A003", "Alice Johnson", AccountType.CHECKING, 2000.0);

        for (int i = 0; i < accounts.length; i++) {
            bank.addAccount(accounts[i]);
        }
    }

    /**
     * Check that a new account was successfully added.
     * 
     */
    @Test
    public void testAddAccountIsSuccessful() { // it says this point to null pointer exception
        Account newAccount = new Account("A004", "Oliver Smith", AccountType.SAVINGS, 2500.0);
        boolean expectedResult = true;
        assertEquals(expectedResult, bank.addAccount(newAccount));
    }

    /**
     * Check that a new account was NOT added successfully because it
     * already exist.
     * 
     */
    @Test
    public void testAddAccountIsNotSuccessful() {
        Account existingAccount = new Account("A001", "Alice Johnson", AccountType.SAVINGS, 1000.0);
        bank.addAccount(existingAccount);
        boolean expectedResult = false;
        assertEquals(expectedResult, bank.addAccount(existingAccount));
    }

    /**
     * This test verifies that searching for an existing account ID returns the
     * correct
     * index.
     * 
     */
    @Test
    public void testFindAccountByAccountId() {
        assertEquals(1, bank.findAccountById("A002"));
    }

    /**
     * This test verifies that searching for a non-existent account ID returns -1.
     * 
     */
    @Test
    public void testFindNonExistentAccount() {
        assertEquals(-1, bank.findAccountById("A999"));
    }

    /**
     * This test verifies that the number of active accounts is returned correctly.
     * 
     */
    @Test
    public void testGetNumberOfAccounts() {
        assertEquals(4, bank.getNumberOfAccounts());
    }

    /**
     * This test verifies that all accounts for a given owner name are returned
     * correctly.
     * 
     */
    @Test
    public void testGetAllAccountsByOwnerName() {
        Account[] aliceJohnsonAccounts = new Account[2];
        assertEquals(aliceJohnsonAccounts[0], bank.getAllAccountsByOwnerName("Alice Johnson")[0]);
        assertEquals(aliceJohnsonAccounts[1], bank.getAllAccountsByOwnerName("Alice Johnson")[1]);
    }
}