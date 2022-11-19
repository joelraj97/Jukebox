package org.example;
import org.example.DAOImplClasses.PlayListDetailsImpl;
import org.example.DAOImplClasses.PlayListImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayListDetailsImplTest
{
    PlayListDetailsImpl plalistdetailsobj=null;
    @BeforeEach
    public void setUp()
    {
        plalistdetailsobj=new PlayListDetailsImpl();
    }
    @AfterEach
    public void tearDown()
    {
        plalistdetailsobj=null;
    }
    @Test
    public void allplaylisDetailsTest()
    {
        assertEquals("NenjukalPeidhidum",plalistdetailsobj.allplayListDetails("P1").get(2).getAudioname());
    }
    @Test
    public void displayPLylistDetailsTest()
    {
        assertEquals("po1",plalistdetailsobj.displayPlayListDetails("P1").get(3).getAudioid());
    }
}
