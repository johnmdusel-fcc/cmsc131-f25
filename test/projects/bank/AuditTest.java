package projects.bank;

import static org.junit.Assert.assertEquals;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Scanner;

public class AuditTest {

    // Phase 4

    private String fileName;
    private Audit audit;

    /**
     * Setting rhe audit file before each test
     * 
     * data/audittest.log exist in our repo.
     * 
     */
    @BeforeEach
    void setup() {
        fileName = "data/audittest.log";
        try {
            audit = new Audit(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test verifies that the warning message
     * Not Such Account
     * is successfully written in the audit file
     * for a transaction t.
     */
    @Test
    void testRecordNoSuchAccount() {
        Transaction t = new Withdrawal("id", 100);
        audit.recordNoSuchAccount(t);
        audit.close();

        // compare audit file to expected result
        Scanner scan;
        try {
            scan = new Scanner(new File(fileName));

            // read the transaction line
            assertEquals(true, scan.hasNextLine());
            String line = scan.nextLine();
            assertEquals(
                    true,
                    // avoiding timestamps
                    line.contains("[WARN]: no such account: " + t.toString()));

            // confirm end of file
            assertEquals(false, scan.hasNextLine());
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test verifies that the warning message
     * Not Sufficient funds
     * is successfully written in the audit file
     * for a given transaction t and an account a.
     */
    @Test
    void testRecordNonsufficientFunds() {
        Transaction t = new Withdrawal("rp332960", 267.57);
        Account a = new SavingsAccount(
                "rp332960", "Anna Gomez", 111);
        audit.recordNonSufficientFunds(t, a);
        audit.close();

        Scanner scan;
        try {
            scan = new Scanner(new File(fileName));

            assertEquals(true, scan.hasNextLine());
            String line = scan.nextLine();
            assertEquals(
                    true,
                    line.contains(
                            String.format(
                                    "[WARN]: nonsufficient funds: %s, but account balance is %.2f",
                                    t.toString(),
                                    a.getCurrentBalance())));

            assertEquals(false, scan.hasNextLine());
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test verifies that the information message
     * of a successful transaction execution
     * is successfully written in the audit file
     * for a transaction t and a related account a.
     */
    @Test
    void testRecordExecute() {
        Transaction t = new Withdrawal("id", 50);
        Account a = new CheckingAccount(
                "id", "owner", 100);
        audit.recordExecute(t, a);
        audit.close();

        Scanner scan;
        try {
            scan = new Scanner(new File(fileName));

            assertEquals(true, scan.hasNextLine());
            String line = scan.nextLine();
            assertEquals(
                    true,
                    line.contains(
                            String.format(
                                    "[INFO]: %s, ending account balance is now %.2f",
                                    t.toString(),
                                    a.setNewBalance())));

            assertEquals(false, scan.hasNextLine());
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}// end of AuditTest