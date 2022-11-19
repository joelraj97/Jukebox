package org.example;
import org.example.DAOImplClasses.PlayListImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PlaylistImplTest
{
PlayListImpl plalistobj=null;
@BeforeEach
public void setUp()
{
  plalistobj=new PlayListImpl();
}
@AfterEach
public void tearDown()
{
   plalistobj=null;
}
@Test
public void  displayPlayListTest()
{
    assertEquals("Melody",plalistobj.displayPlaylistDetails("harrykane").get(0));
}
@Test
public void deletePlayListTest()
{
    assertFalse(plalistobj.deletePlaylist("harrykane","harmony"));
}
}
