package cs3500.music.model;

import java.util.Comparator;

public interface Playable {

  /**
   * Gets the pitch of this note.
   *
   * @return the pitch of this note
   */
  public Pitch getPitch();

  /**
   * Gets the octave of this note
   *
   * @return the octave of this note
   */
  public int getOctave();

  /**
   * Returns the numerical representation of the combined pitch and octave.
   * Ex: C0 = 0, Gsharp6 = 80
   *
   * @return the numerical representation of the combined pitch and octave
   */
  public int numericalPitch();

  /**
   * Gets the beat at which this note should start playing
   *
   * @return the beat at which this note should start playing
   */
  public int getStartBeat();

  /**
   * Sets the beat at which this note should start playing
   *
   * @param newStartBeat the beat at which this note should now start playing
   */
  void setStartBeat(int newStartBeat);

  /**
   * Gets the duration of this note, or number of beats this note should last
   *
   * @return the duration of this note
   */
  public int getDuration();

  /**
   * Sets the duration/number of beats of this note to the given int
   *
   * @param newDuration the number of beats this note should now last
   */
  public void setDuration(int newDuration);

  /**
   * Gets the instrument this note should be played with.
   *
   * @return the instrument this note should be played with
   */
  public int getInstrument();

  /**
   * Sets the instrument this note should be played with to the given string.
   *
   * @param instrument the instrument this note should now be played with
   */
  public void setInstrument(int instrument);

  /**
   *  Gets the volume of the note
   */
  public int getVolume();

  /**
   * Sets the volume of the note
   *
   * @param volume the volume of the note [0, 127]
   */
  public void setVolume(int volume);

  /**
   * Returns true if this note starts on the given startBeat
   *
   * @param startBeat the beat at which we want to know if the note starts
   * @return whether this note starts at startBeat
   */
  public boolean startsAt(int startBeat);

  /**
   * Returns true if this note is sustained on the given sustainedBeat
   *
   * @param sustainedBeat the beat at which we want to know if the note is sustained
   * @return whether this note is sustained at sustainedBeat
   */
  public boolean sustainedAt(int sustainedBeat);

  /**
   * Returns a copy of this note (useful for when you want to give them the note,
   * but not allow them to mutate it;
   *
   * @return a copy of this note
   */
  public Note copyOf();

  public final static class Util {
    /**
     * Converts the given letter pitch to a number.
     *
     * @return the number which corresponds to this note's letter pitch.
     */
    public static int pitchToNum(Pitch pitch) {
      switch (pitch) {
        case C: return 0;
        case Csharp: return 1;
        case D: return 2;
        case Dsharp: return 3;
        case E: return 4;
        case F: return 5;
        case Fsharp: return 6;
        case G: return 7;
        case Gsharp: return 8;
        case A: return 9;
        case Asharp: return 10;
        case B: return 11;
        default:
          throw new RuntimeException("This should never happen, but somehow you got "
                  + "an unexpected member of the enumeration pitch.");
      }
    }

    /**
     * Converts the given int, in the range [0, 11], to a pitch
     *
     * @param letterNum the number associated with a certain letter pitch
     * @return the pitch associated with the given number
     */
    public static Pitch numToPitch(int letterNum) {
      switch (letterNum) {
        case 0: return Pitch.C;
        case 1: return Pitch.Csharp;
        case 2: return Pitch.D;
        case 3: return Pitch.Dsharp;
        case 4: return Pitch.E;
        case 5: return Pitch.F;
        case 6: return Pitch.Fsharp;
        case 7: return Pitch.G;
        case 8: return Pitch.Gsharp;
        case 9: return Pitch.A;
        case 10: return Pitch.Asharp;
        case 11: return Pitch.B;
        default:
          throw new RuntimeException("LetterNum " + letterNum + " out of range [0, 11].");
      }
    }

    /**
     * Returns the string representation of a pitch +octave when given its numerical representation
     *
     * @param numericalPitch the numerical representation of the note to be represented as a String
     * @return the String representation of a pitch + octave when given its numerical
     * representation
     */
    public static String NumbertoNoteString(int numericalPitch) {
      if (numericalPitch < 0 || numericalPitch > 131) {
        throw new IllegalArgumentException("Numerical pitch " + numericalPitch
                + "out of range [0, 131]");
      }
      int letterNum = numericalPitch % 12;
      int octave = (int) Math.floor(numericalPitch / 12);
      return numToPitch(letterNum).toString() + Integer.toString(octave);
    }
  }

  /**
   * Represents a comparator that puts Notes in order based om their numerical representation,
   * from smallest to largest
   */
  public static class LowToHigh implements Comparator<Playable> {

    @Override
    public int compare(Playable note1, Playable note2) {
      return note1.numericalPitch() - note2.numericalPitch();
    }

  }

  /**
   * Represents a comparator that puts Notes in order based om their numerical representation,
   * from smallest to largest
   */
  public static class HighToLow implements Comparator<Playable> {

    @Override
    public int compare(Playable note1, Playable note2) {
      return note2.numericalPitch() - note1.numericalPitch();
    }

  }

}
