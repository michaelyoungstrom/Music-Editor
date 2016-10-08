package cs3500.music.util;

public class Tuple<X, Y> {
  public final X x;
  public final Y y;
  public Tuple(X x, Y y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    } else if (other instanceof Tuple) {
      return this.x.equals(((Tuple)other).x) && this.y.equals(((Tuple)other).y);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return this.x.hashCode() + this.y.hashCode();
  }
}