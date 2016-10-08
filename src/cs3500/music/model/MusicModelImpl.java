package cs3500.music.model;

import java.util.ArrayList;

public class MusicModelImpl implements musicmodel {

  //INVARIANT: currBeat must be non-negative
  //INVARIANT: tempo must be non-negative
  //INVARIANT: lastBeat must be non-negative and can never be less than currBeat
  //INVARIANT: highestPitch must be non-negative and >= than lowestPitch and less than or
  //          equal to 128
  //INVARIANT: lowestPitch must be greater than or equal to 1

  private ArrayList<ourPlayable> music = new ArrayList<>();
  private int currBeat;
  private int tempo;
  private int lastBeat = 0;
  private int highestPitch = 1;
  private int lowestPitch = 128;
  private int startMeasure = 0;
  private int endMeasure = -1;

  /**
   * Sets default values for MusicModelImpl
   */
  public static class Builder {//implements CompositionBuilder<musicmodel>{
    private ArrayList<ourPlayable> music = new ArrayList<>();
    private int currBeat;
    private int tempo;
    private int lastBeat = 0;
    private int highestPitch = 1;
    private int lowestPitch = 128;


    /**
     * Initializes music to the given arraylist, can be empty of filled
     *
     * @return this builder
     */
    public Builder music(ArrayList<ourPlayable> music) {
      this.music = music;
      return this;
    }

    /**
     * Checks that currBeat is > 0
     *
     * @return Builder
     */
    public Builder currBeat(int currBeat) {
      if (currBeat >= 0) {
        this.currBeat = currBeat;
      } else {
        throw new IllegalArgumentException("negative beat number");
      }
      return this;
    }

    /**
     * Checks that tempo is > 0
     *
     * @return Builder
     */
    public Builder setTempo(int tempo) {
      if (tempo <= 0) {
        throw new IllegalArgumentException("illegal tempo");
      } else {
        this.tempo = tempo;
      }
      return this;
    }

    /**
     * Finds the last beat if the given music was filled
     *
     * @return Builder
     */
    public Builder lastBeat() {
      for (int x = 0; x < music.size(); x++) {
        if (music.get(x).getEnd() > this.lastBeat) {
          this.lastBeat = music.get(x).getEnd();
        }
      }
      return this;
    }

    /**
     * Finds the highestPitch if the given music was filled
     *
     * @return Builder
     */
    public Builder highestPitch() {
      for (int x = 0; x < music.size(); x++) {
        Note n = (Note) music.get(x);
        if (highestPitch < n.getPitchID()) {
          highestPitch = n.getPitchID();
        }
      }
      return this;
    }

    /**
     * Finds the lowestPitch if the given music was filled
     *
     * @return Builder
     */
    public Builder lowestPitch() {
      for (int x = 0; x < music.size(); x++) {
        Note n = (Note) music.get(x);
        if (lowestPitch > n.getPitchID()) {
          lowestPitch = n.getPitchID();
        }
      }
      return this;
    }

    /**
     * @return MusicModelImpl
     */
    public MusicModelImpl build() {
      return new MusicModelImpl(this);
    }

  }

  /**
   * Sets defaults as defined by Builder sorts Notes in Music
   */
  public MusicModelImpl(Builder builder) {
    this.music = builder.music;
    this.currBeat = builder.currBeat;
    this.tempo = builder.tempo;
    this.lastBeat = builder.lastBeat;
    this.lowestPitch = builder.lowestPitch;
    this.highestPitch = builder.highestPitch;
    this.sortMusic();
    this.findLastBeat();
  }

  public MusicModelImpl(ArrayList<ourPlayable> notes, int tempo) {
    this.music = notes;
    this.tempo = tempo;
    this.sortMusic();
    this.findLastBeat();
    this.findLowestPitch();
    this.findHighestPitch();
  }

  public MusicModelImpl(){

  }

  @Override
  public int getSongLength() {
    return this.lastBeat;
  }

  @Override
  public void findLastBeat() {

    for (int x = 0; x < music.size(); x++) {
      ourPlayable n = music.get(x);
      Note m = (Note) n;

      if (m.end > this.lastBeat) {
        this.lastBeat = m.end;
      }
    }
  }

  @Override
  public void findLowestPitch() {
    for(ourPlayable y: this.music) {
      if(this.lowestPitch > y.getPitchID()) {
        this.lowestPitch = y.getPitchID();
      }
    }
  }

  @Override
  public void findHighestPitch() {
    for (int x = 0; x < music.size(); x++) {
      if (highestPitch < music.get(x).getPitchID()) {
        highestPitch = music.get(x).getPitchID();
      }
    }
  }

  @Override
  public boolean songOver() {
    return this.currBeat == this.lastBeat;
  }

  @Override
  public int currBeat() {
    return this.currBeat;
  }

  @Override
  public ArrayList<ourPlayable> getNotes() {
    return music;
  }

  @Override
  public ArrayList<ourPlayable> getNotesAt(int beat) {
    ArrayList<ourPlayable> notesAt = new ArrayList<>();
    for (int x = 0; x < music.size(); x++) {
     /* if (music.get(x).start <= beat &&
        music.get(x).end >= beat) {
        notesAt.add(music.get(x));
      }*/
      if(music.get(x).getStart() == beat) {
        notesAt.add(music.get(x));
      }
    }
    return notesAt;
  }

  @Override
  public void addNote(ourPlayable n) {
    Note m = (Note) n;
    if (!music.contains(n)) {
      music.add(n);
      if (n.getEnd() > this.lastBeat) {
        this.lastBeat = n.getEnd();
      }
      if (m.getPitchID() > highestPitch) {
        this.highestPitch = m.getPitchID();
      }
      if (m.getPitchID() < lowestPitch) {
        this.lowestPitch = m.getPitchID();
      }
      this.sortMusic();
    }
  }

  @Override
  public void addNotes(ArrayList<ourPlayable> n) {
    for (int x = 0; x < n.size(); x++) {
      if (!music.contains(n.get(x))) {
        music.add(n.get(x));
        if (n.get(x).getEnd() > this.lastBeat) {
          this.lastBeat = n.get(x).getEnd();
        }
        if (n.get(x).getPitchID() > highestPitch) {
          this.highestPitch = n.get(x).getOurPitch().pitchID;
        }
        if (n.get(x).getPitchID() < lowestPitch) {
          this.lowestPitch = n.get(x).getPitchID();
        }
      }
    }
    this.sortMusic();
  }

  @Override
  public void deleteNote(ourPlayable n) {

    Note m = (Note) n;
    for (int i = 0; i < music.size(); i++){
      Note temp = (Note) music.get(i);

      if (m.getStart() == temp.getStart() &&
              m.getDur() == temp.getDur() &&
              m.getPitchID() == temp.getPitchID()){

        music.remove(i);

        if (m.getPitchID() == this.highestPitch) {
          this.findHighestPitch();
        }
        if (m.getPitchID() == this.lowestPitch) {
          this.findLowestPitch();
        }
        if (m.end == this.lastBeat) {
          findLastBeat();
        }

      }


    }

  }

  @Override
  public void sortMusic() {
    //iterates through arrayList of beats
    for (int x = 0; x < music.size() - 1; x++) {
      Note a = (Note) music.get(x);
      for (int y = 1; y < music.size(); y++) {
        Note b = (Note) music.get(y);
        if (a.compareTo(b) == 1) {
          Note temp = a;
          a = b;
          b = temp;
        }
      }
    }
  }

  @Override
  public void run(int duration) {
    this.currBeat = (this.tempo / 60) * duration;
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

  public int getLowestPitch(){
    return this.lowestPitch;
  }

  public int getHighestPitch(){
    return this.highestPitch;
  }

  public int getLastBeat(){
    findLastBeat();
    return this.lastBeat;
  }

  public ArrayList<ourPlayable> getMusic(){
    return this.music;
  }

  public int getStartingBeat(ArrayList<ourPlayable> list, int j){
    ourPlayable n = list.get(j);
    Note m = (Note) n;
    return m.start;
  }

  public int getFinishingBeat(ArrayList<ourPlayable> list, int j){
    ourPlayable n = list.get(j);
    Note m = (Note) n;
    return m.end;
  }

  public int getPitchValue(ArrayList<ourPlayable> list, int j){
    ourPlayable n = list.get(j);
    Note m = (Note) n;
    return m.pitch.pitchID;
  }

  public boolean isStart(ArrayList<ourPlayable> list, int j, int beat){
    ourPlayable n = list.get(j);
    Note m = (Note) n;
    if (m.start == beat){
      return true;
    } else {
      return false;
    }
  }

  public boolean isHeld(ArrayList<ourPlayable> list, int j, int beat){
    ourPlayable n = list.get(j);
    Note m = (Note) n;
    if (beat > m.start && beat <= m.end){
      return true;
    } else {
      return false;
    }
  }

  public void addBeat(){
    this.lastBeat = this.lastBeat + 1;
  }

  public void resetBeat(){
    this.currBeat = 0;
  }

  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }


  public int getTemp(){
    return this.tempo;
  }

  @Override
  public ArrayList<ourPlayable> isPlayingAt(int beat) {
    ArrayList<ourPlayable> notesAt = new ArrayList<>();
    int start, end;
    for (int x = 0; x < music.size(); x++) {

      start = music.get(x).getStart();
      end = music.get(x).getEnd();

      if(beat >= start && beat <= end) {
        notesAt.add(music.get(x));
      }
    }
    return notesAt;
  }

  @Override
  public ArrayList<ourPlayable> isSustainedAt(int beat) {
    ArrayList<ourPlayable> notesAt = new ArrayList<>();
    int start, end;
    for (int x = 0; x < music.size(); x++) {

      start = music.get(x).getStart();
      end = music.get(x).getEnd();

      if(beat != start && beat <= end) {
        notesAt.add(music.get(x));
      }
    }
    return notesAt;
  }

  public void setCurrBeat(int beat){
    this.currBeat = beat;
  }

  public int getEndMeasure(){
    return this.endMeasure;
  }

  public int getStartMeasure(){
    return this.startMeasure;
  }

  public void setEndMeasure(int m){
    this.endMeasure = m;
  }

  public void setStartMeasure(int m){
    this.startMeasure = m;
  }

}