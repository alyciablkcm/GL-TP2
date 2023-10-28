package theatricalplays;

public abstract class Play {
      public String name;
      public String type;
      public static final String TRAGEDY = "tragedy";
      public static final String COMEDY = "comedy";

      protected Play(String name, String type) {
          this.name = name;
          this.type = type;
      }

      public static Play createPlay(String name, String type) {
          if (TRAGEDY.equals(type)) {
              return new Tragedy(name);
          } else if (COMEDY.equals(type)) {
              return new Comedy(name);
          } else {
              throw new Error("Unknown type: " + type);
          }
      }
      public abstract double calculateAmount(Performance perf);
      public abstract int calculateVolumeCredits(Performance perf);
}
