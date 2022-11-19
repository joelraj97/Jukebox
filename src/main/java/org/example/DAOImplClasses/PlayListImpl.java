package org.example.DAOImplClasses;

import org.example.DAOInterfaces.PlaylistInterface;
import org.example.DBConnection;
import org.example.Models.Playlist;
import org.example.Models.PlaylistDetails;
import org.example.Models.Podcast;
import org.example.Models.Song;
import org.example.PlayAudio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PlayListImpl implements PlaylistInterface
{

    @Override
    public void createnewPlayList(String username)
    {
        Scanner scan=new Scanner(System.in);
        System.out.println("Enter Playlist Name");
        String playlistname=scan.next();
        int id=0;
        try
        {
            String query="select count(*) from PlayList;";
            Connection con= DBConnection.getConnection();
            PreparedStatement pst1=con.prepareStatement(query);
            ResultSet rs2=pst1.executeQuery(query);
            rs2.next();
            id= rs2.getInt(1);
            String playlistid="P"+(id+1);
            Playlist p=new Playlist(playlistid,playlistname,username);
            String playlistquery="insert into PlayList values('"+p.getPlaylistid()+"','"+p.getPLaylistName()+"','"+p.getUsername()+"');";
            PreparedStatement pst2=con.prepareStatement(playlistquery);
            pst2.executeUpdate();
            System.out.println("PlayList "+playlistname+" added");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    public boolean deletePlaylist(String username,String playlistname)
    {
      try
      {
          int flag=0;
          Connection con= DBConnection.getConnection();
          String checkquery="select playlistname from PlayList where username='"+username+"';";
          PreparedStatement pst2=con.prepareStatement(checkquery);
          ResultSet rs= pst2.executeQuery();
          while(rs.next())
          {
              if(rs.getString(1).equalsIgnoreCase(playlistname))
              {
                  flag=1;
                  break;
              }
          }
          if(flag==1)
          {
              String query = "delete from PlayList where username='" + username + "'and playlistname='" + playlistname + "';";
              PreparedStatement pst1 = con.prepareStatement(query);
              pst1.executeUpdate();
              System.out.println(playlistname+" deleted");

          }
          else
          {
              System.out.println("PlayList Not Found");
              return false;
          }
      }
      catch(Exception e)
      {
          System.out.println(e);
      }
      return true;
    }

    @Override
    public void openPlaylist(String username, String playlistname)
    {
        try
        {
            PlayListDetailsImpl pdi=new PlayListDetailsImpl();
            Scanner scan=new Scanner(System.in);
            int flag=0;
            Connection con =DBConnection.getConnection();
            String checkquery="select playlistname from PlayList where username='"+username+"';";
            PreparedStatement pst2=con.prepareStatement(checkquery);
            ResultSet rs= pst2.executeQuery();
            while(rs.next())
            {
                if(rs.getString(1).equalsIgnoreCase(playlistname))
                {
                    flag=1;
                    break;
                }
            }
            if(flag==1)
            {
                System.out.println(playlistname+" Playlist Opened");
                SongImpl song=new SongImpl();
                PodcastImpl podcast=new PodcastImpl();
                String playlistidquery="select playlistid from Playlist where username='"+username+"' and playlistname='"+playlistname+"';";
                PreparedStatement pst3=con.prepareStatement(playlistidquery);
                ResultSet rs2= pst3.executeQuery();
                String playlistid="";
                while(rs2.next())
                {
                    playlistid=rs2.getString(1);
                }
                boolean playliststatus=true;
                while(playliststatus)
                {
                    System.out.println("1.View All Audio Files \n2.Add Song \n3.Add Podcast \n4.Delete Song \n5.Delete Podcast \n6.Play Audio\n7.Play All Songs in PlayList\n8.Exit PlayList");
                    int playlistchoice=scan.nextInt();

                    switch (playlistchoice)
                    {
                        case 1: String displayplaylistdetailsqueries="select count(*) from PlaylistDetails where playlistid='"+playlistid+"';";
                                PreparedStatement pst10=con.prepareStatement(displayplaylistdetailsqueries);
                                ResultSet rs10=pst10.executeQuery();
                                rs10.next();
                                int count=rs10.getInt(1);
                                if(count==0)
                                {
                                    System.out.println("No Audio Files in PlayList");
                                }
                                else
                                {
                                    pdi.displayPlayListDetails(playlistid);
                                }

                                break;
                        case 2:
                                List<Song> allSongs=song.displayAllSongs();
                                System.out.println("Enter Song Id You Want to Add");
                                String songid=scan.next();
                                int songflag=0;
                                for(Song s:allSongs)
                                {
                                    if(songid.equalsIgnoreCase(s.getSongid()))
                                    {
                                        songflag=1;
                                        break;
                                    }
                                }
                                if(songflag==1)
                                {
                                  int alreadyavialablesongcheck=0;
                                  String playlistdetailsqueries="select count(*) from PlaylistDetails where playlistid='"+playlistid+"';";
                                  PreparedStatement pst11=con.prepareStatement(playlistdetailsqueries);
                                  ResultSet rs11=pst11.executeQuery();
                                  rs11.next();
                                  int allcount=rs11.getInt(1);
                                  if(allcount==0)
                                  {
                                      pdi.addSong(playlistid,playlistname,songid);
                                  }
                                  else
                                  {
                                      List<PlaylistDetails> pld=pdi.allplayListDetails(playlistid);
                                      for(PlaylistDetails x:pld)
                                      {
                                          if(x.getAudioid().equalsIgnoreCase(songid))
                                          {
                                              alreadyavialablesongcheck=1;
                                              break;
                                          }
                                      }
                                      if(alreadyavialablesongcheck==1)
                                      {
                                          System.out.println("The Audio is already Available in your Playlist");
                                      }
                                      else
                                      {
                                          pdi.addSong(playlistid,playlistname,songid);
                                      }
                                  }


                                }
                                else
                                {
                                    System.out.println("Invalid Song id");
                                }
                                break;
                        case 3:
                                List<Podcast> allPodcast=podcast.displayAllPodcasts();
                                System.out.println("Enter Podcast Id You Want to Add");
                                String podcastid=scan.next();
                                 int podcastflag=0;
                                 for(Podcast p:allPodcast)
                                 {
                                   if(podcastid.equalsIgnoreCase(p.getPodcastid()))
                                   {
                                     podcastflag=1;
                                     break;
                                    }
                                 }
                                  if(podcastflag==1)
                                  {
                                      String alltheplaylistdetailsqueries="select count(*) from PlaylistDetails where playlistid='"+playlistid+"';";
                                      PreparedStatement pst12=con.prepareStatement(alltheplaylistdetailsqueries);
                                      ResultSet rs12=pst12.executeQuery();
                                      rs12.next();
                                      int allthecount=rs12.getInt(1);
                                      if(allthecount==0)
                                      {
                                          pdi.addPodcast(playlistid,playlistname,podcastid);
                                      }
                                      else
                                      {
                                          int alreadyavialablepodcastcheck=0;
                                          List<PlaylistDetails> ple=pdi.allplayListDetails(playlistid);
                                          for(PlaylistDetails x:ple)
                                          {
                                              if(x.getAudioid().equalsIgnoreCase(podcastid))
                                              {
                                                  alreadyavialablepodcastcheck=1;
                                                  break;
                                              }
                                          }
                                          if(alreadyavialablepodcastcheck==1)
                                          {
                                              System.out.println("The Audio is already Available in your Playlist");
                                          }
                                          else
                                          {
                                              pdi.addPodcast(playlistid,playlistname,podcastid);
                                          }

                                      }

                                }
                                else
                                {
                                  System.out.println("Invalid Podcast id");
                                 }
                                 break;
                        case 4:  String playlistdeletioncheckquery="select count(*) from PlaylistDetails where playlistid='"+playlistid+"';";
                                 PreparedStatement pst13=con.prepareStatement(playlistdeletioncheckquery);
                                 ResultSet rs13=pst13.executeQuery();
                                 rs13.next();
                                 int deletioncount=rs13.getInt(1);
                                 if(deletioncount==0)
                                 {
                                     System.out.println("Nothing to Delete");
                                 }
                                 else
                                 {
                                     pdi.displayPlayListDetails(playlistid);
                                     System.out.println("Enter Song Id you want to Delete");
                                     String deletesongid=scan.next();
                                     List<PlaylistDetails> plc=pdi.allplayListDetails(playlistid);
                                     int checkingavailabletodelete=0;
                                     for(PlaylistDetails x:plc) {
                                         if (x.getAudioid().equalsIgnoreCase(deletesongid))
                                         {
                                             checkingavailabletodelete=1;
                                             break;
                                         }

                                     }
                                     if(checkingavailabletodelete==1)
                                     {
                                         pdi.deleteSong(deletesongid,playlistid);
                                     }
                                     else
                                     {
                                         System.out.println("Wrong SongId");
                                     }
                                 }

                                 break;
                        case 5:   String podcastdeletioncheckquery="select count(*) from PlaylistDetails where playlistid='"+playlistid+"';";
                                  PreparedStatement pst14=con.prepareStatement(podcastdeletioncheckquery);
                                  ResultSet rs14=pst14.executeQuery();
                                  rs14.next();
                                  int podcastdeletioncount=rs14.getInt(1);
                                  if(podcastdeletioncount==0)
                                  {
                                      System.out.println("Nothing to Delete");
                                  }
                                  else
                                  {
                                      pdi.displayPlayListDetails(playlistid);
                                      System.out.println("Enter Podcast Id you want to delete");
                                      String deletepodcastid=scan.next();
                                      List<PlaylistDetails> plg=pdi.allplayListDetails(playlistid);
                                      int checkingavailabletodeletepodcast=0;
                                      for(PlaylistDetails x:plg) {
                                          if (x.getAudioid().equalsIgnoreCase(deletepodcastid))
                                          {
                                              checkingavailabletodeletepodcast=1;
                                              break;
                                          }

                                      }
                                      if(checkingavailabletodeletepodcast==1)
                                      {
                                          pdi.deletePodcast(deletepodcastid,playlistid);
                                      }
                                      else
                                      {
                                          System.out.println("Wrong Podcasr=tId");
                                      }
                                  }

                                 break;
                        case 6:  PlayListImpl pl=new PlayListImpl();
                                 String songplayingcheck="select count(*) from PlaylistDetails where playlistid='"+playlistid+"';";
                                 PreparedStatement ps20=con.prepareStatement(songplayingcheck);
                                 ResultSet rs20=ps20.executeQuery();
                                 rs20.next();
                                 int playingcount=rs20.getInt(1);
                                 if(playingcount==0)
                                 {
                                     System.out.println("No Audio to PLay in this PlayList");
                                 }
                                 else
                                 {
                                     pdi.displayPlayListDetails(playlistid);
                                     System.out.println("Enter audio id you want to play");
                                     String playingaudio=scan.next();
                                     int playingflag=0;
                                     List<PlaylistDetails> plk=pdi.allplayListDetails(playlistid);
                                     for(PlaylistDetails x:plk) {
                                         if (x.getAudioid().equalsIgnoreCase(playingaudio))
                                         {
                                             playingflag=1;
                                             break;
                                         }

                                     }
                                     if(playingflag==1)
                                     {
                                         if(playingaudio.charAt(0)=='s'||playingaudio.charAt(0)=='S')
                                         {
                                             List<Song> allthesongdetails=song.allSongDetails();
                                             String filepath="";
                                             for(Song x:allthesongdetails)
                                             {
                                                 if(x.getSongid().equalsIgnoreCase(playingaudio))
                                                 {
                                                     filepath=x.getFilepath();
                                                     break;
                                                 }
                                             }
                                             System.out.println(filepath);
                                             pl.readAudio(filepath,2);
                                         }
                                         else if(playingaudio.charAt(0)=='p'||playingaudio.charAt(0)=='P')
                                         {
                                            List<Podcast> allthepodcastdetails=podcast.allPodcastDetails();
                                            String podcastfilepath="";
                                            for(Podcast x:allthepodcastdetails)
                                            {
                                                if(x.getPodcastid().equalsIgnoreCase(playingaudio))
                                                {
                                                    podcastfilepath=x.getFilepath();
                                                    break;
                                                }
                                            }
                                             System.out.println(podcastfilepath);
                                            pl.readAudio(podcastfilepath,2);
                                         }
                                         else
                                         {
                                             System.out.println("invalid Audio");
                                         }
                                     }
                                     else
                                     {
                                         System.out.println("Entered Audio Not in PlayList");
                                     }

                                 }

                                 break;
                        case 8:  playliststatus=false;
                                 break;
                        case 7:   PlayListImpl pli=new PlayListImpl();
                                  String checkingplaylistdetails="select count(*) from PlaylistDetails where playlistid='"+playlistid+"';";
                                  PreparedStatement ps21=con.prepareStatement(checkingplaylistdetails);
                                  ResultSet rs21=ps21.executeQuery();
                                  rs21.next();
                                  int countsss=rs21.getInt(1);
                                  if(countsss==0)
                                   {
                                      System.out.println("No Audio to PLay in this PlayList");
                                  }
                                  else
                                  {
                                      List<String> audioids=new ArrayList<>();
                                      String allsongsplayquery="Select audioid from playlistDetails where playlistid='"+playlistid+"';";
                                      PreparedStatement ps22=con.prepareStatement(allsongsplayquery);
                                      ResultSet rs22=ps22.executeQuery();
                                      while(rs22.next())
                                      {
                                          audioids.add(rs22.getString(1));
                                      }
                                      List<String> allfilepathinPlaylist=new ArrayList<>();
                                      for(String x:audioids)
                                      {
                                          if(x.charAt(0)=='s'||x.charAt(0)=='S')
                                          {
                                              List<Song> thesongdetails=song.allSongDetails();
                                              String allfilepath="";
                                              for(Song y:thesongdetails)
                                              {
                                                  if(y.getSongid().equalsIgnoreCase(x))
                                                  {
                                                      allfilepath=y.getFilepath();
                                                      allfilepathinPlaylist.add(allfilepath);
                                                      break;
                                                  }
                                              }
                                          }
                                          else
                                          {
                                              List<Podcast> thepodcastdetails=podcast.allPodcastDetails();
                                              String thepodcastfilepath="";
                                              for(Podcast k:thepodcastdetails)
                                              {
                                                  if(k.getPodcastid().equalsIgnoreCase(x))
                                                  {
                                                      thepodcastfilepath=k.getFilepath();
                                                      allfilepathinPlaylist.add(thepodcastfilepath);
                                                      break;
                                                  }
                                              }
                                          }
                                      }
                                      for(String x:allfilepathinPlaylist)
                                      {
                                          System.out.println(x);
                                      }
                                      pli.readmultipleaudios(allfilepathinPlaylist);
                                  }
                                  break;
                        default:
                                 System.out.println("Wrong Choice");
                    }
                }
            }
            else
            {
                System.out.println("PlayList Not Found");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    public List<String> displayPlaylistDetails(String username)
    {
        List<String> allplaylistdetails=new ArrayList<>();
        try {
            String query = "Select playlistname from playlist where username='"+username+"';";
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                allplaylistdetails.add(rs.getString(1));
                System.out.println(rs.getString(1));
            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return allplaylistdetails;
    }


    public void readAudio(String audioSrcFile, int count)
        {
        PlayAudio playAudio = new PlayAudio();
        int c = 0;
        count = count-1;
        try {

            playAudio.playAudio1(audioSrcFile,count);

            playAudio.play();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("1. pause");
                System.out.println("2. resume");
                System.out.println("3. restart");
                System.out.println("4. stop");

                if (c == 4) {
                    break;
                }

                if (scanner.hasNextInt()) {
                    c = scanner.nextInt();
                } else {
                    break;
                }
                switch (c) {
                    case 1:
                        playAudio.pause();
                        break;
                    case 2:
                        playAudio.resumeAudio();
                        break;
                    case 3:
                        playAudio.restart();
                        break;
                    case 4:
                        playAudio.stop();
                        break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Error with playing sound." + ex);
        }
    }
    public void readmultipleaudios(List<String> allsongs)
    {
        int totalsize=allsongs.size()-1;
        Scanner scan=new Scanner(System.in);
        PlayAudio playAudio = new PlayAudio();
        int c = 0;
        try
        {
            Random rand = new Random();

            // Generate random integers in range 0 to 999
            int random = rand.nextInt(totalsize+1);
            System.out.println("1.Start Playing From Beginning of Playlist \n2.Randomly from Playlist");
            int option=scan.nextInt();
            int counter=0;
            if(option==1)
            {
                playAudio.playAudio1(allsongs.get(0),0);
                playAudio.play();

            }
            else
            {
                playAudio.playAudio1(allsongs.get(random),0);
                playAudio.play();
                counter=random;
            }

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("1. pause");
                System.out.println("2. resume");
                System.out.println("3. restart");
                System.out.println("4. next");
                System.out.println("5. previous");
                System.out.println("6. shuffle");
                System.out.println("7. stop");

                if (c == 7) {
                    break;
                }

                if (scanner.hasNextInt()) {
                    c = scanner.nextInt();
                } else {
                    break;
                }
                switch (c) {
                    case 1:
                        playAudio.pause();
                        break;
                    case 2:
                        playAudio.resumeAudio();
                        break;
                    case 3:
                        playAudio.restart();
                        break;
                    case 4:counter=counter+1;
                        if(counter<=totalsize) {
                            playAudio.stop();
                            playAudio.playAudio1(allsongs.get(counter), 0);
                            playAudio.play();
                        }
                        else
                        {
                            counter=counter-1;
                        }
                        break;
                    case 5:
                        counter=counter-1;
                        if(counter>=0) {
                            playAudio.stop();
                            playAudio.playAudio1(allsongs.get(counter), 0);
                            playAudio.play();
                        }
                        else
                        {
                            counter=counter+1;
                        }
                        break;
                    case 6:int randoms = rand.nextInt(totalsize+1);
                           counter=randoms;
                          playAudio.stop();
                          playAudio.playAudio1(allsongs.get(randoms), 0);
                            playAudio.play();
                            break;
                    case 7:
                        playAudio.stop();
                        break;

                }
            }
        } catch (Exception ex) {
            System.out.println("Error with playing sound." + ex);
        }

    }






}
