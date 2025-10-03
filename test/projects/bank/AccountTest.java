package projects.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {

    private Account account;

    @BeforeEach
    public void setUp() {
        // easier to read when long lines are formatted like this
        account = new Account(
                "12345",
                "Alice Johnson",
                AccountType.SAVINGS,
                1000.0);
    }

    @Test
    void constructorThrowsForNullAccountId() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Account(
                            null,
                            "Alice Johnson",
                            AccountType.SAVINGS,
                            1000.0);
                });
        assertEquals("Account ID cannot be empty.",
                exception.getMessage());
    }

    @Test
    void constructorThrowsForNullAccountOwnerName() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Account(
                            "12345",
                            null,
                            AccountType.SAVINGS,
                            1000.0);
                });
        assertEquals("Account owner's name cannot be empty.",
                exception.getMessage());
    }

    @Test
    public void testGetAccountId() {
        assertEquals("12345", account.getID());
    }

    @Test
    public void testGetCurrentBalance() {
        assertEquals(1000.0, account.getCurrentBalance());
    }

    @Test
    public void testAccounOwnerName() {
        assertEquals("Alice Johnson", account.getOwner());
    }

    @Test
    public void testAccountType() {
        assertEquals(AccountType.SAVINGS, account.getType());
    }
}