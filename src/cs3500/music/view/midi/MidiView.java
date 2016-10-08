package cs3500.music.view.midi;

import cs3500.music.view.EditableCompositionView;

public interface MidiView extends EditableCompositionView {

  boolean isPaused();

  void pause();


}
