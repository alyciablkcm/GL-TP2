package theatricalplays;

public class Play {

  public String name;
  public String type;
  public static final String TRAGEDY = "tragedy";
  public static final String COMEDY = "comedy";

      private Play(String name, String type) {
          this.name = name;
          this.type = type;
      }

      public static Play createPlay(String name, String type) {
          if (!type.equals(TRAGEDY) && !type.equals(COMEDY)) {
              throw new Error("Unknown type: " + type);
          }
          return new Play(name, type);
      }
}
