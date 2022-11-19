package org.example.Models;


public class PlaylistDetails
{
    private String playlistid;
    private String audioid;



    private String audioname;

    public PlaylistDetails(String playlistid, String audioname,String audioid)
    {
        this.playlistid = playlistid;
        this.audioname=audioname;
        this.audioid = audioid;
    }

    public String getPlaylistid() {
        return playlistid;
    }

    public void setPlaylistid(String playlistid) {
        this.playlistid = playlistid;
    }

    public String getAudioid() {
        return audioid;
    }

    public void setAudioid(String audioid) {
        this.audioid = audioid;
    }
    public String getAudioname()
    {
        return audioname;
    }

    public void setAudioname(String audioname) {
        this.audioname = audioname;
    }
}
