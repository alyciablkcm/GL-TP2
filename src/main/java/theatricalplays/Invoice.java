package theatricalplays;

import java.util.List;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Invoice {

  public Customer customer;
  public List<Performance> performances;
  public StatementPrinter statementPrinter;

  public Invoice(Customer customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;
    this.statementPrinter = new StatementPrinter();
  }

  public void toHTMLFile(Map<String, Play> plays, String fileName) {
      exportToFile(fileName, statementPrinter.render(this, plays));
      }

  public void toTEXTFile(Map<String, Play> plays, String fileName) {
      exportToFile(fileName, statementPrinter.print(this, plays));
  }

  private void exportToFile(String fileName, String invoice) {
       try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
           writer.write(invoice);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
