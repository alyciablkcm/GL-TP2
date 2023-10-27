package theatricalplays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.approvaltests.Approvals.verify;

public class StatementPrinterTests {

    @Test
    void exampleStatement() {
        Map<String, Play> plays = Map.of(
                "hamlet",  Play.createPlay("Hamlet", "tragedy"),
                "as-like", Play.createPlay("As You Like It", "comedy"),
                "othello", Play.createPlay("Othello", "tragedy"));

        Invoice invoice = new Invoice("BigCo", List.of(
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
}
