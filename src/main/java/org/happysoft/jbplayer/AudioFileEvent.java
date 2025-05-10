/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.happysoft.jbplayer;

import org.happysoft.jbplayer.RequestDetails;

/**
 *
 * @author chrisf
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
  
  private long framePosition = 0L;
  private final RequestDetails requestDetails;
  private final PlaybackStatus status;
  
  public AudioFileEvent(RequestDetails requestDetails, PlaybackStatus status, long framePosition) {
    this.requestDetails = requestDetails;
    this.status = status;
    this.framePosition = framePosition;
  }
  
  public PlaybackStatus getStatus() {
    return status;
  }
  
  public RequestDetails getRequestDetails() {
    return requestDetails;
  }
  
  public long getFrame() {
    return framePosition;
  }
}
