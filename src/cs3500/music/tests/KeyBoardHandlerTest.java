/*
package cs3500.music.tests;

import cs3500.music.controller.KeyBoardHandler;
import cs3500.music.model.MusicModelImpl;
import cs3500.music.model.Note;
import cs3500.music.model.Playable;
import cs3500.music.view.GuiViewFrame;
import org.junit.Test;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class KeyBoardHandlerTest {

    @Test
    public void testKeyPressed() throws Exception {

        Playable c1 = new Note(0, 2, 1, 1, 1);
        Playable b1 = new Note(2, 4, 12, 1, 1);
        Playable c2 = new Note(2, 3, 13, 1, 1);
        Playable d2 = new Note(0, 4, 15, 1, 1);
        MusicModelImpl ex1 = new MusicModelImpl.Builder()
                .music(makeMT()).currBeat(0).lastBeat().lowestPitch().highestPitch().build();

        GuiViewFrame gui = new GuiViewFrame(ex1);

        KeyBoardHandler k = new KeyBoardHandler(gui, ex1);
        gui.addKeyListener(k);



    }

    ArrayList<Playable> makeMT() {
        ArrayList<Playable> mt = new ArrayList<Playable>();
        return mt;
    }
}*/
