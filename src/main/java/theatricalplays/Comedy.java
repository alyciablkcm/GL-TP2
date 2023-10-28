package theatricalplays;

public class Comedy extends Play {
    public Comedy(String name) {
        super(name, COMEDY);
    }

    public double calculateAmount(Performance perf){
      double thisAmount = 300.0;
      if (perf.audience > 20) {
          thisAmount += 100.0 + 5.0 * (perf.audience - 20);
      }
      thisAmount += 3.0 * perf.audience;
      return thisAmount;
    }

    public int calculateVolumeCredits(Performance perf){
      int volumeCredits = Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      volumeCredits += Math.floor(perf.audience / 5);
      return volumeCredits;
    }
}
