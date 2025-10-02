package projects.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projects.bank.Account.AccountType;

public class BankTest {

    private Bank bank = new Bank();

    /**
     * This is our sample account already set up in the bank.
     * 
     */
    @BeforeEach
    void setup() {
        Account existingAccount = new Account("A001", "Alice Johnson", AccountType.SAVINGS, 1000.0);
        bank.add(existingAccount);
    }

    /**
     * Check that a new account was successfully added.
     * 
     */
    @Test
    void testAddAccountIsSuccessful() {
        Account newAccount = new Account("A002", "Oliver Smith", AccountType.SAVINGS, 2500.0);
        boolean expectedResult = true;
        assertEquals(expectedResult, bank.add(newAccount));
    }

    /**
     * Check that a new account was NOT added successfully because it
     * already exist.
     * 
     */
    @Test
    void testAddAccountIsNotSuccessful() {
        Account toAdd = new Account("A001", "Alice Johnson", AccountType.SAVINGS, 1000.0);
        boolean expectedResult = false;
        assertEquals(expectedResult, bank.add(toAdd));
    }

    /**
     * This test verifies that searching for an existing account ID returns the
     * correct
     * index.
     * 
     */
    @Test
    void testFindAccountByAccountId() {
        assertEquals(0, bank.find("A001"));
    }

    /**
     * This test verifies that searching for a non-existent account ID returns -1.
     * 
     */
    @Test
    void testFindNonExistentAccount() {
        assertEquals(-1, bank.find("A999"));
    }

    /**
     * This test verifies that the number of active accounts is returned correctly.
     * 
     */
    @Test
    void testGetNumberOfAccounts() {
        assertEquals(1, bank.getCount());
    }

    /**
     * This test verifies that all accounts for a given owner name are returned
     * correctly.
     * 
     */
    @Test
    void testGetAccounts() {
        Account secondAccount = new Account("A003", "Alice Johnson", AccountType.CHECKING, 2500.0);
        bank.add(secondAccount);
        int expectNumberOfAccounts = 2;
        assertEquals(expectNumberOfAccounts, bank.getAccounts("Alice Johnson").length);
    }
}