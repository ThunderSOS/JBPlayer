
package org.happysoft.jbplayer;

/**
 * @author ChrisF
 */
public class AudioFileEvent {
  
  public static enum PlaybackStatus{
    MEDIA_STARTED, 
    MEDIA_ENDED, 
    MEDIA_STOPPED, 
    MEDIA_PAUSED,
    MEDIA_RESUMED, 
    MEDIA_ERROR
  }
  
  private final RequestDetails requestDetails;
  private final PlaybackStatus status;
  
  public AudioFileEvent(RequestDetails requestDetails, PlaybackStatus status) {
    this.requestDetails = requestDetails;
    this.status = status;
  }
  
  public PlaybackStatus getStatus() {
    return status;
  }
  
  public RequestDetails getRequestDetails() {
    return requestDetails;
  }

}
