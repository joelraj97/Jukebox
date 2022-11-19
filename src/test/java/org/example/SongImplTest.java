package org.example;

import org.example.DAOImplClasses.SongImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SongImplTest
{
    SongImpl songobj=null;
@BeforeEach
public void setUp()
{
    songobj=new SongImpl();
}
@AfterEach
public void tearDown()
{
    songobj=null;
}
@Test
public void display_all_song_test()
{
    assertEquals(10,songobj.displayAllSongs().size());
    String expected="Perfect";
    String actual=songobj.displayAllSongs().get(0).getSongname();
    assertEquals(expected,actual);
}
@Test
public void sort_song_test()
{
    assertEquals("Mizhiyariyathe",songobj.sortSongs("SongName").get(2).getSongname());
    assertEquals("Eminem",songobj.sortSongs("genre").get(4).getArtist());
}
@Test
public void searchSong_Test()
{
   assertEquals("LinkinPark",songobj.searchSong("Numb").getArtist());
   assertEquals(null,songobj.searchSong("Firefly").getSongname());
}



}
