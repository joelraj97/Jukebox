package org.example.DAOImplClasses;

import org.example.DAOInterfaces.PlaylistInterface;
import org.example.DAOInterfaces.PodcastInterface;
import org.example.DAOInterfaces.SongInterface;
import org.example.DAOInterfaces.UserInterface;
import org.example.DBConnection;
import org.example.Models.Podcast;
import org.example.Models.Song;
import org.example.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserImpl implements UserInterface {
    @Override
    public String createNewUser(String username,String password)
    {

        Scanner scan=new Scanner(System.in);
       // boolean status=false;
        try {
         //   do {

                //System.out.println("Enter username");
                //String username = scan.next();
               // System.out.println("Enter Password");
               // String password = scan.next();
                int flag = 0;
                Connection con = DBConnection.getConnection();
                String query = "select username from User;";
                PreparedStatement pst1 = con.prepareStatement(query);
                ResultSet rs2 = pst1.executeQuery(query);
                while (rs2.next()) {
                    if (rs2.getString(1).equalsIgnoreCase(username)) {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 1)
                {
                    System.out.println("Username Already Exists");
                    return "Username Already Exists";
                //    status=true;
                }
                else
                {
                 //   status=false;
                    User user = new User(username, password);
                    String insertuser="insert into User values('"+user.getUsername()+"','"+user.getPassword()+"');";
                    PreparedStatement pst2 = con.prepareStatement(insertuser);
                    pst2.executeUpdate();
                    SongInterface songs=new SongImpl();
                    PodcastInterface podcasts=new PodcastImpl();
                    PlaylistInterface playlists=new PlayListImpl();
                    System.out.println("Welcome "+username+" to Jukebox ");
                    while(true)
                    {
                        System.out.println("Enter your choice \n1.Display All Songs \n2.Sort Songs \n3.Search Song \n4.Search Song by Category \n5.Play Song\n6.Display All Podcasts \n7.Search Podcast\n8.Play  Podcast\n9.Create Playlist\n10.All PlayList Available \n11.Delete Playlist \n12.Open Playlist\n13.Exit App");
                        int userchoice=scan.nextInt();
                        switch(userchoice)
                        {
                            case  1:System.out.println("All Songs available are");

                                    songs.displayAllSongs();
                                     break;
                            case 2:   String sortcontinuation="";
                                      do {
                                        System.out.println("Sort Songs Based on \n1.Songname \n2.Artist \n3.Genre");
                                        int sortchoice = scan.nextInt();
                                        if (sortchoice == 1) {
                                           songs.sortSongs("Songname");
                                        } else if (sortchoice == 2) {
                                           songs.sortSongs("Artist");
                                        } else if (sortchoice == 3) {
                                           songs.sortSongs("Genre");
                                        } else {
                                           System.out.println("Wrong Choice");
                                        }
                                         System.out.println("Do you want to Sort Again (Yes/No)");
                                         sortcontinuation = scan.next();
                                      }while(sortcontinuation.equalsIgnoreCase("yes"));
                                       break;
                            case 3:  System.out.println("Enter Song  You Want to Search ");
                                     String songname = scan.next();
                                     songs.searchSong(songname);
                                     break;
                            case 4: boolean categorysearchstatus = false;
                                do {
                                    System.out.println("Which Category you want to Search \n1.Artist \n2.Genre");
                                    int categorysearch = scan.nextInt();
                                    if (categorysearch == 1) {
                                        categorysearchstatus = false;
                                        System.out.println("Enter the Name of the Artist ");
                                        String artistname = scan.next();
                                        songs.songCategory("Artist", artistname);

                                    } else if (categorysearch == 2) {
                                        categorysearchstatus = false;
                                        System.out.println("Enter the Genre");
                                        String genrename = scan.next();
                                        songs.songCategory("Genre", genrename);
                                    } else {
                                        categorysearchstatus = true;
                                        System.out.println("Wrong Choice");
                                    }
                                }while(categorysearchstatus);
                                break;

                            case 5: List<Song> playsongstore=songs.displayAllSongs();
                                    System.out.println("Enter Song id you want to Play");
                                    String playsongid=scan.next();
                                    String playsongfilepath="";
                                    int playsongflag=0;
                                    for(Song x:playsongstore)
                                    {
                                        if(x.getSongid().equalsIgnoreCase(playsongid))
                                        {
                                            playsongfilepath=x.getFilepath();
                                            playsongflag=1;
                                        }
                                    }
                                    if(playsongflag==1)
                                    {
                                        PlayListImpl plew=new PlayListImpl();
                                        plew.readAudio(playsongfilepath,0);
                                    }
                                    else
                                    {
                                        System.out.println("Wrong Song Id");
                                    }

                                     break;
                            case 6: System.out.println("All Podcasts Available are");
                                     podcasts.displayAllPodcasts();
                                     break;
                            case 7:  System.out.println("Enter Podcast You want to Search");
                                     String podcastname = scan.next();
                                     podcasts.searchPodcast(podcastname);
                                     break;
                            case 8:  List<Podcast> playpodcast=podcasts.displayAllPodcasts();
                                      System.out.println("Enter Podcast Id you want to Play");
                                      String playpodcastid=scan.next();
                                      String podcastfilepath="";
                                      int playpodcastflag=0;
                                      for(Podcast x:playpodcast)
                                      {
                                          if(x.getPodcastid().equalsIgnoreCase(playpodcastid))
                                          {
                                              podcastfilepath=x.getFilepath();
                                              playpodcastflag=1;
                                          }
                                      }
                                      if(playpodcastflag==1)
                                      {
                                          PlayListImpl ploi=new PlayListImpl();
                                          ploi.readAudio(podcastfilepath,0);
                                      }
                                      else
                                      {
                                          System.out.println("Wrong Podcast Id");
                                      }
                                      break;
                            case 9:  playlists.createnewPlayList(user.getUsername());
                                     break;
                            case 10:  playlists.displayPlaylistDetails(user.getUsername());
                                     break;
                            case 11:  System.out.println("Enter PlayList You Want to delete");
                                     String playlistname=scan.next();
                                     playlists.deletePlaylist(user.getUsername(),playlistname);
                                     break;
                            case 12:  System.out.println("Enter PlayList You Want to Open");
                                     String openplaylistname=scan.next();
                                     playlists.openPlaylist(user.getUsername(),openplaylistname);
                                     break;
                            case 13:System.exit(0);

                            default:
                                System.out.println("Wrong Choice");
                        }
                    }
                }
           // }while(status);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return "Welcome "+username;
    }

    @Override
    public String existingUser(String username,String password)
    {
       // boolean status=false;
        Scanner scan=new Scanner(System.in);
        try {
      //  do {
            //System.out.println("Enter username");
            //String username = scan.next();
           // System.out.println("Enter Password");
           // String password = scan.next();
            int flag = 0;

                Connection con = DBConnection.getConnection();
            System.out.println("JKJDFKS");
                String query = "select username from User;";
                PreparedStatement pst1 = con.prepareStatement(query);
                ResultSet rs2 = pst1.executeQuery(query);

                while (rs2.next()) {
                    if (rs2.getString(1).equalsIgnoreCase(username)) {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 1)
                {
                    User u = new User(username, password);
               //     status=false;
                    SongInterface songs=new SongImpl();
                    PodcastInterface podcasts=new PodcastImpl();
                    PlaylistInterface playlists=new PlayListImpl();
                    System.out.println("Welcome "+username+" to Jukebox ");
                    while(true)
                    {
                        System.out.println("Enter your choice \n1.Display All Songs \n2.Sort Songs \n3.Search Song \n4.Search Song by category\n5.Play Song \n6.Display All Podcasts \n7.Search Podcast\n8.Play Podcast\n9.Create Playlist\n10.All Playlist Available \n11.Delete Playlist \n12.Open Playlist\n13.Exit App");
                        int userchoice=scan.nextInt();
                        switch(userchoice) {
                            case 1:
                                System.out.println("All Songs available are");
                                songs.displayAllSongs();
                                break;
                            case 2:
                                String sortcontinuation = "";
                                do {
                                    System.out.println("Sort Songs Based on \n1.Songname \n2.Artist \n3.Genre");
                                    int sortchoice = scan.nextInt();
                                    if (sortchoice == 1) {
                                        songs.sortSongs("Songname");
                                    } else if (sortchoice == 2) {
                                        songs.sortSongs("Artist");
                                    } else if (sortchoice == 3) {
                                        songs.sortSongs("Genre");
                                    } else {
                                        System.out.println("Wrong Choice");
                                    }
                                    System.out.println("do you want to Sort Again (Yes/No)");
                                    sortcontinuation = scan.next();
                                } while (sortcontinuation.equalsIgnoreCase("yes"));
                                break;
                            case 3:
                                System.out.println("Enter Song  You Want to Search ");
                                String songname = scan.next();
                                songs.searchSong(songname);
                                break;
                            case 4: boolean categorysearchstatus = false;
                                  do {
                                      System.out.println("Which Category you want to Search \n1.Artist \n2.Genre");
                                      int categorysearch = scan.nextInt();
                                       if (categorysearch == 1) {
                                           categorysearchstatus = false;
                                           System.out.println("Enter the Name of the Artist ");
                                           String artistname = scan.next();
                                           songs.songCategory("Artist", artistname);

                                     } else if (categorysearch == 2) {
                                          categorysearchstatus = false;
                                          System.out.println("Enter the Genre");
                                          String genrename = scan.next();
                                          songs.songCategory("Genre", genrename);
                                     } else {
                                        categorysearchstatus = true;
                                        System.out.println("Wrong Choice");
                                     }
                                     }while(categorysearchstatus);
                                      break;
                            case 5:     List<Song> playsongstore=songs.displayAllSongs();
                                        System.out.println("Enter Song id you want to Play");
                                        String playsongid=scan.next();
                                        String playsongfilepath="";
                                         int playsongflag=0;
                                         for(Song x:playsongstore)
                                          {
                                            if(x.getSongid().equalsIgnoreCase(playsongid))
                                               {
                                                  playsongfilepath=x.getFilepath();
                                                  playsongflag=1;
                                                }
                                         }
                                          if(playsongflag==1)
                                           {
                                            PlayListImpl plew=new PlayListImpl();
                                            plew.readAudio(playsongfilepath,0);
                                          }
                                           else
                                          {
                                           System.out.println("Wrong Song Id");
                                           }

                                           break;

                            case 6: System.out.println("All Podcasts Available are");
                                     podcasts.displayAllPodcasts();
                                     break;
                            case 7:  System.out.println("Enter Podcast You want to Search");
                                     String podcastname = scan.next();
                                     podcasts.searchPodcast(podcastname);
                                     break;
                            case 8:                  List<Podcast> playpodcast=podcasts.displayAllPodcasts();
                                       System.out.println("Enter Podcast Id you want to Play");
                                        String playpodcastid=scan.next();
                                        String podcastfilepath="";
                                       int playpodcastflag=0;
                                       for(Podcast x:playpodcast)
                                        {
                                         if(x.getPodcastid().equalsIgnoreCase(playpodcastid))
                                          {
                                            podcastfilepath=x.getFilepath();
                                            playpodcastflag=1;
                                         }
                                        }
                                        if(playpodcastflag==1)
                                       {
                                         PlayListImpl ploi=new PlayListImpl();
                                          ploi.readAudio(podcastfilepath,0);
                                       }
                                       else
                                      {
                                          System.out.println("Wrong Podcast Id");
                                          }
                                       break;
                            case 9:  playlists.createnewPlayList(u.getUsername());
                                     break;
                            case 10:  playlists.displayPlaylistDetails(u.getUsername());
                                     break;
                            case 11:  System.out.println("Enter PlayList You Want to delete");
                                     String playlistname=scan.next();
                                     playlists.deletePlaylist(u.getUsername(),playlistname);
                                     break;
                            case 12:  System.out.println("Enter PlayList You Want to Open");
                                     String openplaylistname=scan.next();
                                     playlists.openPlaylist(u.getUsername(),openplaylistname);
                                     break;
                            case 13:System.exit(0);
                            default:
                                System.out.println("Wrong Choice");
                        }
                    }

                }
                else
                {
                    System.out.println("Invalid Username or Password");
                    return "Invalid Username or Password";
                 //   status=true;
                }
           // }while(status);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return "Welocme "+username;
    }
}
