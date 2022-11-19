package org.example.Models;


public class Playlist
{
    private String Playlistid;
    private String PLaylistName;
    private String username;

    public Playlist(String playlistid, String PLaylistName, String username) {

        Playlistid = playlistid;
        this.PLaylistName = PLaylistName;
        this.username = username;
    }

    public String getPlaylistid() {
        return Playlistid;
    }

    public void setPlaylistid(String playlistid) {
        Playlistid = playlistid;
    }

    public String getPLaylistName() {
        return PLaylistName;
    }

    public void setPLaylistName(String PLaylistName) {
        this.PLaylistName = PLaylistName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
