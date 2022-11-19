package org.example.DAOInterfaces;

import org.example.Models.Podcast;

import java.util.List;

public interface PodcastInterface
{
    List<Podcast> displayAllPodcasts();
    Podcast searchPodcast(String podcastname);
    List<Podcast> allPodcastDetails();

}
