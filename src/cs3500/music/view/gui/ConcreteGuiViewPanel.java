package cs3500.music.view.gui;

import cs3500.music.model.EditableCompositionModel;
import cs3500.music.model.Note;
import cs3500.music.model.Playable;
import cs3500.music.util.Tuple;
import javafx.scene.control.Cell;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.*;

/**
 *  The JPanel that constructs and contains a grid which contains all the note cells (themselves
 *  {@link CellViewPanel}s) in a given EditableCompositionModel
 */
public class ConcreteGuiViewPanel extends JPanel {
  private final List<List<CellViewPanel>> cells;
  private final GridViewPanel grid;
  private final JScrollPane scroller;

  public ConcreteGuiViewPanel() {
    this.grid = new GridViewPanel();
    this.grid.setLayout(new BoxLayout(grid, BoxLayout.X_AXIS));
    this.scroller = new JScrollPane(grid);
    this.cells = new ArrayList<List<CellViewPanel>>();

    this.add(this.scroller);
  }

  /**
   * Returns the preferred size for the scroller, either the size of the composition or a smaller
   * size that will fit on the screen
   * @return the preferred size for the scroller
   */
  protected Dimension scrollerSize() {
    int x;
    int y;
    int xlen = this.cells.size();
    if (xlen > 0) {
      int ylen = this.cells.get(0).size();
      y = Math.min(Constants.VIEWABLE_ROWS, ylen);
    } else {
      y = 0;
    }
    x = Math.min(Constants.VIEWABLE_COLUMNS, xlen);
    return new Dimension((x + 1) * Constants.GRID_CELL_SIZE + 12,
            (y + 1) * Constants.GRID_CELL_SIZE + 12);
  }

  /**
   * Creates a vertical row header with all the pitches from the given {@code highest} note to the
   * given {@code lowest} note, in the given {@code font}.
   *
   * @param highest the numerical representation of the highest note to be displayed
   * @param lowest  the numerical representation of the lowest note to be displayed
   * @param font    the font in which these notes are to be displayed
   * @return the vertical row header containing all the pitches from {@code highest} to
   * {@code lowest}
   */
  private JPanel verticalPitchHeader(int highest, int lowest, Font font) {
    JPanel notesHeader = new JPanel();
    notesHeader.setLayout(new BoxLayout(notesHeader, BoxLayout.Y_AXIS));
    for (int i = highest; i >= lowest; i--) {
      JPanel p = new GridCellPanel();
      JLabel l = new JLabel(Playable.Util.NumbertoNoteString(i));
      l.setFont(font);
      p.add(l);
      notesHeader.add(p);
    }
    return notesHeader;
  }

  /**
   * Creates a single {@code GridCellPanel} which should be used to label a column with the given
   * beatNum in the given font; should be added to the horizontal header of the grid of notes
   *
   * @param beatNum the number this JPanel is to be labelled with
   * @param font    the font this JPanel is to be labelled in
   * @return a single {@code GridCellPanel} which should be used to label a column with the given
   * beatNum in the given font
   */
  private JPanel beatHeaderCellPanel(int beatNum, Font font) {
    JPanel p = new GridCellPanel();
    JLabel l = new JLabel(Integer.toString(beatNum));
    l.setFont(font);
    p.add(l);
    return p;
  }

  protected void initCells(EditableCompositionModel model) {
    Font font = new Font("Helvetica", Font.PLAIN, 8);
    this.grid.setCurr(model.getCurrentBeat());
    int lowest = model.lowestNoteNumerical();
    int highest = model.highestNoteNumerical();
    JPanel horizontalBeatHeader = new JPanel();
    horizontalBeatHeader.setLayout(new BoxLayout(horizontalBeatHeader, BoxLayout.X_AXIS));

    // build the grid of note cells and the list of note cells
    for (int beatNum = 0; beatNum < model.length(); beatNum++) {
      List<Playable> beat = model.playingNotes(beatNum);
      List<CellViewPanel> column = new ArrayList<CellViewPanel>();
      beat.sort(new Playable.HighToLow());
      int curr = highest;
      CellViewPanel c;

      // create the beat column
      JPanel col = new JPanel();
      col.setLayout(new BoxLayout(col, BoxLayout.Y_AXIS));

      // build the beat column
      for (Playable note : beat) {
        int noteNum = note.numericalPitch();
        while (curr > noteNum) { // no note is played here, add empty space
          c = new CellViewPanel(beatNum, curr);
          column.add(c);
          col.add(c);
          curr -= 1;
        }
        if (curr == noteNum) { // this note is played here, add appropriate marker
          c = new CellViewPanel(beatNum, note, curr);
          column.add(c);
          col.add(c);
          curr -= 1;
        } // if curr < noteNum, it's a duplicate and therefore can't be rendered in this view
      }

      // add JPanels/cells to fill rest of the empty space in the beat column
      while (column.size() <= (highest - lowest)) {
        c = new CellViewPanel(beatNum, curr);
        column.add(c);
        col.add(c);
        curr -= 1;
      }
      horizontalBeatHeader.add(this.beatHeaderCellPanel(beatNum, font));
      this.cells.add(column);
      grid.add(col, beatNum);
    }
    // set up the size and headers for the scroller
    scroller.setPreferredSize(this.scrollerSize());

    JViewport rowHeaderViewport = new JViewport();
    rowHeaderViewport.setView(this.verticalPitchHeader(highest, lowest, font));

    JViewport columnHeaderViewport = new JViewport();
    columnHeaderViewport.setView(horizontalBeatHeader);

    scroller.setRowHeader(rowHeaderViewport);
    scroller.setColumnHeader(columnHeaderViewport);

    this.setPreferredSize(this.scrollerSize());
  }

  /**
   * Is the given position pointing to a note cell?
   * @param screenCoords the mouse position, in screen coordinates
   * @return true if the given screen coordinates are on a note cell
   */
  public boolean isNoteCell(Tuple<Integer, Integer> screenCoords) {
    return this.grid.isNoteCell(screenCoords);
  }

  /**
   * Converts a mouse position, in grid coordinates, to the cell it is pointing at
   * @param mousePosn the mouse position, in grid coordinates
   * @return the cell the mouse is pointing at
   */
  private CellViewPanel posnToCell(Tuple<Integer, Integer> mousePosn) {
    return this.cells.get(mousePosn.x).get(mousePosn.y);
  }

  /**
   * Returns the numerical pitch of the highest note in this view
   * @return the numerical pitch of the highest note in this view
   */
  private int highestPitch() {
    if (this.cells.size() > 0) {
      if(this.cells.get(0).size() > 0) {
        return this.cells.get(0).get(0).getNoteNumerical();
      }
    }
    return 0;
  }

  /**
   * Adds the note to the mouse is pointing at to this view, if there is not already a note there
   * @param mousePosn the position of the mouse, in grid coordinates
   * @return the note that was added, so it can be added to the model
   */
  public Playable addNote(Tuple<Integer, Integer> mousePosn) {
    CellViewPanel cell = this.posnToCell(mousePosn);
    Playable note = new Note(this.highestPitch() - mousePosn.y, mousePosn.x, 1);
    if (cell.getState() == CellViewPanel.State.Empty) {
      cell.setNote(note);
    }
    return note;
  }

  /**
   * Deletes the note the mouse is pointing to from this view, along with its duration, if it is a
   * startNote, or the rest of the duration of the note, if it is a sustainedNote
   * @param mousePosn the position of the mouse, in grid coordinates
   * @return the note that was deleted, so it can in turn be deleted from the model
   */
  public Playable deleteNote(Tuple<Integer, Integer> mousePosn) {
    CellViewPanel cell = this.posnToCell(mousePosn);
    Playable note = cell.getNote();
    if (cell.getState() == CellViewPanel.State.StartNote ||
            cell.getState() == CellViewPanel.State.SustainedNote) {
      int dur = cell.getNoteDuration();
      int pos = mousePosn.x - cell.getNoteStart();
      for (int i = 0; i < (dur - pos); i++) {
        this.cells.get(mousePosn.x + i).get(mousePosn.y).clearNote();
      }
    }
    return note;
  }

  @Override
  public void addMouseListener(MouseListener mouseListener) {
    this.grid.addMouseListener(mouseListener);
  }

  /**
   * Converts a pair of screen coordinates to a pair of grid coordinates
   * @param screenCoords the screen coordinates to be converted
   * @return the new grid coordinates
   */
  public Tuple<Integer, Integer> screenToGridCoords(Tuple<Integer, Integer> screenCoords) {
    return this.grid.screenToGridCoords(screenCoords);
  }

  /**
   * Repaints the given column of this panel's grid
   * @param column the int column you want to repaint
   */
  public void repaint(int column) {
    this.grid.repaint((Constants.GRID_CELL_SIZE * column) - 1, 0, Constants.GRID_CELL_SIZE * 2,
            grid.getHeight());
    this.grid.updateCurr();
  }

  public void repaintBegin(int beatNum, int mStart, int mEnd){
    this.grid.repaint((Constants.GRID_CELL_SIZE * (beatNum)) - 1, 0, Constants.GRID_CELL_SIZE * 2,
            grid.getHeight());
    this.grid.setCurr(beatNum);
    this.grid.setStartMeasure(mStart);
    this.grid.setEndMeasure(mEnd);
  }


}