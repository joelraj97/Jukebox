package org.example.Models;

public class Song
{
    private String songid;
    private String songname;
    private String artist;
    private String genre;
    private String duration;
    private String filepath;

    

    public Song()
    {

    }
    public Song(String songid, String songname, String artist, String genre,String duration ,String filepath) {
        this.songid = songid;
        this.songname = songname;
        this.artist = artist;
        this.genre = genre;
        this.duration=duration;
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


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
