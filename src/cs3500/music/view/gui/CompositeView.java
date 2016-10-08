package cs3500.music.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.util.TimerTask;
import java.util.Timer;

import cs3500.music.model.EditableCompositionModel;
import cs3500.music.model.Playable;
import cs3500.music.util.Tuple;
import cs3500.music.view.midi.MidiView;
import cs3500.music.view.midi.MidiViewImpl;

import javax.swing.*;

/**
 * A class that contains both a GuiVew and a MidiView and uses a Timer to sychronize them in its
 * render method; also includes a red position line to mark the currently playing beat in the GUI;
 * ;hands off all its GuiView interface functionality to its GuiView
 */
public class CompositeView implements GuiView {
  private final MidiViewImpl midiView;
  private final GuiViewFrame guiView;
  EditableCompositionModel mod;
  private boolean hasRepeated = false;
  private int repeatEndMeasure;
  private int repeatStartMeasure;

  public CompositeView() {
    this.midiView = new MidiViewImpl();
    this.guiView = new GuiViewFrame();
  }

  @Override
  public Playable addNote(Tuple<Integer, Integer> mousePosn) {
    return this.guiView.addNote(mousePosn);
  }

  @Override
  public void addNote(Playable note) {

  }

  @Override
  public Playable deleteNote(Tuple<Integer, Integer> mousePosn) {
    return this.guiView.deleteNote(mousePosn);
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
  public void addKeyListener(KeyListener keyListener) {
    this.guiView.addKeyListener(keyListener);
  }

  @Override
  public void addMouseListener(MouseListener mouseListener) {
    this.guiView.addMouseListener(mouseListener);
  }

  @Override
  public Tuple<Integer, Integer> screenToGridCoords(Tuple<Integer, Integer> screenCoords) {
    return this.guiView.screenToGridCoords(screenCoords);
  }

  @Override
  public boolean isNoteCell(Tuple<Integer, Integer> screenCoords) {
    return this.guiView.isNoteCell(screenCoords);
  }

  @Override
  public void render(EditableCompositionModel model) {
    this.mod = model;
    this.guiView.render(model);
  }

  public void updateStartAndEnd(){
    int repeatEndMeasure = mod.getEndMeasure();
    int repeatStartMeasure = mod.getStartMeasure();
    guiView.repaintBegin(repeatEndMeasure * 4, repeatStartMeasure, repeatEndMeasure);
    guiView.repaintBegin(repeatStartMeasure * 4, repeatStartMeasure, repeatEndMeasure);
    guiView.repaintBegin(0, repeatStartMeasure, repeatEndMeasure);
  }

  public void play(){
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      int repeatEndMeasure = mod.getEndMeasure();
      int repeatStartMeasure = mod.getStartMeasure();
      int curr = mod.getCurrentBeat();

      //run() cycles through beat #'s
      //MIDI plays all notes at given beat
      //GUI updates to show red cursor at beat
      @Override
      public void run() {
        midiView.renderBeat(mod, curr);
        guiView.repaint(curr);
        curr = curr + 1;

        //if current beat is a repeat sign that hasn't been
        //executed yet, go back to beginning of repeat
        if (curr == (repeatEndMeasure * 4) && !hasRepeated){
          //set beat to start of repeat (4 beats per measure)
          mod.setCurrentBeat(repeatStartMeasure * 4);
          curr = repeatStartMeasure * 4;
          //repaint takes in repeat measures to update GUI
          guiView.repaintBegin(repeatStartMeasure * 4, repeatStartMeasure, repeatEndMeasure);
          hasRepeated = true;
        }

        if(curr == mod.length() && hasRepeated) {
          //if the song has ended, reset but keep the repeat signs
          timer.cancel();
          mod.setCurrentBeat(0);
          curr = 0;
          guiView.repaintBegin(0, repeatStartMeasure, repeatEndMeasure);
          hasRepeated = false;
        }
      }
    }, mod.getCurrentBeat(), mod.getTempo() / 1000);

  }

  public void ren(){
    render(mod);
  }
}