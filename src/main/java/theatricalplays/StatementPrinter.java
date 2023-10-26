package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

  public String print(Invoice invoice, Map<String, Play> plays) {
    int totalAmount = 0;
    int volumeCredits = 0;
    StringBuffer result = new StringBuffer();
    result.append(String.format("Statement for %s\n", invoice.customer));
    // Create a currency formatter to format amounts using the US currency format
    NumberFormat currencyFormatter  = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      int thisAmount = calculateAmount(play,perf);
      // add volume credits
      volumeCredits += calculateVolumeCredits(play,perf);

      // print line for this order
      result.append(String.format("  %s: %s (%s seats)\n", play.name, currencyFormatter.format(thisAmount / 100), perf.audience));
      totalAmount += thisAmount;
    }
    result.append(String.format("Amount owed is %s\n", currencyFormatter.format(totalAmount / 100)));
    result.append(String.format("You earned %s credits\n", volumeCredits));
    return result.toString();
  }

    private int calculateAmount(Play play, Performance perf) {
        int thisAmount = 0;
        switch (play.type) {
            case Play.TRAGEDY:
                thisAmount = 40000;
                if (perf.audience > 30) {
                    thisAmount += 1000 * (perf.audience - 30);
                }
                break;
            case Play.COMEDY:
                thisAmount = 30000;
                if (perf.audience > 20) {
                    thisAmount += 10000 + 500 * (perf.audience - 20);
                }
                thisAmount += 300 * perf.audience;
                break;
            default:
                throw new Error("Type inconnu : " + play.type);
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
