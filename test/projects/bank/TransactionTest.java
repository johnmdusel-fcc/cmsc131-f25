package projects.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionTest {

    private Account ckgAccount;
    private Transaction defaultDeposit, defaultWithdrawal;

    /*
     * Setting up the transaction class with
     * a checking account,
     * a deposit,
     * and a withdrawal transaction.
     * they will be used for testing.
     */

    @BeforeEach
    void setUp() {
        ckgAccount = new CheckingAccount("id", "owner", 10.0);
        defaultDeposit = new Deposit("id", 2.51);
        defaultWithdrawal = new Withdrawal("id", 1.75);
    }

    /*
     * Test to confirm that each transaction paramter is valid.
     * exceptions will be thrown on invalid parameter.
     */
    @Test
    void testConstructorDataValidation() {
        Exception e = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    defaultDeposit = new Deposit(null, 2.51);
                    ;
                });
        assertEquals("account number cannot be null.", e.getMessage());

        e = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    defaultDeposit = new Deposit("id", -2.51);
                    ;
                });
        assertEquals("transaction amount must be positive.", e.getMessage());

        e = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    defaultWithdrawal = new Withdrawal(null, 1.75);
                    ;
                });
        assertEquals("account number cannot be null.", e.getMessage());

        e = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    defaultWithdrawal = new Withdrawal("id", -1.75);
                    ;
                });
        assertEquals("transaction amount must be positive.", e.getMessage());
    }

    /*
     * Test confirms that make() will thrown an exception
     * when passed a null string input.
     */
    @Test
    void makeThrowsOnNullInputTest() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Transaction.make(null);
                });
        assertEquals(
                "line must not be null.",
                exception.getMessage());
    }

    /*
     * Test verifies that transaction data are preserved during the factory process.
     */
    @Test
    void makePreservesDataTest() {
        Transaction defaultDeposit2 = Transaction.make("deposit,id,2.51");
        assertEquals(
                defaultDeposit.getAccountNumber(),
                defaultDeposit2.getAccountNumber());
        assertEquals(
                defaultDeposit.getAmount(),
                defaultDeposit2.getAmount(), 1e-2);
        assertEquals(
                defaultDeposit.getType(),
                defaultDeposit2.getType());

        Transaction defaultWithdrawal2 = Transaction.make(
                "withdrawal,id,1.75");
        assertEquals(
                defaultWithdrawal.getAccountNumber(),
                defaultWithdrawal2.getAccountNumber());
        assertEquals(
                defaultWithdrawal.getAmount(),
                defaultWithdrawal2.getAmount(), 1e-2);
        assertEquals(
                defaultWithdrawal.getType(),
                defaultWithdrawal2.getType());

    }

    /*
     * Test confirms that validate() a deposit returns true.
     */
    @Test
    void validateDepositTest() {
        assertEquals(true, defaultDeposit.validate(ckgAccount));
    }

    /*
     * Test confirms that validate() a Withdrawal returns "true" when
     * the transaction amount is less than the account balance.
     * otherwise, it will return "false".
     */
    @Test
    void testValidateWithdrawal() {
        Transaction defaultWithdrawal2 = new Withdrawal("id", 11.75);
        assertEquals(true, defaultWithdrawal.validate(ckgAccount));
        assertEquals(false, defaultWithdrawal2.validate(ckgAccount));
    }

    /*
     * Test confirms that execute() a deposit successfully work
     * because the new account balance increases by the transaction amount.
     */
    @Test
    void executeDepositTest() {
        ckgAccount.credit(defaultDeposit.getAmount());
        double endingBalance = ckgAccount.getCurrentBalance();
        assertEquals(12.51, endingBalance);
    }

    /*
     * Test confirms that execute() a withdrawal successfully work
     * because the new account balance decreases by the transaction amount.
     */
    @Test
    void testExecuteWithdrawal() {
        ckgAccount.debit(defaultWithdrawal.getAmount());
        double endingBalance = ckgAccount.getCurrentBalance();
        assertEquals(8.25, endingBalance);
    }

}// end: class TransactionTest
