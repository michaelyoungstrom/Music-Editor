package cs3500.music.view.midi;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiDeviceReceiver;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.ShortMessage;

public class MockReceiver implements MidiDeviceReceiver {
  private final StringBuilder log;

  public MockReceiver(StringBuilder log) {
    this.log = log;
  }

  @Override
  public MidiDevice getMidiDevice() {
    return null;
  }

  @Override
  public void send(MidiMessage message, long timeStamp) {
    int command = message.getStatus();
//    if (command == 248) {
//      this.log.append("tempo ");
//      this.log.append(((ShortMessage)message).getData1() + " ");
//      this.log.append('\n');
//    } else
    if (command >= 144 && command <= 159) {
      this.log.append("note on  ");
      this.log.append(timeStamp + " ");
      this.log.append((((ShortMessage)message).getChannel() + 1) + " ");
      this.log.append(((ShortMessage)message).getData1() + " ");
      this.log.append(((ShortMessage)message).getData2() + " ");
      this.log.append('\n');
    } else if (command >= 128 && command <= 143){
      this.log.append("note off ");
      this.log.append(timeStamp + " ");
      this.log.append((((ShortMessage)message).getChannel() + 1) + " ");
      this.log.append(((ShortMessage)message).getData1() + " ");
      this.log.append(((ShortMessage)message).getData2() + " ");
      this.log.append('\n');
    } else {
      this.log.append("unrecognized MidiMessage: " + command);
      this.log.append('\n');
    }
  }

  @Override
  public void close() {

  }
}
