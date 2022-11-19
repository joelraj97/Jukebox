package DAOInterfaces;

import Models.Podcast;

import java.util.List;

public interface PodcastInterface
{
List<Podcast> displayAllPodcasts();
Podcast searchPodcast(String podcastname);

}
