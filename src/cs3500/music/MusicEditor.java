package cs3500.music;


import java.awt.event.MouseListener;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.controller.KeyBoardHandler;
import cs3500.music.controller.MouseHandler;
import cs3500.music.model.*;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.gui.CompositeView;
import cs3500.music.view.gui.GuiViewFrame;
import cs3500.music.view.midi.MidiViewImpl;
import cs3500.music.model.MusicModelImpl;


public class MusicEditor {
  public static void main(String[] args)
    throws IOException, InvalidMidiDataException, InterruptedException {

    CompositionBuilder<MusicModelImpl> comp = new CompositionBuilderImpl();
    String piece = args[1];
    MusicModelImpl ex1 = MusicReader.parseFile(new FileReader(piece), comp);

    MusicEditor.ViewFactory.create(args[0], ex1);

  }

    public static class ViewFactory {
      public ViewFactory(){
      }
      public static void create(String type, MusicModelImpl ex1) throws
              InvalidMidiDataException, InterruptedException {

        compositionAdaptor adap = new compositionAdaptor(ex1);

        switch (type) {
          case "CompositeView":
            CompositeView comp = new CompositeView();
            comp.render(adap);
            KeyBoardHandler k = new KeyBoardHandler(comp, ex1, adap);
            comp.addKeyListener(k);
            MouseHandler m = new MouseHandler(comp, ex1, adap);
            comp.addMouseListener(m);
            break;
          default:
            break;
        }

      }
    }
  }


