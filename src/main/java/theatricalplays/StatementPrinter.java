package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
       double totalAmount = 0.0;
       int volumeCredits = 0;
       StringBuffer result = new StringBuffer();
       Customer customer= invoice.customer;
       result.append(String.format("Statement for %s\n", customer.name));

       for (Performance perf : invoice.performances) {
         Play play = plays.get(perf.playID);
         double thisAmount = play.calculateAmount(perf);
         // add volume credits
         volumeCredits += play.calculateVolumeCredits(perf);

         // print line for this order
         result.append(String.format("  %s: %s (%s seats)\n", play.name, formatAmount(thisAmount), perf.audience));
         totalAmount += thisAmount;
     }
       result.append(String.format("Amount owed is %s\n", formatAmount(totalAmount)));
       result.append(String.format("You earned %s credits\n", volumeCredits));
       return result.toString();
     }

   public String render(Invoice invoice, Map<String, Play> plays) {
      double totalAmount = 0.0;
      int volumeCredits = 0;
      StringBuilder html = new StringBuilder();
      Customer customer= invoice.customer;
         html.append("<html><head><title>Invoice</title></head><body>");
         html.append("<h1>Invoice</h1>");
         html.append("<p>Client: " + customer.name + "</p>");
         html.append("<table border=\"1\">");
         html.append("<tr><th>Piece</th><th>Seats sold</th><th colspan='2'>Price</th></tr>");

      for (Performance perf : invoice.performances) {
        Play play = plays.get(perf.playID);
        double thisAmount = play.calculateAmount(perf);
        volumeCredits += play.calculateVolumeCredits(perf);

        html.append("<tr><td>" + play.name + "</td><td>" + perf.audience + "</td><td colspan='2'>" + formatAmount(thisAmount) + "</td></tr>");
         totalAmount += thisAmount;
      }

      
      if (volumeCredits >= 150) {
          totalAmount -= 15.0;
          customer.loyaltyPoints = customer.loyaltyPoints - 150 ;
      }

          html.append("<tr><td colspan='2'><b>Total Owed</b></td><td >" + formatAmount(totalAmount) + "</td></tr>");
          html.append("<tr><td colspan='2'><b>Fidelity points earned</b></td><td>" + customer.loyaltyPoints + "</td></tr>");
          html.append("</table>");
          html.append("<p>Payment is required under 30 days. We can break your knees if you don't do so.</p>");

          html.append("</body></html>");

      return html.toString();
    }

    private String formatAmount(double amount) {
        // Format the amount as currency using the US currency format
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        return currencyFormatter.format(amount);
    }
}
