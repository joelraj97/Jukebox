package org.example.Models;

import java.sql.Date;

public class Podcast
{
    private String podcastid;
    private String podcastname;
    private String speaker;
    private int episodes;
    private String duration;
    private Date releasedate;
    private String filepath;


    public Podcast()
    {

    }
    public Podcast(String podcastid, String podcastname, String speaker, int episodes, String duration,Date releasedate,String filepath) {
        this.podcastid = podcastid;
        this.podcastname = podcastname;
        this.speaker = speaker;
        this.episodes = episodes;
        this.duration=duration;
        this.releasedate=releasedate;
        this.filepath = filepath;
    }

    public String getPodcastid() {
        return podcastid;
    }

    public void setPodcastid(String podcastid) {
        this.podcastid = podcastid;
    }

    public String getPodcastname() {
        return podcastname;
    }

    public void setPodcastname(String podcastname) {
        this.podcastname = podcastname;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public String getDuration() {
        return duration;
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
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
