package cs3500.music.view.gui;

import cs3500.music.util.Tuple;

import javax.swing.*;
import java.awt.*;

/**
 *  A JPanel that will hold as grid consisting of more JPanels that represent notes in a
 *  composition
 */
public class GridViewPanel extends JPanel implements Scrollable {
  private int curr;
  private int endMeasure;
  private int startMeasure;

  public GridViewPanel() {
    this.curr = 0;
  }

  public void setCurr(int curr) {
    this.curr = curr;
  }

  public void updateCurr() {
    this.curr++;
  }

  public void setEndMeasure(int m){
    this.endMeasure = m;
  }

  public void setStartMeasure(int m){
    this.startMeasure = m;
  }

  public int getEndMeasure(){
    return this.endMeasure;
  }

  public int getStartMeasure(){
    return this.startMeasure;
  }

  public void resetCurr() {
    this.curr = 0;
  }

  @Override
  public Dimension getPreferredScrollableViewportSize() {
    return null;
  }

  @Override
  public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
    return Constants.GRID_CELL_SIZE;
  }

  @Override
  public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
    return Constants.GRID_CELL_SIZE;
  }

  @Override
  public boolean getScrollableTracksViewportWidth() {
    return false;
  }

  @Override
  public boolean getScrollableTracksViewportHeight() {
    return false;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    for (int i = 0; i < this.getWidth(); i += (Constants.GRID_CELL_SIZE * 4)) {
      g.setColor(Color.black);
      g.drawLine(i, 0, i, this.getHeight());
    }
    for (int i = 0; i < this.getHeight(); i += Constants.GRID_CELL_SIZE) {
      g.setColor(Color.black);
      g.drawLine(0, i, this.getWidth(), i);
    }
    g.setColor(Color.red);
    g.drawLine(curr * Constants.GRID_CELL_SIZE, 0, curr * Constants.GRID_CELL_SIZE,
            this.getHeight());

    //NOW draws blue lines for repeat signs
    g.setColor(Color.BLUE);
    g.drawLine(endMeasure * 4 * Constants.GRID_CELL_SIZE, 0, endMeasure * 4 * Constants.GRID_CELL_SIZE,
            this.getHeight());
    g.drawLine(startMeasure * 4 * Constants.GRID_CELL_SIZE, 0, startMeasure * 4 * Constants.GRID_CELL_SIZE,
            this.getHeight());

  }

  /**
   * Convert the given screen coordinates to coordinates on the grid of notes
   * @param screenCoords the screen coordinates to be converted
   * @return the new grid coordinates that represent a note
   */
  public Tuple<Integer, Integer> screenToGridCoords(Tuple<Integer, Integer> screenCoords) {
    return new Tuple<Integer, Integer>((screenCoords.x / Constants.GRID_CELL_SIZE),
            (screenCoords.y / Constants.GRID_CELL_SIZE));
  }

  /**
   * Returns true if the coordinates the mouse is pointing at is a note cell (in this particular
   * case, always true)
   * @param screenCoords the coordinates to be tested
   * @return true since this entire thing is made up of note cells
   */
  public boolean isNoteCell(Tuple<Integer, Integer> screenCoords) {
    return true;
  }
}