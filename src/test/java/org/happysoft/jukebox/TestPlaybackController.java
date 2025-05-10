package org.happysoft.jukebox;

import java.io.File;
import junit.framework.TestCase;
import org.happysoft.jbplayer.*;

/**
 * @author chrisf
 */
public class TestPlaybackController extends TestCase implements AudioPlaybackListener {
  
  private final AudioFilePlayer player = new AudioFilePlayer();
  private final File pathToTestDir = new File("./src/test/java/").getAbsoluteFile();
  
  private int mediaEndedEventsCount = 0;
  private int mediaErrorEventsCount = 0;
  private int mediaPausedEventsCount = 0;
  private int mediaResumedEventsCount = 0;
  private int mediaStartedEventsCount = 0;
  private int mediaStoppedEventsCount = 0;
  
  @Override
  public void notifyMediaEvent(AudioFileEvent event) {
    System.out.println("AudioEvent - " + event.getStatus() + " - " + event.getRequestDetails().getFullPath());
    switch(event.getStatus()) {
      case MEDIA_ENDED -> mediaEndedEventsCount++;
        
      case MEDIA_ERROR -> mediaErrorEventsCount++;
        
      case MEDIA_PAUSED -> mediaPausedEventsCount++;
        
      case MEDIA_RESUMED -> mediaResumedEventsCount++;
       
      case MEDIA_STARTED -> mediaStartedEventsCount++;
        
      case MEDIA_STOPPED -> mediaStoppedEventsCount++;
          
    }
  }
  
  void resetCounts() {
    mediaEndedEventsCount = 0;
    mediaErrorEventsCount = 0;
    mediaPausedEventsCount = 0;
    mediaResumedEventsCount = 0;
    mediaStartedEventsCount = 0;
    mediaStoppedEventsCount = 0;
  }
  
  @Override
  protected void setUp() throws Exception {        
    player.addMediaListener(this);
    player.start();
  }
  
  public void testPlayAndPauseMp3() throws Exception {    
    System.out.println("Playing mp3");
    resetCounts();
    
    var r = new RequestDetails();
    r.setFullPath(pathToTestDir.toString() + "/fileexampleMP3700KB.mp3");
    
    var pl = PlayList.getPlayList();
    pl.addSelection(r);
    
    Thread.sleep(5000);
    player.togglePaused();
    
    Thread.sleep(5000);
    player.togglePaused();
    
    Thread.sleep(5000);
    player.stopCurrentRequest();
    
    assertEquals(1, mediaStartedEventsCount);
    assertEquals(1, mediaPausedEventsCount);
    assertEquals(1, mediaResumedEventsCount);
    assertEquals(1, mediaStoppedEventsCount);
    assertEquals(0, mediaErrorEventsCount);
    assertEquals(0, mediaEndedEventsCount);
  }
  
  public void testPlayAndPauseOgg() throws Exception {
    System.out.println("Playing ogg");
    resetCounts();
    
    var r = new RequestDetails();
    r.setFullPath(pathToTestDir.toString() + "/fileexampleOOG1MG.oga");

    var pl = PlayList.getPlayList();
    pl.addSelection(r);
    
    Thread.sleep(5000);
    player.togglePaused();
    
    Thread.sleep(5000);
    player.togglePaused();
    
    Thread.sleep(5000);
    player.stopCurrentRequest();
    
    assertEquals(1, mediaStartedEventsCount);
    assertEquals(1, mediaPausedEventsCount);
    assertEquals(1, mediaResumedEventsCount);
    assertEquals(1, mediaStoppedEventsCount);
    assertEquals(0, mediaErrorEventsCount);
    assertEquals(0, mediaEndedEventsCount);
  }
  
  public void testPlayAllWayThrough() throws Exception {
    System.out.println("Play all the way");
    resetCounts();
    
    var r = new RequestDetails();
    r.setFullPath(pathToTestDir.toString() + "/fileexampleOOG1MG.oga");

    var pl = PlayList.getPlayList();
    pl.addSelection(r);
    
    while(mediaEndedEventsCount == 0) {
      Thread.sleep(10);
    }
    
    assertEquals(1, mediaStartedEventsCount);
    assertEquals(0, mediaPausedEventsCount);
    assertEquals(0, mediaResumedEventsCount);
    assertEquals(0, mediaStoppedEventsCount);
    assertEquals(0, mediaErrorEventsCount);
    assertEquals(1, mediaEndedEventsCount);
  }
  
  public void testNotPlayingStopThenAddTrack() throws Exception {
    System.out.println("Play after stop");
    resetCounts();
    
    // this shouldn't generate an event as the player isn't playing
    player.stopCurrentRequest();
    
    var r = new RequestDetails();
    r.setFullPath(pathToTestDir.toString() + "/fileexampleOOG1MG.oga");

    var pl = PlayList.getPlayList();
    pl.addSelection(r);
    
    Thread.sleep(5000);
    player.stopCurrentRequest();
    
    assertEquals(1, mediaStartedEventsCount);
    assertEquals(0, mediaPausedEventsCount);
    assertEquals(0, mediaResumedEventsCount);
    assertEquals(1, mediaStoppedEventsCount); // not 2
    assertEquals(0, mediaErrorEventsCount);
    assertEquals(0, mediaEndedEventsCount);
  }
  
  @Override
  protected void tearDown() throws Exception {
    player.stop();
  }

}
