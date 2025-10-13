package projects.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {

        private Bank bank;
        private Account acct;

        /**
         * This is our sample account already set up in the bank.
         * 
         */
        @BeforeEach
        void setup() {
                bank = new Bank();
                acct = new Account(
                                "A001",
                                "Alice Johnson",
                                AccountType.SAVINGS,
                                1000.0);
        }

        /**
         * 
         * Check if Bank account data is non null;
         */
        @Test
        void testAddDataValidation() {
                Exception e = assertThrows(
                                IllegalArgumentException.class,
                                () -> {
                                        bank.add(null);
                                });
                assertEquals("account must not be null.", e.getMessage());
        }

        /**
         * Check that a new account was successfully added.
         * 
         */
        @Test
        void testAddAccount() {
                // add account that's absent from bank's accounts
                boolean addAccountResult = bank.add(acct);
                assertEquals(
                                true,
                                addAccountResult,
                                "bank.add should return true");
                assertEquals(
                                1,
                                bank.getCount(),
                                "bank.getCount should be 1");

                // add account that's present in bank's accounts (no effect)
                addAccountResult = bank.add(acct);
                assertEquals(
                                false,
                                addAccountResult,
                                "bank.add should return false");
                assertEquals(
                                1,
                                bank.getCount(),
                                "bank.getCount should still be 1");
        }
        /*
         * This test confirms that a full bank database
         * successfully increases after a new account is added.
         */

        @Test
        void testAddAccountOverflow() {
                for (int idx = 0; idx <= 100; idx++) {
                        Integer id = idx;
                        bank.add(
                                        new Account(
                                                        id.toString(),
                                                        "Alice Johnson",
                                                        AccountType.SAVINGS,
                                                        1000.0));
                }

                // also serves as a test for getCount
                assertEquals(
                                101,
                                bank.getCount(),
                                "bank should hold 101 accounts");
        }

        /**
         * Data Validation for account ID to be non null.
         */
        @Test
        void testFindDataValidation() {
                Exception e = assertThrows(
                                IllegalArgumentException.class,
                                () -> {
                                        bank.find(null);
                                });
                assertEquals("accountID must not be null.", e.getMessage());
        }

        /**
         * This test verifies that searching for an existing account ID returns the
         * correct
         * index.
         * 
         */
        @Test
        void testFind() {
                bank.add(acct);
                assertEquals(
                                0,
                                bank.find(acct.getID()),
                                "acct should be at index 0");
                assertEquals(
                                -1,
                                bank.find("id1"),
                                "result should be -1 when finding absent account");
        }

        /*
         * This test validates the parameters of the LoadAccounts method.
         * 
         */
        @Test
        void testLoadAccountsDataValidation() throws IOException {
                // When the line is null
                File tempFile = new File("test_null_line.csv");
                FileWriter fw = new FileWriter(tempFile);
                fw.write("\n");
                fw.close();

                Exception e = assertThrows(IllegalArgumentException.class, () -> {
                        Bank.loadAccounts("test_null_line.csv");
                });
                assertEquals("line must not be null.", e.getMessage());

                // Clean up
                tempFile.delete();

                // when the file cannot be found
                // Remove the file if it exists
                File file = new File("Accounts.csv");
                if (file.exists()) {
                        file.delete();
                }
                boolean result;

                result = Bank.loadAccounts("Accounts.csv");

                assertEquals(false, result, "Should return false if file not found");
        }

        /*
         * This test read a file to add, transform each line into an account
         * and add that account to the bank.
         */
        @Test
        public void LoadAccountsTest() throws IOException {
                // Create a temporary file with account data
                File tempFile = new File("test.csv");
                FileWriter fw = new FileWriter(tempFile);
                fw.write("savings,A002,Bob Smith,500.0\n");
                fw.write("checking,A003,Carol Lee,250.0\n");
                fw.close();

                boolean result = Bank.loadAccounts("test.csv");
                assertEquals(true, result, "Should return true when accounts are loaded");

                // Clean up
                tempFile.delete();
        }

        /*
         * This test confirms that the system will return false
         * if the file is non existent.
         */
        @Test
        public void writeAccountsDataValidation() {
                // Try writing to a directory (should fail)
                boolean result = Bank.writeAccounts("/nonexistent_dir/test.csv");
                assertEquals(false, result, "writeAccounts should return false if file cannot be written");
        }

        /*
         * This test verifies writing accounts back into a new file is successful.
         * 
         */
        @Test
        public void writeAccountsTest() throws IOException {
                // Setup: create bank and add accounts
                File file = new File("test_write_success.csv");
                Account account1 = new Account("id1", "Owner1", AccountType.SAVINGS, 100.0);
                Account account2 = new Account("id2", "Owner2", AccountType.CHECKING, 200.0);
                bank.add(account1);
                bank.add(account2);

                boolean result = Bank.writeAccounts("test_write_success.csv");
                assertEquals(true, result, "writeAccounts should return true when accounts are written");

                // Clean up
                file.delete();
        }
}