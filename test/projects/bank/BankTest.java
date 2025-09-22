package projects.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projects.bank.Enum.AccountType;

public class BankTest {
    // Our test bank will hold up to 10 accounts for testing purposes.
    private Account[] accounts = new Account[10];
    private Bank bank;

    @BeforeEach
    public void setUp() {
        // This sets three testing accounts with their reference for our test.
        bank = new Bank(10);
        accounts[0] = new Account("A001", AccountType.SAVINGS, 1000.0);
        accounts[1] = new Account("A002", AccountType.SAVINGS, 1500.0);
        accounts[2] = new Account("A003", AccountType.CHECKING, 2000.0);

        // set owner names for clarity in tests
        accounts[0].setAccountOwnerName("Alice");
        accounts[1].setAccountOwnerName("Bob");
        accounts[2].setAccountOwnerName("Alice");

        for (int i = 0; i < 3; i++) {
            bank.addAccount(accounts[i]);
        }
    }

    // this test checks that the number of accounts is correct after setup.
    @Test
    public void testAddAccount() {
        Account newAccount = new Account("A004", AccountType.SAVINGS, 2500.0);
        bank.addAccount(newAccount);
        assertEquals(4, bank.getNumberOfAccounts());
    }

    // This test verifies that we can find the correct account's reference using the
    // account ID.
    @Test
    public void testFindAccountByAccountId() {
        assertEquals(accounts[1], bank.findAccountById("A002"));
    }

    // This test verifies that searching for a non-existent account ID returns null.
    @Test
    public void testFindNonExistentAccount() {
        Account account = bank.findAccountById("A999");
        assertEquals(null, account);
    }

    // This test checks that the number of a specific accoutn owner's accounts is
    // correct.
    @Test
    public void testGetAllAccountsByOwnerName() {
        Account[] expectedAccount = new Account[2];
        expectedAccount[0] = accounts[0];
        expectedAccount[1] = accounts[2];
        assertEquals(expectedAccount[0], bank.getAllAccountsByOwnerName("Alice")[0]);
        assertEquals(expectedAccount[1], bank.getAllAccountsByOwnerName("Alice")[1]);
    }

    // This test checks that the bank does not exceed its capacity when adding
    // accounts.
    @Test
    public void testBankCapacity() {
        // add distinct accounts (do not reuse A001..A003 which were added in setUp)
        for (int i = 4; i <= 6; i++) {
            bank.addAccount(new Account("A00" + i, AccountType.CHECKING, 100.0));
        }

        // now we should have 6 accounts
        assertEquals(6, bank.getNumberOfAccounts());
    }
}
