package cs3500.music.model;

/**
 * Created by Tejas on 12/7/2015.
 */
public class ourPlayableToPlayableAdapter extends ourPlayable
        implements Playable  {
  ourPlayable ours;

  public ourPlayableToPlayableAdapter(ourPlayable o) {

    this.ours = o;
  }

  public ourPlayableToPlayableAdapter() {
  }

  @Override
  public cs3500.music.model.Pitch getPitch() {

    return ours.getTheirPitch();
  }

  @Override
  public int getOctave() {
    return ours.getOurOctave();
  }

  @Override
  public int numericalPitch() {
    return ours.getPitchID();
  }

  @Override
  public int getStartBeat() {
    return ours.getStart();
  }

  @Override
  public void setStartBeat(int newStartBeat) {

    ours.setStart(newStartBeat);
  }

  @Override
  public int getDuration() {

    try {
      ours.getDur();
    } catch(NullPointerException e){
      return 1;
    }

    return ours.getDur(); }

  @Override
  public void setDuration(int newDuration) {

    ours.setDuration(newDuration);
  }

  @Override
  public void setInstrument(int instrument) {

    ours.setInstrument(instrument);
  }

  @Override
  public int getInstrument(){
    return ours.getInstrument();
  }

  @Override
  public void setVolume(int volume) {
    ours.setVolume(volume);
  }

  @Override
  public int getVolume(){
    return ours.getVolume();
  }

  @Override
  public String toString(){
    return "hi";
  }

  @Override
  public boolean startsAt(int startBeat) {

    try {
      this.getStartBeat();
    } catch(NullPointerException e){
      return true;
    }

    if (startBeat == this.getStartBeat()){
      return true;
    } else {
      return false;
    }

  }

  @Override
  public boolean sustainedAt(int sustainedBeat) {
    return sustainedBeat > this.getStartBeat() &&
            sustainedBeat <= (this.getDuration() + this.getStartBeat() - 1);
  }

  @Override
  public Note copyOf() {
    return new Note(this.getStart(), this.getEnd(),
            this.getPitchID(), this.getInstrument(),
      this.getVolume());
  }
}
