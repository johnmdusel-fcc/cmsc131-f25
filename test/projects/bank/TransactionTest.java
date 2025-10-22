package projects.bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionTest {

    private Account ckgAccount;
    private Transaction defaultDeposit, defaultWithdrawal;

    @BeforeEach
    void setUp() {
        ckgAccount = new CheckingAccount("id", "owner", 10.0);
        defaultDeposit = new Deposit("id", 2.51);
        defaultWithdrawal = new Withdrawal("id", 1.75);
    }

    @Test
    void testConstructorDataValidation() {
        // TODO
    }

    @Test
    void testMakeThrowsOnNullInput() {
        // TODO
    }

    @Test
    void testMakePreservesData() {
        // TODO
    }

    @Test
    void testValidateDeposit() {
        // TODO
    }

    @Test
    void testValidateWithdrawal() {
        // TODO
    }

    @Test
    void testExecuteDeposit() {
        // TODO
    }

    @Test
    void testExecuteWithdrawal() {
        // TODO
    }

}
