package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

  public String print(Invoice invoice, Map<String, Play> plays) {
    double totalAmount = 0.0;
    int volumeCredits = 0;
    StringBuffer result = new StringBuffer();
    result.append(String.format("Statement for %s\n", invoice.customer));
    // Create a currency formatter to format amounts using the US currency format
    NumberFormat currencyFormatter  = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      double thisAmount = calculateAmount(play,perf);
      // add volume credits
      volumeCredits += calculateVolumeCredits(play,perf);

      // print line for this order
      result.append(String.format("  %s: %s (%s seats)\n", play.name, currencyFormatter.format(thisAmount), perf.audience));
      totalAmount += thisAmount;
    }
    result.append(String.format("Amount owed is %s\n", currencyFormatter.format(totalAmount)));
    result.append(String.format("You earned %s credits\n", volumeCredits));
    return result.toString();
  }

    private double calculateAmount(Play play, Performance perf) {
         double thisAmount = 0.0;
         switch (play.type) {
             case Play.TRAGEDY:
                 thisAmount = 400.0;
                 if (perf.audience > 30) {
                     thisAmount += 10.0 * (perf.audience - 30);
                 }
                 break;
             case Play.COMEDY:
                 thisAmount = 300.0;
                 if (perf.audience > 20) {
                     thisAmount += 100.0 + 5.0 * (perf.audience - 20);
                 }
                 thisAmount += 3.0 * perf.audience;
                 break;
             default:
                 throw new Error("Unknown type: " + play.type);
         }
         return thisAmount;
     }

    private int calculateVolumeCredits(Play play, Performance perf) {
        int volumeCredits = Math.max(perf.audience - 30, 0);
         // add extra credit for every ten comedy attendees
        if (Play.COMEDY.equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);
        return volumeCredits;
    }


}
