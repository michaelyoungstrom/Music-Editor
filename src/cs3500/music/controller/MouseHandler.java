package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import cs3500.music.model.musicmodel;
import cs3500.music.view.gui.CompositeView;
import cs3500.music.util.Tuple;
import cs3500.music.view.gui.GuiView;
import cs3500.music.model.*;

/**
 * Created by Tejas on 11/21/2015.
 */

public class MouseHandler implements MouseListener {

  private CompositeView g;
  private musicmodel m;
  private compositionAdaptor a;

  public MouseHandler(CompositeView g, musicmodel m, compositionAdaptor a) {
    this.m = m;
    this.g = g;
    this.a = a;
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    //check if click was over a note
    int x=e.getX();
    int y=e.getY();
    Tuple<Integer, Integer> coords = new Tuple(x,y);
    boolean noteChecker = g.isNoteCell(coords);

    if (e.getButton() == MouseEvent.BUTTON1){
      if (noteChecker) {
        cs3500.music.model.Playable not = g.addNote(coords);

        try {
          a.addNote(not);
        } catch (NullPointerException exc) {
          System.out.print("Error with new note");
        }
      }
    } else {
      if (noteChecker){
        try {
          cs3500.music.model.Playable not = g.deleteNote(coords);
          a.deleteNote(not);
        } catch (NullPointerException ex){
          System.out.print("Not a note!");
        }
      }
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseReleased(MouseEvent e) {
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }


}
