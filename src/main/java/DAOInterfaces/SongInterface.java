package DAOInterfaces;

import Models.Song;

import java.util.List;

public interface SongInterface
{
List<Song> displayAllSongs();
List<Song> sortSongs(String category);
Song searchSong(String songname);

}
