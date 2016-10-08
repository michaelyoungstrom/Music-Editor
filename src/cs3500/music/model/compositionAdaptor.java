package cs3500.music.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mayoungstrom on 12/7/15.
 */
public class compositionAdaptor implements EditableCompositionModel{

    MusicModelImpl song;

    public compositionAdaptor(MusicModelImpl song){
        this.song = song;
    }

    public Note createNote(Playable note){

        int dur = note.getDuration();
        int start = note.getStartBeat();
        int end = start + dur - 1;
        int instr = note.getInstrument();
        int volume = note.getVolume();

        int num = note.numericalPitch();

        String s = cs3500.music.model.Playable.Util.NumbertoNoteString(num);

        //Find octave
        boolean sharp = false;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '#'){
                sharp = true;
            }
        }
        String numString;
        if (sharp){
            numString = s.substring(2, s.length());
        } else {
            numString = s.substring(1, s.length());
        }
        int o = Integer.valueOf(numString);
        int oct = o;

        int pitchID = note.numericalPitch();

        Note newNote = new Note(start, end, pitchID, instr, volume);
        return newNote;
    }

    public Note createNoteAdd(Playable note){

        int dur = note.getDuration();
        int instr = note.getInstrument();
        int volume = note.getVolume();

        int start = note.getStartBeat();
        int end = start + dur - 1;

        int num = note.numericalPitch();

        String s = cs3500.music.model.Playable.Util.NumbertoNoteString(num);

        //Find octave
        boolean sharp = false;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '#'){
                sharp = true;
            }
        }
        String numString;
        if (sharp){
            numString = s.substring(2, s.length());
        } else {
            numString = s.substring(1, s.length());
        }
        int o = Integer.valueOf(numString);
        int oct = o;

        int pitchID = note.numericalPitch();

        Note newNote = new Note(start, end, pitchID, instr, volume);
        return newNote;
    }


    @Override
    public void addNote(Playable note) {
        Note newNote = createNoteAdd(note);
        song.addNote(newNote);
    }


    @Override
    public void addAllNotes(List<Playable> notes) {
        Note newNote;

        for (int i = 0; i < notes.size(); i++){
            newNote = createNote(notes.get(i));
            song.addNote(newNote);
        }
    }

    @Override
    public void addAllAfter(List<Playable> notes) {

    }


    @Override
    public List<Playable> allNotes() {
        return null;
    }

    @Override
    public void deleteNote(Playable note) {

        Note newNote = createNote(note);
        song.deleteNote(newNote);

    }

    @Override
    public void changeDuration(Playable note, int duration) {

        note.setDuration(duration);
    }

    @Override
    public void moveNote(Playable note, int newStartBeat) {

        note.setStartBeat(newStartBeat);
    }

    @Override
    public int highestNoteNumerical() {
        return song.getHighestPitch();
    }

    @Override
    public int lowestNoteNumerical() {
        return song.getLowestPitch();
    }

    @Override
    public int length() {
        return song.getSongLength();
    }

    @Override
    public List<Playable> startNotes(int startBeat) {
        List<ourPlayable> startingNotes = song.getNotesAt(startBeat);
        List<Playable> startingNotesTheirs = new ArrayList<Playable>();
        for (int i = 0; i < startingNotes.size(); i++){
            ourPlayableToPlayableAdapter adp =
                    new ourPlayableToPlayableAdapter(startingNotes.get(i));
            startingNotesTheirs.add(adp);
        }
        return startingNotesTheirs;
    }

    @Override
    public List<Playable> sustainedNotes(int sustainedBeat) {

        List<ourPlayable> test = song.getNotes();

        List<ourPlayable> susNotes = song.getNotesAt(sustainedBeat);
        List<Playable> susNotesTheirs = new ArrayList<Playable>();
        for (int i = 0; i < susNotes.size(); i++){
            ourPlayableToPlayableAdapter adp =
                    new ourPlayableToPlayableAdapter(susNotes.get(i));
            susNotesTheirs.add(adp);
        }
        return susNotesTheirs;
    }

    @Override
    public List<Playable> playingNotes(int playingBeat) {
        List<ourPlayable> startingNotes = song.isPlayingAt(playingBeat);
        List<Playable> startingNotesTheirs = new ArrayList<Playable>();
        for (int i = 0; i < startingNotes.size(); i++){
            ourPlayableToPlayableAdapter adp =
                    new ourPlayableToPlayableAdapter(startingNotes.get(i));
            startingNotesTheirs.add(adp);
        }
        return startingNotesTheirs;
    }

    @Override
    public void combineWith(EditableCompositionModel other) {

    }

    @Override
    public void addCompositionAfter(EditableCompositionModel other) {

    }

    @Override
    public int getTempo() {
        return song.getTempo();
    }

    @Override
    public void setTempo(int tempo) {
       song.setTempo(tempo);
    }

    @Override
    public int getCurrentBeat() {
        return song.currBeat();
    }

    @Override
    public void setCurrentBeat(int beat){
        song.setCurrBeat(beat);
    }

    @Override
    public void updateCurrentBeat() {
        song.addBeat();
    }

    @Override
    public void reset(){
        song.resetBeat();
    }

    @Override
    public int getEndMeasure(){
        return song.getEndMeasure();
    }

    @Override
    public int getStartMeasure(){
        return song.getStartMeasure();
    }

    @Override
    public void setEndMeasure(int m){
        song.setEndMeasure(m);
    }

    @Override
    public void setStartMeasure(int m){
        song.setStartMeasure(m);
    }
}
