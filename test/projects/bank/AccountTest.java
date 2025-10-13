package projects.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    public void testDataValidation() {
        Exception e = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Account(null, "name", AccountType.CHECKING, 0.0);
                });
        assertEquals("account ID cannot be empty.", e.getMessage());

        e = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Account("id", null, AccountType.CHECKING, 0.0);
                });
        assertEquals("account owner's name cannot be empty.", e.getMessage());

        e = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Account("id", "Owner Name", null, 1.0);
                });
        assertEquals("type cannot be null.", e.getMessage());
    }

    @Test
    public void makeDataValidation() {
        Exception e = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Account.make(null);
                });
        assertEquals("line must not be null.", e.getMessage());
    }

    @Test
    public void makeTest() {
        String line = ("savings,id,Owner name,0.00");
        Account actualOutput = Account.make(line);
        String expectedResultID = "id";
        String expectedResultOwner = "Owner name";
        AccountType expectedResultType = AccountType.SAVINGS;
        Double expectedResultBalance = 0.00;
        assertEquals(expectedResultID, actualOutput.getID());
        assertEquals(expectedResultOwner, actualOutput.getOwner());
        assertEquals(expectedResultType, actualOutput.getType());
        assertEquals(expectedResultBalance, actualOutput.getCurrentBalance());
    }

    @Test
    public void toCSVDataValidation() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Account.toCSV(null);
                });
        assertEquals("account must not be null.", exception.getMessage());
    }

    @Test
    public void toCSVTest() {
        Account account = new Account("id", "Owner name", AccountType.CHECKING, 1.0);
        String expectedResult = ("checking,id,Owner name,1.0");
        assertEquals(expectedResult, Account.toCSV(account));
    }
}
