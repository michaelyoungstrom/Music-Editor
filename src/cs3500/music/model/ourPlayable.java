package cs3500.music.model;

/**
 * Created by Tejas on 11/12/2015.
 */
public abstract class ourPlayable {
  private int start;
  private int end;
  private Pitch pitch;
  private int instrument;
  private int volume;

  public ourPlayable(int start, int end, Pitch p, int instrument, int volume) {
    this.start = start;
    this.end = end;
    this.pitch = p;
    this.instrument = instrument;
    this.volume = volume;
  }

  public ourPlayable(int pitchID, int start, int end) {
    this.start = start;
    this.end = end;
    this.pitch = new Pitch(pitchID);
    this.instrument = 0;
    this.volume = 100;
  }

  public ourPlayable() {
  }

  /**
   * Prints this Note as a string
   *
   * @return "x" if the given beat is this note's start and "|" elsewise
   */
  public String toString(boolean isStart, boolean isHeld) {
    if (isStart) {
      return "x";
    } else if (isHeld) {
      return "|";
    } else {
      return ".";
    }
  }

  public int getStart() {
    return this.start;
  }

  public void setStart(int newStart) {
    this.start = newStart;
  }

  public int getEnd() {
    return this.end;
  }

  public int getPitchID() {
    return this.pitch.pitchID;
  }

  public int getVolume() {
    return this.volume;
  }

  public void setVolume(int newVolume) {
    this.volume = newVolume;
  }

  public int getInstrument() {
    return this.instrument;
  }

  public void setInstrument(int newInstr) {
    this.instrument = newInstr;
  }

  public int getDur() {
    return this.getEnd() - this.getStart() + 1;
  }

  public void setDuration(int newDur) {
    this.end = this.getStart() + newDur - 1;
  }

  public Pitch getOurPitch() {
    return this.pitch;
  }

  public int getOurOctave() {
    return this.getOurPitch().getOctave();
  }

  public cs3500.music.model.Pitch getTheirPitch() {
    int p = this.getPitchID();
    float exact = (float) p / 12;
    float notExact = p / 12;

    float rem = exact - notExact;
    float pitFloat = rem * 12;
    int pit = Math.round(pitFloat);

    switch(pit) {
      case 0 :
        return cs3500.music.model.Pitch.A;
      case 1 :
        return cs3500.music.model.Pitch.Asharp;
      case 2 :
        return cs3500.music.model.Pitch.B;
      case 3 :
        return cs3500.music.model.Pitch.C;
      case 4 :
        return cs3500.music.model.Pitch.Csharp;
      case 5 :
        return cs3500.music.model.Pitch.D;
      case 6 :
        return cs3500.music.model.Pitch.Dsharp;
      case 7 :
        return cs3500.music.model.Pitch.E;
      case 8 :
        return cs3500.music.model.Pitch.F;
      case 9 :
        return cs3500.music.model.Pitch.Fsharp;
      case 10 :
        return cs3500.music.model.Pitch.G;
      case 11 :
        return cs3500.music.model.Pitch.Gsharp;
      default:
        throw new IllegalArgumentException("shouldn't get to this, idk");
    }

  }


  /**
   * Defines the pitch as a field in Note
   */
  public static class Pitch {
    //INVARIANT: octave must be greater than 1 and less than 10
    //INVARIANT: pitchID must be between 1 and 128

    NOTE n;
    int octave;
    ACCIDENTAL a;
    int pitchID;

    public Pitch(int pitchID) {
      this.pitchID = pitchID;
      int note = (pitchID % 12) + 1;
      switch (note) {
        case 1:
          n = NOTE.C;
          a = ACCIDENTAL.NATURAL;
          break;
        case 2:
          n = NOTE.C;
          a = ACCIDENTAL.SHARP;
          break;
        case 3:
          n = NOTE.D;
          a = ACCIDENTAL.NATURAL;
          break;
        case 4:
          n = NOTE.D;
          a = ACCIDENTAL.SHARP;
          break;
        case 5:
          n = NOTE.E;
          a = ACCIDENTAL.NATURAL;
          break;
        case 6:
          //E# is F natural
          n = NOTE.F;
          a = ACCIDENTAL.NATURAL;
          break;
        case 7:
          n = NOTE.F;
          a = ACCIDENTAL.SHARP;
          break;
        case 8:
          n = NOTE.G;
          a = ACCIDENTAL.NATURAL;
          break;
        case 9:
          n = NOTE.G;
          a = ACCIDENTAL.SHARP;
          break;
        case 10:
          n = NOTE.A;
          a = ACCIDENTAL.NATURAL;
          break;
        case 11:
          n = NOTE.A;
          a = ACCIDENTAL.SHARP;
          break;
        case 12:
          n = NOTE.B;
          a = ACCIDENTAL.NATURAL;
          break;
        default:
          break;
      }
      octave = (pitchID / 12) + 1;
    }

    /**
     * alternative constructor for a pitch
     */
    public Pitch(NOTE n, ACCIDENTAL a, int octave) {
      this.n = n;
      this.a = a;
      if (octave >= 1 && octave <= 10) {
        this.octave = octave;
      } else {
        throw new IllegalArgumentException("illegal octave");
      }

      int startNum = 0;

      switch (n) {
        case C:
          startNum = 1;
          break;
        case D:
          startNum = 3;
          break;
        case E:
          startNum = 5;
          break;
        case F:
          startNum = 6;
          break;
        case G:
          startNum = 8;
          break;
        case A:
          startNum = 10;
          break;
        case B:
          startNum = 12;
          break;
        default:
          break;
      }
      startNum = startNum + (12 * (octave - 1));

      switch (a) {
        case SHARP:
          startNum++;
          break;
        case NATURAL:
          break;
        case FLAT:
          startNum--;
          break;
      }
    }

    public int getOctave() {

      return this.octave;
    }

    public static String toString(int pitchID) {
      Pitch temp = new Pitch(pitchID);
      String note = temp.n.toString();
      String acc = "";
      String oct = "";
      if (temp.a == ACCIDENTAL.SHARP) {
        acc = "#";
      } else if (temp.a == ACCIDENTAL.NATURAL) {
        acc = " ";
      }

      if (temp.octave < 10) {
        oct = "0" + Integer.toString(temp.octave);
      } else {
        oct = Integer.toString(temp.octave);
      }

      return note + acc + oct;
    }

    protected enum NOTE {
      A, B, C, D, E, F, G;
    }

    protected enum ACCIDENTAL {
      SHARP, FLAT, NATURAL;
    }

  }

}