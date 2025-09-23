package projects.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projects.bank.Enum.AccountType;

public class AccountTest {

    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account("12345", "Alice Johnson", AccountType.SAVINGS, 1000.0);

    }

    @Test
    public void testGetAccountId() {
        assertEquals("12345", account.getAccountId());
    }

    @Test
    public void testGetCurrentBalance() {
        assertEquals(1000.0, account.getCurrentBalance());
    }

    @Test
    public void testAccounOwnerName() {
        assertEquals("Alice Johnson", account.getAccountOwnerName());
    }

    @Test
    public void testAccountType() {
        assertEquals(AccountType.SAVINGS, account.getAccountType());
    }

}