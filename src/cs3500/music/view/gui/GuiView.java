package cs3500.music.view.gui;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import cs3500.music.model.Playable;
import cs3500.music.util.Tuple;
import cs3500.music.view.EditableCompositionView;


public interface GuiView extends EditableCompositionView {
  /**
   * Adds the note pointed to by the mousePosn to this view, returns it so it can be added to the
   * overall composition
   *
   * @param mousePosn the position of the mouse, in screen coordinates
   * @return the note to be added to the composition
   */
  public Playable addNote(Tuple<Integer, Integer> mousePosn);

  /**
   * Add the given note to this view
   * @param note the note to be added to the view
   */
  public void addNote(Playable note);

  /**
   * Deletes the note pointed to by the composition to this view, including its duration if it is a
   * start note, or shortens the duration if it is a sustained note
   *
   * @param mousePosn the position of the mouse, in screen coordinates
   * @return the note to be added to the composition
   */
  public Playable deleteNote(Tuple<Integer, Integer> mousePosn);

  /**
   * Moves the note
   * @param note the note you want to mvoe
   */
  public void moveNote(Playable note);

  /**
   * Changes the duration of the note
   * @param note the note you want to change
   * @param duration the new duration
   */
  public void changeDuration(Playable note, int duration);

  /**
   * Sets the tempo of the composition
   *
   * @param tempo the tempo, in microseconds per beat
   */
  public void setTempo(int tempo);

  /**
   * Adds a key listener to this GuiView
   *
   * @param keyListener the keyListener you want to add
   */
  public void addKeyListener(KeyListener keyListener);

  /**
   * Adds a mouse listener to this GuiView
   *
   * @param mouseListener the mouseListener you want to add
   */
  public void addMouseListener(MouseListener mouseListener);

  /**
   * Converts screen coordinates (such as those produced by a mouse click) to grid coordinates;
   * throws error if the given screen coordinates do not correspond to any grid coordinates
   * @param screenCoords the screen coordinates to be converted to grid coordinates
   * @return the newly converted grid coordinates
   * @throws IllegalArgumentException if the given screen coordinates do not correspond to any
   * grid coordinates
   */
  public Tuple<Integer, Integer> screenToGridCoords(Tuple<Integer, Integer> screenCoords);

  /**
   * Do the given screen coordinates correspond to a note cell?
   *
   * @param screenCoords the screen coordinates to be tested
   * @return true if {@code screenCoords} corresponds to a note cell
   */
  public boolean isNoteCell(Tuple<Integer, Integer> screenCoords);
}