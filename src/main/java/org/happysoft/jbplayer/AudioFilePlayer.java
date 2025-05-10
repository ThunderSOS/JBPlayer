/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.happysoft.jbplayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;

public class AudioFilePlayer implements Runnable {
  
  private long framePosition;
  private AudioFormat audioFormat;
  private AudioInputStream fileInputStream;
  private AudioInputStream audioInputStream;
  private SourceDataLine line;
  private final List<AudioPlaybackListener> listeners = new ArrayList<>();
  private RequestDetails currentRequest;
  
  private final Thread playerThread;
  
  private volatile boolean paused = false;
  private volatile boolean stopPlaying = false;
  private volatile boolean quit = false;

  public AudioFilePlayer() {
    playerThread = new Thread(this);
  }
  
  public void start() {
    playerThread.start();
  }
  
  public void stop() {
    quit = true;
  }
  
  @Override
  public void run() {
    PlayList pl = PlayList.getPlayList();
    while(!quit) {
      currentRequest = pl.getNextSelection();
      if(currentRequest != null) {
        play(currentRequest.getFullPath());
      }
    }
  }
  
  public void addMediaListener(AudioPlaybackListener listener) {
    listeners.add(listener);
  }
  
  public void togglePaused() {
    paused = !paused;
    if(paused) {
      fireMediaEvent(AudioFileEvent.PlaybackStatus.MEDIA_PAUSED);
    } else {
      fireMediaEvent(AudioFileEvent.PlaybackStatus.MEDIA_RESUMED);
    }
  }
  
  public void stopCurrentRequest() {
    //just clearing the line doesn't seem to be enough so we physically exit 
    //the play loop. 
    if(currentRequest != null) {
      stopPlaying = true;
      fireMediaEvent(AudioFileEvent.PlaybackStatus.MEDIA_STOPPED);
    }
  }
  
  private void play(String filePath) {
    final File file = new File(filePath);
    
    try {
      fireMediaEvent(AudioFileEvent.PlaybackStatus.MEDIA_STARTED);
      fileInputStream = AudioSystem.getAudioInputStream(file);
      audioFormat = getOutputFormat(fileInputStream.getFormat());
      playViaSourceDataLine();

    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {      
      fireMediaEvent(AudioFileEvent.PlaybackStatus.MEDIA_ERROR);
    }
  }
  
  private void playViaSourceDataLine() throws IOException, LineUnavailableException {
    Info info = new Info(SourceDataLine.class, audioFormat);
    audioInputStream = AudioSystem.getAudioInputStream(audioFormat, fileInputStream);
    line = (SourceDataLine) AudioSystem.getLine(info);    
    if (line != null) {
      line.open(audioFormat);
      line.start();        
      playStream();
      clearLine();
    }
  }
  
  private void clearLine() {
    if(line != null) {
      line.drain();
      line.stop();
      line.close();
    }
  }

  private AudioFormat getOutputFormat(AudioFormat inFormat) {
    int ch = inFormat.getChannels();
    float rate = inFormat.getSampleRate();
    return new AudioFormat(PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
  }
  
  private void playStream() throws IOException {
    final byte[] buffer = new byte[4096];
    while (!stopPlaying) {
      if(!paused) {
        int n = audioInputStream.read(buffer, 0, buffer.length);
        if (n != -1) {
          framePosition = line.getLongFramePosition();
          line.write(buffer, 0, n);
        } else {
          fireMediaEvent(AudioFileEvent.PlaybackStatus.MEDIA_ENDED);
          break;
        }
      }
    }
    clearLine();
    stopPlaying = false;
  }
  
  private void fireMediaEvent(AudioFileEvent.PlaybackStatus status) {
    if(currentRequest != null) {
      AudioFileEvent event = new AudioFileEvent(currentRequest, status, framePosition);
      for (AudioPlaybackListener listener : listeners) {
        listener.notifyMediaEvent(event);
      }
    }
  }
}
