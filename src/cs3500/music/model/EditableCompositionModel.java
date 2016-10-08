package cs3500.music.model;

import java.util.List;

public interface EditableCompositionModel {
  /**
   * Adds the given note to this composition.
   *
   * @param note the note to be added to the composition
   */
  public void addNote(Playable note);

  /**
   * Adds all the notes in the given list to this composition.
   *
   * @param notes the list of all the notes to be added to the composition
   */
  public void addAllNotes(List<Playable> notes);

  /**
   * Adds all the notes in a given list after the last beat in this composition.
   *
   * @param notes the list of all the notes to be added to the composition
   */
  public void addAllAfter(List<Playable> notes);

  /**
   * Returns a list of copies of all the notes in this composition.
   *
   * @return a list of copies of all the notes in this composition.
   */
  public List<Playable> allNotes();

  /**
   * Deletes the given note from this composition.
   *
   * @param note the note to be deleted from the composition
   */
  public void deleteNote(Playable note);

  /**
   * Changes the duration of a given note.
   *
   * @param note the note whose duration is to be changed
   * @param duration the new number of beats the note should now last
   */
  public void changeDuration(Playable note, int duration);

  /**
   * Changes the beat at which the given note is played.
   *
   * @param note the note to be moved
   * @param newStartBeat the new beat at which the note will now start
   */
  public void moveNote(Playable note, int newStartBeat);

  /**
   * Returns the numerical representation of the highest note in this composition.
   *
   * @return the numerical representation of the highest note in this composition
   */
  public int highestNoteNumerical();

  /**
   * Returns the numerical representation of the lowest note in this composition.
   *
   * @return the numerical representation of the lowest note in this composition
   */
  public int lowestNoteNumerical();

  /**
   * Returns the current number of beats in this composition.
   *
   * @return the current number of beats in this composition
   */
  public int length();

  /**
   * Returns a list of copies of all the notes that start at the given beat.
   *
   * @param startBeat the beat at which the returned notes start
   * @return a list of all the notes that start at the given beat
   */
  public List<Playable> startNotes(int startBeat);

  /**
   * Returns a list of copies of all the notes that are sustained at the given beat.
   *
   * @param sustainedBeat the beat at which the returned notes are sustained
   * @return a list of all the notes that are sustained at the given beat
   */
  public List<Playable> sustainedNotes(int sustainedBeat);

  /**
   * Returns a list of copies of all the notes playing at the given beat.
   *
   * @param playingBeat the beat at which all the returned notes are played
   * @return a list of all the notes that are played at the given beat
   */
  public List<Playable> playingNotes(int playingBeat);

  /**
   * Combines this EditableCompositionModel with another EditableCompositionModel, so
   * they are played simulataneously.
   *
   * @param other the EditableCompositionModel to be played simultaneously with this one   *
   */
  public void combineWith(EditableCompositionModel other);

  /**
   * Adds the given EditableCompositionModel after this EditableCompositionModel, so
   * they are played consecutively.
   *
   * @param other the EditableCompositionMode to be played after this one
   */
  public void addCompositionAfter(EditableCompositionModel other);

  /**
   * Gets the tempo of the composition (in microseconds per beat)
   */
  public int getTempo();

  /**
   * Sets the tempo of the composition
   *
   * @param tempo the tempo, in microseconds per beat
   */
  public void setTempo(int tempo);

  /**
   * Gets the currently playing beat of the composition
   *
   * @return the currently playing beat of the composition
   */
  public int getCurrentBeat();

  public void setCurrentBeat(int beat);

  /**
   * Adds one to the current beat of the composition
   */
  public void updateCurrentBeat();

  public void reset();

  public int getStartMeasure();

  public int getEndMeasure();

  public void setStartMeasure(int m);

  public void setEndMeasure(int m);
}