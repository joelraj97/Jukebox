package Models;

public class PlaylistDetails
{
private String playlistid;
private String audioid;

    public PlaylistDetails(String playlistid, String audioid)
    {
        this.playlistid = playlistid;
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
}
