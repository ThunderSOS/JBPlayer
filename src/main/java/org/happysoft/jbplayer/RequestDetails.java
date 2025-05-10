/*
 Java Jukebox
 Copyright (C) 2004, Chris Francis.

 This program is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public License
 as published by the Free Software Foundation; either version 2
 of the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*/

package org.happysoft.jbplayer;

import java.io.Serializable;
import java.time.Instant;


public class RequestDetails extends SelectionDetails implements Serializable {

  protected Instant requestTime;
  protected long requestId;
  protected String requestedBy;
  protected String status;

  public RequestDetails() {
  }

  public long getRequestId() {
    return requestId;
  }
  public void setRequestId(long requestId) {
    this.requestId = requestId;
  }
  public Instant getRequestTime() {
    return requestTime;
  }
  public void setRequestTime(Instant requestTime) {
    this.requestTime = requestTime;
  }
  public String getRequestedBy() {
    return requestedBy;
  }
  public void setRequestedBy(String requestedBy) {
    this.requestedBy = requestedBy;
  }
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }

}


