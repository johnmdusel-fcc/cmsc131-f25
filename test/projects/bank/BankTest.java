package projects.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projects.bank.Enum.AccountType;

public class BankTest {
    // Our test bank will hold up to 392 accounts.
    // TODO unnecessary to instantiate accounts on line 11
    private Account[] accounts = new Account[392];
    private Bank bank;

    @BeforeEach
    public void setUp() {
        // This sets three testing accounts with their reference for our test.
        bank = new Bank();
        accounts[0] = new Account("A001", "Alice Johnson", AccountType.SAVINGS, 1000.0);
        accounts[1] = new Account("A002", "Bethy White", AccountType.SAVINGS, 1500.0);
        accounts[2] = new Account("A003", "Alice Johnson", AccountType.CHECKING, 2000.0);

        for (int i = 0; i < 3; i++) {
            bank.addAccount(accounts[i]);
        }
    }

    // this test checks that the number of accounts is correct after setup.
    @Test
    public void testAddAccount() {
        Account newAccount = new Account("A004", "Oliver Smith", AccountType.SAVINGS, 2500.0);
        // TODO: get return value of call on line 32 and test against expected value, like on line 65
        bank.addAccount(newAccount); 
        assertEquals(4, bank.getNumberOfAccounts());
        assertEquals(392, accounts.length);
    }

    // This test verifies that we can find the correct account's reference using the
    // account ID.
    @Test
    public void testFindAccountByAccountId() {
        assertEquals(1, bank.findAccountById("A002"));
    }

    // This test verifies that searching for a non-existent account ID returns null.
    @Test
    public void testFindNonExistentAccount() {
        assertEquals(-1, bank.findAccountById("A999"));
    }

    // This test checks that the number of a specific accoutn owner's accounts is
    // correct.
    @Test
    public void testGetAllAccountsByOwnerName() {
        Account[] expectedAccount = new Account[2];
        expectedAccount[0] = accounts[0];
        expectedAccount[1] = accounts[2];
        assertEquals(expectedAccount[0], bank.getAllAccountsByOwnerName("Alice Johnson")[0]);
        assertEquals(expectedAccount[1], bank.getAllAccountsByOwnerName("Alice Johnson")[1]);
    }

    // This test checks that adding an account with a duplicate ID is handled
    @Test
    public void testAddAccountWithDuplicateId() {
        Account duplicateAccount = new Account("A002", "Anthony Smith", AccountType.CHECKING, 500.0);
        boolean result = bank.addAccount(duplicateAccount);
        assertEquals(false, result);
    }
}