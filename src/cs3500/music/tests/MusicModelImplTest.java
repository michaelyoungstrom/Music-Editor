/*
package cs3500.music.tests;

import org.junit.Test;

import java.util.ArrayList;

import cs3500.music.model.MusicModelImpl;
import cs3500.music.model.Note;
import cs3500.music.model.Playable;

import static org.junit.Assert.assertEquals;

public class MusicModelImplTest {

  Playable c1 = new Note(0, 2, 1, 1, 1);
  Playable b1 = new Note(2, 4, 12, 1, 1);
  Playable c2 = new Note(2, 3, 13, 1, 1);
  Playable d2 = new Note(0, 4, 15, 1, 1);
  MusicModelImpl ex1 = new MusicModelImpl.Builder()
    .music(makeMT()).currBeat(0).lastBeat().lowestPitch().highestPitch().build();
  MusicModelImpl ex2 = new MusicModelImpl.Builder()
    .music(makeB()).currBeat(0).lastBeat().lowestPitch().highestPitch().build();
  MusicModelImpl ex3 = new MusicModelImpl.Builder()
    .music(makeA()).currBeat(0).lastBeat().lowestPitch().highestPitch().build();

  ArrayList<Playable> makeA() {
    ArrayList<Playable> a = new ArrayList<Playable>();
    a.add(c1);
    return a;
  }

  ArrayList<Playable> makeB() {
    ArrayList<Playable> b = new ArrayList<Playable>();
    b.add(c1);
    b.add(b1);
    b.add(c2);
    return b;
  }

  ArrayList<Playable> makeD() {
    ArrayList<Playable> d = new ArrayList<Playable>();
    d.add(d2);
    return d;
  }

  ArrayList<Playable> makeMT() {
    ArrayList<Playable> mt = new ArrayList<Playable>();
    return mt;
  }

  @Test
  public void testSongLength() {
    assertEquals(ex1.getSongLength(), 0);
    assertEquals(ex2.getSongLength(), 4);
  }

  @Test
  public void testAddNotes() {
    assertEquals(ex1.getNotes(), makeMT());
    ex1.addNote(d2);
    assertEquals(ex1.getNotes(), makeD());
  }

  @Test
  public void testDeleteNote() {
    assertEquals(ex3.getNotes(), makeA());
    ex3.deleteNote(c1);
    assertEquals(ex3.getNotes(), makeMT());
  }



}*/
