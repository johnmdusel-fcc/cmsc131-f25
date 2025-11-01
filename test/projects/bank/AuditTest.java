package projects.bank;

import static org.junit.Assert.assertEquals;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Scanner;

public class AuditTest {

    private String fileName;
    private Audit audit;

    @BeforeEach
    void setup() {
        fileName = "data/audittest.log";
        try {
            audit = new Audit(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    @Test
    void testRecordNonsufficientFunds() {
        Transaction t = new Withdrawal("id", 50);
        Account a = new CheckingAccount(
                "id", "owner", 25);
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
                                    " [WARN] nonsufficient funds: %s, but account balance is %s",
                                    t.toString(),
                                    a.getCurrentBalance())));

            assertEquals(false, scan.hasNextLine());
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                                    "[INFO]: %s, ending account balance is now %s",
                                    t.toString(),
                                    a.getCurrentBalance())));

            assertEquals(false, scan.hasNextLine());
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}