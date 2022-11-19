package Models;

public class Song
{
private String songid;
private String songname;
private String artist;
private String genre;
private String filepath;

    public Song(String songid, String songname, String artist, String genre, String filepath) {
        this.songid = songid;
        this.songname = songname;
        this.artist = artist;
        this.genre = genre;
        this.filepath = filepath;
    }

    public String getSongid() {
        return songid;
    }

    public void setSongid(String songid) {
        this.songid = songid;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
