package cs3500.music.model;

public enum Pitch {
  C("C"), Csharp("C#"), D("D"), Dsharp("D#"), E("E"),
  F("F"), Fsharp("F#"), G("G"), Gsharp("G#"), A("A"), Asharp("A#"), B("B");

  private final String toString;

  Pitch(String toString) {
    this.toString = toString;
  }

  @Override
  public String toString() {
    return this.toString;
  }
}
