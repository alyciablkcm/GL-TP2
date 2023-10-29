package theatricalplays;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import java.io.File;

import static org.approvaltests.Approvals.verify;

public class StatementPrinterTests {
  private Customer customer1, customer2;
     private Map<String, Play> plays;
     private Invoice invoice1, invoice2;
     private StatementPrinter statementPrinter;

     @BeforeEach
     void setUp() {
         customer1 = new Customer("BigCo", 123456, 20);
         customer2 = new Customer("Marc", 123456, 200);
         plays = Map.of(
             "hamlet",  Play.createPlay("Hamlet", "tragedy"),
             "as-like", Play.createPlay("As You Like It", "comedy"),
             "othello", Play.createPlay("Othello", "tragedy"));

         invoice1 = new Invoice(customer1, List.of(
             new Performance("hamlet", 55),
             new Performance("as-like", 35),
             new Performance("othello", 40)));

         invoice2 = new Invoice(customer2, List.of(
             new Performance("hamlet", 55),
             new Performance("as-like", 35),
             new Performance("othello", 40)));
         statementPrinter = new StatementPrinter();
     }

    @Test
    void exampleStatement() {
        var result = statementPrinter.print(invoice1, plays);
        verify(result);
    }

    @Test
    void statementWithNewPlayTypes() {
        Assertions.assertThrows(Error.class, () -> {
           Play.createPlay("As You Like It", "pastoral");
        });

        Assertions.assertThrows(Error.class, () -> {
           Play.createPlay("Henry V", "history");
        });
    }

    @Test
    void testGenerateHTMLInvoiceWithLoyaltyPointsUnder150() {
      String testFileName = "test_alycia_invoice1.html";
      assertEquals(20, customer1.loyaltyPoints);
      invoice1.toHTMLFile(plays, testFileName);
      File file = new File(testFileName);
      assertTrue(file.exists());
      assertTrue(file.length() > 0);
      assertEquals(20, customer1.loyaltyPoints);
    }

    @Test
    void testGenerateHTMLInvoiceWithLoyaltyPointsOver150() {
      String testFileName = "test_alycia_invoice2.html";
      assertEquals(200, customer2.loyaltyPoints);
      invoice2.toHTMLFile(plays, testFileName);
      File file = new File(testFileName);
      assertTrue(file.exists());
      assertTrue(file.length() > 0);
      assertEquals(50, customer2.loyaltyPoints);
    }

     @Test
     void testToTEXTFileInvoiceWithLoyaltyPointsUnder150() {
      String testFileName = "test_dahbia_invoice1.txt";
      assertEquals(20, customer1.loyaltyPoints);
      invoice1.toTEXTFile(plays, testFileName);
      File file = new File(testFileName);
      assertTrue(file.exists());
      assertTrue(file.length() > 0);
      assertEquals(20, customer1.loyaltyPoints);
    }

    @Test
    void testToTEXTFileInvoiceWithLoyaltyPointsOver150() {
     String testFileName = "test_dahbia_invoice2.txt";
     assertEquals(200, customer2.loyaltyPoints);
     invoice2.toTEXTFile(plays, testFileName);
     File file = new File(testFileName);
     assertTrue(file.exists());
     assertTrue(file.length() > 0);
     assertEquals(50, customer2.loyaltyPoints);
   }
}
