package org.happysoft.jbplayer;

import java.util.ArrayList;
import java.util.List;


public class PlayList {

  private final List<RequestDetails> queue = new ArrayList<>();
  private RequestDetails lastRequest = null;
  
  private static final PlayList INSTANCE = new PlayList();

  private PlayList() {
  }

  public static PlayList getPlayList() {
    return INSTANCE;
  }

  public void addSelection(RequestDetails details) {
    queue.add(details);
  }

  public void removeSelection(RequestDetails details) {
    queue.remove(details);
  }

  public RequestDetails getNextSelection() {
    if(!queue.isEmpty()) {
      lastRequest = queue.remove(0);
      return lastRequest;
    }
    lastRequest = null;
    return null;
  }

  public RequestDetails findRequest(long requestId) {
    RequestDetails r = null;
    boolean found = false;
    int index = 0;
    while(!found && index < queue.size()) {
      r = queue.get(index);
      if(r.getRequestId() == requestId) {
        found = true;
      }
      index++;
    }
    if(!found) {
      r = null;
    }
    return r;
  }


}