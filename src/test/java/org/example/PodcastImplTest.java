package org.example;
import org.example.DAOImplClasses.PodcastImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class PodcastImplTest
{
PodcastImpl podcastobj=null;
@BeforeEach
public void setUp()
{
  podcastobj=new PodcastImpl();
}
@AfterEach
public void tearDown()
{
  podcastobj=null;
}
@Test
public void seach_podcast_test()
{
 assertEquals("VOA",podcastobj.searchPodcast("Time").getSpeaker());
}
@Test
public void displayAllPodcast_Test()
{
 assertEquals("MusicandHumanBrain",podcastobj.displayAllPodcasts().get(1).getPodcastname());
}

}
