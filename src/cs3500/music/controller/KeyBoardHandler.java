package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Map;
import cs3500.music.model.musicmodel;
import cs3500.music.model.ourPlayable;
import cs3500.music.view.gui.CompositeView;
import cs3500.music.model.EditableCompositionModel;


public class KeyBoardHandler implements KeyListener {
  Map<String, Runnable> pressed;
  Map<String, Runnable> typed;
  Map<String, Runnable> released;

  private musicmodel m;
  private CompositeView g;
  private EditableCompositionModel ed;
  boolean isEHeld = false;
  boolean isSHeld = false;

  public KeyBoardHandler(CompositeView g, musicmodel m, EditableCompositionModel ed) {
    this.m = m;
    this.g = g;
    this.ed = ed;
  }

  public void addTyped() {
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {

    char code = e.getKeyChar();

    //KEYBOARD INSTRUCTIONS:
    //SPACE - START PLAYING
    //HOLD E (+ RIGHT ARROW) - ADVANCE END MEASURE
    //HOLD S (+ RIGHT ARROW) - ADVANCE START MEASURE
    
    if (code == ' ') {
      new playToggle();
    } else if (code == 'e'){
      isEHeld = true;
      ed.setEndMeasure(0);
      g.updateStartAndEnd();
    } else if (code == 's'){
      isSHeld = true;
      if (ed.getEndMeasure() > 1) {
        ed.setStartMeasure(0);
        g.updateStartAndEnd();
      }
    }

    if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
      if (isEHeld){

        int m = ed.getEndMeasure();
        if (m < (ed.length()/4)) {
          ed.setEndMeasure(m + 1);
        } else {
          ed.setEndMeasure(1);
        }
        g.updateStartAndEnd();

      } else if (isSHeld) {

        int m = ed.getStartMeasure();
        if (m < ed.getEndMeasure()) {
          ed.setStartMeasure(m + 1);
        } else {
          ed.setStartMeasure(0);
        }
        g.updateStartAndEnd();


      }
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
      //new scrollLeft();
    }

  }

  @Override
  public void keyReleased(KeyEvent e) {

    char code = e.getKeyChar();

    if (code == 'e'){
      isEHeld = false;
    } else if (code == 's'){
      isSHeld = false;
    }


  }




  class playToggle implements Runnable {

    playToggle() {
      this.run();
    }

    public void run() {
      g.play();
    }
  }

}

