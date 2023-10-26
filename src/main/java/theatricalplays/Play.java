package theatricalplays;

public class Play {

  public String name;
  public String type;
  public static final String TRAGEDY = "tragedy";
  public static final String COMEDY = "comedy";

  public Play(String name, String type) {
    this.name = name;
    this.type = type;
  }
}
