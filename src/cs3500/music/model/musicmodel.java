package cs3500.music.model;

import java.util.ArrayList;

public interface musicmodel {

  /**
   * @return the number of beats in the song
   */
  int getSongLength();

  /**
   * Finds the last beat and alters lastBeat field
   */
  void findLastBeat();

  /**
   * Finds the lowest pitch in the music Sets lowestPitch field
   */
  void findLowestPitch();

  /**
   * Finds the highest pitch in the music Sets the highestPitch field
   */
  void findHighestPitch();

  /**
   * @return true if the song is over
   */
  boolean songOver();

  /**
   * @return the current beat
   */
  int currBeat();


  /**
   * @return the all of the notes
   */
  ArrayList<ourPlayable> getNotes();

  /**
   * gives an arraylist of notes that start at the given beat
   *
   * @return the notes at the given beat
   */
  ArrayList<ourPlayable> getNotesAt(int beat);

  /**
   * Adds the given note Checks each note if it is a new highestPitch, lowestPitch, or lastBeat and
   * recalculates
   */
  void addNote(ourPlayable n);

  /**
   * Adds the given ArrayList of notes Checks each note if it is a new highestPitch, lowestPitch,
   * or
   * lastBeat and recalculates
   *
   * @param n ArrayList<Note></Note>
   */
  void addNotes(ArrayList<ourPlayable> n);

  /**
   * Deletes the given note Checks if it is responsible for highestPitch, lowestPitch, or lastBeat
   * and recalculates
   */
  void deleteNote(ourPlayable n);

  /**
   * prints the music to the console output
   */
 // void printMusic();

  /**
   * Sorts the arraylist to earliest starting nodes in the front
   */
  void sortMusic();

  /**
   * Assuming tempo is given in beats per minute runs for the given duration of time in seconds
   * mutates the currBeat field
   */
  void run(int duration);

  /**
   * gives the tempo
   */
  int getTempo();

  /**
   *
   * @return the lowest pitchID in the composition
   */
  int getLowestPitch();
  /**
   *
   * @return the highest pitchiD in the compositon
   */
  int getHighestPitch();
  /**
   *
   * @return the first played note
   */
  int getStartingBeat(ArrayList<ourPlayable> list, int j);
  /**
   *
   * @return the last beat ending
   */
  int getFinishingBeat(ArrayList<ourPlayable> list, int j);
  /**
   * @param j
   * @param list
   * @return the the pitch value of the given note in the list
   */
  int getPitchValue(ArrayList<ourPlayable> list, int j);
  /**
   *
   * @return the lastBeat of the composition
   */
  int getLastBeat();
  /**
   *
   * @return if it is the start
   */
  boolean isStart(ArrayList<ourPlayable> list, int j, int beat);
  /**
   *
   * @return if the beat is held by the note in the list
   */
  boolean isHeld(ArrayList<ourPlayable> list, int j, int beat);

  void addBeat();

  void resetBeat();

  void setTempo(int tempo);

  public ArrayList<ourPlayable> isPlayingAt(int beat);

  public ArrayList<ourPlayable> isSustainedAt(int beat);
}