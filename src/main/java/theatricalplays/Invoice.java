package theatricalplays;

import java.util.List;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Invoice {

  public String customer;
  public List<Performance> performances;

  public Invoice(String customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;
  }

  public void toHTMLFile(Map<String, Play> plays, String fileName) {
    StatementPrinter statementPrinter = new StatementPrinter();
    String htmlInvoice = statementPrinter.render(this,plays);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
      writer.write(htmlInvoice);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
