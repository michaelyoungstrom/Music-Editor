/*
package cs3500.music.tests;

import cs3500.music.model.*;
import cs3500.music.view.gui.*;
import cs3500.music.view.midi.*;

import org.junit.Test;

import javax.sound.midi.InvalidMidiDataException;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CompositeViewTest {

  ArrayList<Playable> makeMT() {
    ArrayList<Playable> mt = new ArrayList<Playable>();
    return mt;
  }

  @Test
  public void testComposite() throws InterruptedException, InvalidMidiDataException {

    Playable c1 = new Note(0, 2, 1, 1, 1);
    Playable b1 = new Note(2, 4, 12, 1, 1);
    Playable c2 = new Note(2, 3, 13, 1, 1);
    Playable d2 = new Note(0, 4, 15, 1, 1);
    MusicModelImpl ex1 = new MusicModelImpl.Builder()
      .music(makeMT()).currBeat(0).lastBeat().lowestPitch().highestPitch().build();

    MidiViewImpl mid = new MidiViewImpl();
    GuiViewFrame gui = new GuiViewFrame(ex1);

    CompositeView newView = new CompositeView(mid, gui);
  }
}*/
