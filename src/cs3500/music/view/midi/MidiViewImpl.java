package cs3500.music.view.midi;

import cs3500.music.model.EditableCompositionModel;
import cs3500.music.model.Playable;
import cs3500.music.view.EditableCompositionView;

import javax.sound.midi.*;
import java.util.List;

/**
 * A skeleton for MIDI playback
 */
public class MidiViewImpl implements EditableCompositionView, MidiView {
  private final Synthesizer synth;
  private final Receiver receiver;
  private Synthesizer s;
  private Receiver r;
  private final boolean DEBUG;
  private boolean isPaused;
  private boolean toMock;

  public MidiViewImpl() {
    try {
      s = MidiSystem.getSynthesizer();
      r = s.getReceiver();
      s.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
      s = null;
      r = null;
    }
    this.synth = s;
    this.receiver = r;
    this.DEBUG = false;
  }

  public MidiViewImpl(MockSynthesizer mockSynth) {
    try {
      s = mockSynth;
      r = mockSynth.getReceiver();
      s.open();
    } catch(MidiUnavailableException e) {
      e.printStackTrace();
      s = null;
      r = null;
    }
    this.synth = s;
    this.receiver = r;
    this.DEBUG = true;
  }

  /**
   * Relevant classes and methods from the javax.sound.midi library:
   * <ul>
   * <li>{@link MidiSystem#getSynthesizer()}</li>
   * <li>{@link Synthesizer}
   * <ul>
   * <li>{@link Synthesizer#open()}</li>
   * <li>{@link Synthesizer#getReceiver()}</li>
   * <li>{@link Synthesizer#getChannels()}</li>
   * </ul>
   * </li>
   * <li>{@link Receiver}
   * <ul>
   * <li>{@link Receiver#send(MidiMessage, long)}</li>
   * <li>{@link Receiver#close()}</li>
   * </ul>
   * </li>
   * <li>{@link MidiMessage}</li>
   * <li>{@link ShortMessage}</li>
   * <li>{@link MidiChannel}
   * <ul>
   * <li>{@link MidiChannel#getProgram()}</li>
   * <li>{@link MidiChannel#programChange(int)}</li>
   * </ul>
   * </li>
   * </ul>
   *
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   * https://en.wikipedia.org/wiki/General_MIDI
   * </a>
   */

  public void playNote(Playable note, int tempo) throws InvalidMidiDataException {
    ShortMessage start = new ShortMessage(ShortMessage.NOTE_ON, note.getInstrument() - 1,
            note.numericalPitch(), note.getVolume());
    ShortMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, note.getInstrument() - 1,
            note.numericalPitch(), note.getVolume());
    this.receiver.send(start, this.synth.getMicrosecondPosition());
    if (!DEBUG) {
      this.receiver.send(stop, this.synth.getMicrosecondPosition() + note.getDuration() * tempo);
    } else {
      this.receiver.send(stop, this.synth.getMicrosecondPosition() + note.getDuration());
    }
  }

  public void renderBeat(EditableCompositionModel model, int beatNum) {
    List<Playable> beat = model.startNotes(beatNum);
    for (Playable note : beat) {
      try {
        this.playNote(note, model.getTempo());
      } catch (InvalidMidiDataException e) {
        throw new RuntimeException("InvalidMidiDataException");
      }
    }
  }

  public void closeReceiver() {
    this.receiver.close();
  }

  /**
   * @param model
   */
  @Override
  public void render(EditableCompositionModel model) {
    for (int beatNum = 0; beatNum < model.length(); beatNum++) {
      this.renderBeat(model, beatNum);
      if (!DEBUG) {
        try {
          Thread.sleep(model.getTempo() / 1000);
        } catch (InterruptedException e) {
          throw new RuntimeException("got an Interrupted Exception in Thread.sleep");
        }
      } else {
        ((MockSynthesizer) this.synth).updateMicrosecondPosition();
      }
    }
    this.receiver.close(); // Only call this once you're done playing *all* notes
  }

  @Override
  public boolean isPaused() {
    return this.isPaused;
  }

  @Override
  public void pause() {
    MidiChannel [] channel = this.synth.getChannels();
    for(MidiChannel mc: channel) {
      mc.allNotesOff();
    }
    this.isPaused = true;
  }
}