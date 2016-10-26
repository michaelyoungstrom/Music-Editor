package cs3500.music.model;

import java.util.ArrayList;

import cs3500.music.util.CompositionBuilder;

public class CompositionBuilderImpl implements CompositionBuilder<MusicModelImpl> {

  private ArrayList<ourPlayable> music = new ArrayList<>();
  private int tempo;


  @Override
  public MusicModelImpl build() {
    return new MusicModelImpl(music, tempo);
  }

  @Override
  public CompositionBuilder<MusicModelImpl> setTempo(int tempo) {
    this.tempo = tempo;
    return this;
  }

  @Override
  public CompositionBuilder<MusicModelImpl> addNote(int start, int end, int instrument,
                                                    int pitch, int volume) {
   this.music.add(new Note(start, end, pitch, instrument, volume));
    return this;
  }


}
