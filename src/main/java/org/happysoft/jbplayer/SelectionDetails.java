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


public class SelectionDetails extends TrackDetails implements Serializable {

  protected String remoteMachine;
  protected String remoteDirectory;
  protected String path;
  protected String category;

  protected long ownedById;

  public SelectionDetails() {
  }

  public String getRemoteMachine() {
    return remoteMachine;
  }
  public void setRemoteMachine(String remoteMachine) {
    this.remoteMachine = remoteMachine;
  }
  public String getRemoteDirectory() {
    return remoteDirectory;
  }
  public void setRemoteDirectory(String remoteDirectory) {
    this.remoteDirectory = remoteDirectory;
  }
  public long getOwnedById() {
    return ownedById;
  }
  public void setOwnedById(long ownedById) {
    this.ownedById = ownedById;
  }
  public String getFullPath() {
    return path;
  }
  public void setFullPath(String path) {
    this.path = path;
  }
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }


}
