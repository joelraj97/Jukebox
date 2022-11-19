package org.example.DAOInterfaces;

import org.example.Models.Song;
import java.util.List;
public interface SongInterface
{
    List<Song> displayAllSongs();
    List<Song> sortSongs(String category);
    Song searchSong(String songname);
    List<Song> allSongDetails();

    List<Song> songCategory(String category,String categoryvalue);

}