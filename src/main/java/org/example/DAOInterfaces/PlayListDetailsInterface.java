package org.example.DAOInterfaces;

import org.example.Models.PlaylistDetails;

import java.util.List;

public interface PlayListDetailsInterface
{
    void addSong(String playlistid,String playlistname,String songid);
    void addPodcast(String playlistid,String playlistname,String podcastid);
    void deleteSong(String songid,String playlistid);
    void deletePodcast(String podcastid,String playlistid);
    List<PlaylistDetails> displayPlayListDetails(String playlistid);
     List<PlaylistDetails> allplayListDetails(String playlistid);

}
