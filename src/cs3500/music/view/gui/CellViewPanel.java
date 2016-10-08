package cs3500.music.view.gui;

import cs3500.music.model.Playable;

import java.awt.*;

import static cs3500.music.view.gui.Constants.*;

/**
 * A JPanel that contains a note, and represents it graphically via its background color; can be
 * updated with different notes and will refresh and repaint to respond to that
 */
public class CellViewPanel extends GridCellPanel {
  enum State {StartNote, SustainedNote, Empty};

  private final int beat;
  private Playable note;
  private State state;
  private final int noteNumerical;

  CellViewPanel(int beat, int noteNumerical) {
    this.beat = beat;
    this.noteNumerical = noteNumerical;
    this.note = null;
    this.state = State.Empty;
    this.refresh();
  }

  CellViewPanel(int beat, Playable note, int noteNumerical) {
    this.beat = beat;
    this.noteNumerical = noteNumerical;
    this.note = note;
    this.refresh();
  }

  /**
   * Refreshes this note's state and background color based on its note
   */
  private void refresh() {
    Color color;
    if (this.note == null) {
      this.state = State.Empty;
      color = CELL_EMPTY_COLOR;
    } else if (this.note.startsAt(this.beat)) {
      this.state = State.StartNote;
      color =  CELL_STARTING_COLOR;
    } else if (this.note.sustainedAt(this.beat)) {
      this.state = State.SustainedNote;
      color =  CELL_SUSTAINED_COLOR;
    } else {
      color =  CELL_EMPTY_COLOR;
    }
    this.setBackground(color);
    this.repaint();
  }

  /**
   * Returns this cell's state
   * @return this cell's state
   */
  public State getState() {
    return this.state;
  }

  /**
   * Returns this cell's note's duration
   * @return this cell's note's duration
   */
  public int getNoteDuration() {
    return this.note.getDuration();
  }

  /**
   * returns this cell's note's startBeat
   * @return this cell's note's startBeat
   */
  public int getNoteStart() {
    return this.note.getStartBeat();
  }

  /**
   * Returns this cell's note's pumerical pitch
   * @return this cell's note's numerical pitch
   */
  public int getNoteNumerical() {
    return this.noteNumerical;
  }

  /**
   * Returns this cell's note
   * @return
   */
  public Playable getNote() {
    return this.note;
  }

  /**
   * Sets this cell's note to the given note and refreshes it
   * @param note the note you want to set as this cell's note
   */
  public void setNote(Playable note) {
    this.note = note;
    this.refresh();
  }

  /**
   * Reset's this cell's note to null and refreshes it
   */
  public void clearNote() {
    this.note = null;
    this.refresh();
  }
}