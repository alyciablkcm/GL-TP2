package theatricalplays;

public class Tragedy extends Play {
    public Tragedy(String name) {
        super(name, TRAGEDY);
    }

    public double calculateAmount(Performance perf){
      double thisAmount = 400.0;
      if (perf.audience > 30) {
          thisAmount += 10.0 * (perf.audience - 30);
      }
      return thisAmount;
    }

    public int calculateVolumeCredits(Performance perf){
      int volumeCredits = Math.max(perf.audience - 30, 0);
      return volumeCredits;
    }
}
