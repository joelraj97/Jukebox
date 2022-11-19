package org.example.DAOInterfaces;

import java.util.List;

public interface PlaylistInterface
{
    void createnewPlayList(String username);
    boolean deletePlaylist(String username,String playlistname);
    void openPlaylist(String username,String playlistname);
    List<String> displayPlaylistDetails(String username);

}

