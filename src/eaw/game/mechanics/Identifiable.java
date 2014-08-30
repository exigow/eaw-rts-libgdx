package eaw.game.mechanics;

public abstract class Identifiable implements Comparable<Identifiable> {

  private static int idIterator = 0;
  private final int id;

  public Identifiable() {
    id = generateId();
  }

  private int generateId() {
    return idIterator++;
  }

  public int getId() {
      return id;
  }

  @Override
  public int compareTo(Identifiable object) {
      return this.id - object.id;
  }

  @Override
  public String toString() {
      return "<" + id + ">";
  }

}
