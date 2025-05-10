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

public class TrackDetails implements Serializable{

  protected String trackName;
  protected String artistName;
  protected String albumName;
  protected String ownedBy;

  protected long trackId;
  protected long artistId;
  protected long albumId;

  public TrackDetails() {
  }

  public long getAlbumId() {
    return albumId;
  }
  public void setAlbumId(long albumId) {
    this.albumId = albumId;
  }
  public void setAlbumName(String albumName) {
    this.albumName = albumName;
  }
  public String getAlbumName() {
    return albumName;
  }
  public long getArtistId() {
    return artistId;
  }
  public void setArtistId(long artistId) {
    this.artistId = artistId;
  }
  public void setArtistName(String artistName) {
    this.artistName = artistName;
  }
  public String getArtistName() {
    return artistName;
  }
  public long getTrackId() {
    return trackId;
  }
  public void setTrackId(long trackId) {
    this.trackId = trackId;
  }
  public void setTrackName(String trackName) {
    this.trackName = trackName;
  }
  public String getTrackName() {
    return trackName;
  }
  public String getOwnedBy() {
    return ownedBy;
  }
  public void setOwnedBy(String ownedBy) {
    this.ownedBy = ownedBy;
  }

}
