package cs3500.music.model;

public class Note extends ourPlayableToPlayableAdapter{
  int start;
  int end; ///could be end
  Pitch pitch;
  int instrument = 10;
  int volume = 100;

  //INVARIANT: start must be non-negative
  //INVARIANT: end must be non-negative and greater or equal to start
  //INVARIANT: end must be at most 4 more than start

  /**
   * Constructs a Note with the given start and end times and with the pitch given as an int
   */
  public Note(int start, int end, int pitchID, int instrument, int volume) {
    this.start = start;
    this.end = end;
    this.pitch = new Pitch(pitchID);
    this.instrument = instrument;
    this.volume = volume;
  }

  /**
   * Constructs a Note with the given start and end times and with the pitch given as a NOTE,
   * ACCIDENTAL, and an octave number
   */
  public Note(int start, int end, Pitch.NOTE n, Pitch.ACCIDENTAL a, int octave, int instrument,
              int volume) {
    this.start = start;
    this.end = end;
    this.pitch = new Pitch(n, a, octave);
    this.instrument = instrument;
    this.volume = volume;
  }

  public Note(int pitchID, int start, int duration) {

    this.start = start;
    this.end = start + duration + 1;
    this.pitch = new Pitch(pitchID);

  }

  /**
   * Compares pitchID of this Note versus the given note
   *
   * @param o Note
   * @return -1 if this is lower than that, 0 if they are equal,
   * 1 if this is higher than that
   */
  public int compareTo(Note o) {

    if (this.pitch.pitchID < o.pitch.pitchID) {
      return -1;
    } else if (this.pitch.pitchID == o.pitch.pitchID) {
      return 0;
    } else {
      return 1;
    }
  }

  public int getPitchID() {
    return this.pitch.pitchID;
  }

  public int getInstrument(){
    return this.instrument;
  }

  public String toString() {
    return Integer.toString(this.start) + " " + Integer.toString(this.end) + " "
      + Integer.toString(this.instrument) + " " + Integer.toString(this.end);
  }

  public int getVolume(){
    return this.volume;
  }

  public int getStart() {
    return this.start;
  }

  public int getEnd(){
    return this.end;
  }

  public void setStart(int start){
    this.start = start;
  }

  public void setEnd(int end){
    this.end = end;
  }


}
