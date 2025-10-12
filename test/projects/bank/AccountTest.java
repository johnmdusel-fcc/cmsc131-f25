package projects.bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {

    private Account account;

    @BeforeEach
    void setupAccount() {
        account = new Account(
            "wz240833",
            "Anna Gomez",
            8111.00,
            AccountType.SAVINGS
        );
    }

    @Test
    void testDataValidation() {
        Exception e = assertThrows(
            IllegalArgumentException.class,
            () -> {new Account(null, "name", 0.0, AccountType.CHECKING);}
        );
        assertEquals("id cannot be null", e.getMessage());

        e = assertThrows(
            IllegalArgumentException.class,
            () -> {
                new Account("id", null, 0.0, AccountType.CHECKING);}
        );
        assertEquals("ownerName cannot be null", e.getMessage());

        e = assertThrows(
            IllegalArgumentException.class,
            () -> {new Account("id", "Owner Name", 0.0, null);}
        );
        assertEquals("type cannot be null", e.getMessage());
    }

    @Test
    void testMakeThrowsOnNullInput() {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> {Account.make(null);}
        );
        assertEquals(
            "inputLine cannot be null",
            exception.getMessage()
        );
    }

    @Test
    void testMakePreservesData() {
        Account account2 = Account.make(
            "savings,wz240833,Anna Gomez,8111.00"
        );
        assertEquals(
            account.getID(),
            account2.getID()
        );
        assertEquals(
            account.getOwnerName(),
            account2.getOwnerName()
        );
        assertEquals(
            account.getType(),
            account2.getType()
        );
        assertEquals(
            account.getBalance(),
            account2.getBalance(),
            1e-2
        );
    }

    @Test
    void testToCSV() {
        assertEquals(
            "savings,wz240833,Anna Gomez,8111.00",
            account.toCSV()
        );
    }

}

