package theatricalplays;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import java.io.File;

import static org.approvaltests.Approvals.verify;

public class StatementPrinterTests {

    @Test
    void exampleStatement() {
        Customer c = new Customer("BigCo", 123456);
        Play p1 = new Tragedy("Hamlet");
        Play p2 = new Comedy("As You Like It");
        Map<String, Play> plays = Map.of(
                "hamlet",  p1,
                "as-like", p2,
                "othello", Play.createPlay("Othello", "tragedy"));

        Invoice invoice = new Invoice(c, List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays);

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
    void testToHTMLFile() {
      Customer c = new Customer("BigCo", 123456);
      Map<String, Play> plays = Map.of(
              "hamlet",  Play.createPlay("Hamlet", "tragedy"),
              "as-like", Play.createPlay("As You Like It", "comedy"),
              "othello", Play.createPlay("Othello", "tragedy"));

      Invoice invoice = new Invoice(c, List.of(
              new Performance("hamlet", 55),
              new Performance("as-like", 35),
              new Performance("othello", 40)));
      String testFileName = "test_alycia_invoice.html";
      invoice.toHTMLFile(plays, testFileName);
      //  Vérifier si le fichier existe et a une taille non nulle.
      File file = new File(testFileName);
      assertTrue(file.exists());
      assertTrue(file.length() > 0);
    }

     @Test
     void testToTEXTFile() {
        
      Customer c = new Customer("BigCo", 123456);
      Map<String, Play> plays = Map.of(
              "hamlet",  Play.createPlay("Hamlet", "tragedy"),
              "as-like", Play.createPlay("As You Like It", "comedy"),
              "othello", Play.createPlay("Othello", "tragedy"));

      Invoice invoice = new Invoice(c, List.of(
              new Performance("hamlet", 55),
              new Performance("as-like", 35),
              new Performance("othello", 40)));
      String testFileName = "test_dahbia_invoice.txt";
      invoice.toTEXTFile(plays, testFileName);
      //  Vérifier si le fichier existe et a une taille non nulle.
      File file = new File(testFileName);
      assertTrue(file.exists());
      assertTrue(file.length() > 0);
    }
}
