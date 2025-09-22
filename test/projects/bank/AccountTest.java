package projects.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import projects.bank.Enum.AccountType;

public class AccountTest {

    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account("12345", AccountType.SAVINGS, 1000.0);

    }

    @Test
    public void testGetAccountId() {
        assertEquals("12345", account.getAccountId());
    }

    @Test
    public void testGetBalance() {
        assertEquals(1000.0, account.getBalance());
    }

    @Test
    public void testGetAccountOwnerNameInitiallyNull() {
        assertEquals(null, account.getAccountOwnerName());
    }

    @Test
    public void testAccountNotNullAfterCreation() {
        String expectedOwnerName = "TestOwnerName";
        account.setAccountOwnerName(expectedOwnerName);
        assertEquals(expectedOwnerName, account.getAccountOwnerName());
    }

    @Test
    public void testAccountType() {
        assertEquals(AccountType.SAVINGS, account.getAccountType());
    }

}