package cs3500.music.view.gui;

import cs3500.music.model.EditableCompositionModel;
import cs3500.music.model.Playable;
import cs3500.music.util.Tuple;
import cs3500.music.view.EditableCompositionView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends javax.swing.JFrame implements GuiView {
  private final ConcreteGuiViewPanel displayPanel;
  // You may want to refine this to a subtype of JPanel

  /**
   * Creates new GuiViewPanel
   */
  public GuiViewFrame() {
    this.displayPanel = new ConcreteGuiViewPanel();
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().add(displayPanel);
    this.pack();
  }

  public void initialize(){
    this.setVisible(true);
  }

  @Override
  public void render(EditableCompositionModel model) {
    this.displayPanel.initCells(model);
    this.setBounds(0, 0, (int) displayPanel.scrollerSize().getWidth(),
            (int) displayPanel.scrollerSize().getHeight());
//    this.setPreferredSize(this.displayPanel.scrollerSize());
    this.initialize();
  }

  @Override
  public Playable addNote(Tuple<Integer, Integer> mousePosn) {
    return this.displayPanel.addNote(this.screenToGridCoords(mousePosn));
  }

  @Override
  public void addNote(Playable note) {

  }

  @Override
  public Playable deleteNote(Tuple<Integer, Integer> mousePosn) {
    return this.displayPanel.deleteNote(this.screenToGridCoords(mousePosn));
  }

  @Override
  public void moveNote(Playable note) {

  }

  @Override
  public void changeDuration(Playable note, int duration) {

  }

  @Override
  public void setTempo(int tempo) {

  }

  @Override
  public Tuple<Integer, Integer> screenToGridCoords(Tuple<Integer, Integer> screenCoords) {
    return this.displayPanel.screenToGridCoords(screenCoords);
  }

  @Override
  public boolean isNoteCell(Tuple<Integer, Integer> screenCoords) {
    return this.displayPanel.isNoteCell(screenCoords);
  }

  @Override
  public void addMouseListener(MouseListener mouseListener) {
    this.displayPanel.addMouseListener(mouseListener);
  }

  public void repaint(int column) {
    this.displayPanel.repaint(column);
  }

  public void repaintBegin(int beatNum, int mStart, int mEnd) { this.displayPanel.repaintBegin(beatNum, mStart, mEnd);}

}