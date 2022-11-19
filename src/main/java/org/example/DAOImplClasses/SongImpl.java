package org.example.DAOImplClasses;

import org.example.DAOInterfaces.SongInterface;
import org.example.DBConnection;
import org.example.Models.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SongImpl implements SongInterface
{

    @Override
    public List<Song> displayAllSongs()
    {
        List<Song> allsongs=new ArrayList<>();
        try
        {
            Connection con = DBConnection.getConnection();
            String query = "select * from Song;";
            PreparedStatement pst1 = con.prepareStatement(query);
            ResultSet rs2 = pst1.executeQuery(query);
            while(rs2.next())
            {
               String songid= rs2.getString(1);
               String songname=rs2.getString(2);
               String artist=rs2.getString(3);
               String genre=rs2.getString(4);
               String duration=rs2.getString(5);
               String filepath=rs2.getString(6);
               Song s=new Song(songid,songname,artist,genre,duration,filepath);
               allsongs.add(s);
            }
            System.out.format(" %10s  %20s  %20s  %20s %20s","Songid","SongName","Artist","Genre","Duration");
            System.out.println();
            for(Song x:allsongs)
            {
                System.out.format(" %10s  %20s  %20s  %20s %20s",x.getSongid(),x.getSongname(),x.getArtist(),x.getGenre(),x.getDuration());
                System.out.println();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return allsongs;
    }

    @Override
    public List<Song> sortSongs(String category)
    {
        List<Song> allsongs=new ArrayList<>();
        try
        {
            Connection con = DBConnection.getConnection();
            String query = "select * from Song;";
            PreparedStatement pst1 = con.prepareStatement(query);
            ResultSet rs2 = pst1.executeQuery(query);
            while (rs2.next())
            {
                String songid = rs2.getString(1);
                String songname = rs2.getString(2);
                String artist = rs2.getString(3);
                String genre = rs2.getString(4);
                String duration=rs2.getString(5);
                String filepath = rs2.getString(6);
                Song s = new Song(songid, songname, artist, genre, duration,filepath);
                allsongs.add(s);
            }
            if(category.equalsIgnoreCase("Songname"))
            {
                Collections.sort(allsongs,(obj1,obj2)->(int)obj1.getSongname().compareToIgnoreCase(obj2.getSongname()));
                System.out.println("Sort based on SongnName");
                System.out.format(" %10s  %20s  %20s  %20s %20s  ","Songid","SongName","Artist","Genre","Duration");
                System.out.println();
                for(Song x:allsongs)
                {
                    System.out.format(" %10s  %20s  %20s  %20s %20s",x.getSongid(),x.getSongname(),x.getArtist(),x.getGenre(),x.getDuration());
                    System.out.println();
                }

            }
            else if(category.equalsIgnoreCase("Genre"))
            {
                Collections.sort(allsongs,(obj1,obj2)->(int)obj1.getGenre().compareToIgnoreCase(obj2.getGenre()));
                System.out.println("Sort based on SongnName");
                System.out.format(" %10s  %20s  %20s  %20s  %20s ","Songid","SongName","Artist","Genre","Duration");
                System.out.println();
                for(Song x:allsongs)
                {
                    System.out.format(" %10s  %20s  %20s  %20s %20s",x.getSongid(),x.getSongname(),x.getArtist(),x.getGenre(),x.getDuration());
                    System.out.println();

                }
            }
            else
            {
                Collections.sort(allsongs,(obj1,obj2)->(int)obj1.getArtist().compareToIgnoreCase(obj2.getArtist()));
                System.out.println("Sort based on SongnName");
                System.out.format(" %10s  %20s  %20s  %20s %20s","Songid","SongName","Artist","Genre","Duration");
                System.out.println();
                for(Song x:allsongs)
                {
                    System.out.format(" %10s  %20s  %20s  %20s %20s ",x.getSongid(),x.getSongname(),x.getArtist(),x.getGenre(),x.getDuration());
                    System.out.println();
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return allsongs;
    }

    @Override
    public Song searchSong(String songname)
    {
        Song s=new Song();
        try {
            Connection con = DBConnection.getConnection();
            String query = "select * from Song;";
            PreparedStatement pst1 = con.prepareStatement(query);
            ResultSet rs2 = pst1.executeQuery(query);
            int flag=0;
            while(rs2.next())
            {
               if(rs2.getString(2).equalsIgnoreCase(songname))
               {

                   s.setSongid(rs2.getString(1));
                   s.setSongname(rs2.getString(2));
                   s.setArtist(rs2.getString(3));
                   s.setGenre(rs2.getString(4));
                   s.setDuration(rs2.getString(5));
                   s.setFilepath(rs2.getString(6));
                   flag=1;
                   break;
               }
            }
            if(flag==1)
            {
                System.out.println("Song Found");
                System.out.format(" %10s  %20s  %20s  %20s %20s ","Songid","SongName","Artist","Genre","Duration");
                System.out.println();
                System.out.format(" %10s  %20s  %20s  %20s %20s ",s.getSongid(),s.getSongname(),s.getArtist(),s.getGenre(),s.getDuration());
                System.out.println();

            }
            else
            {
                System.out.println("Song Not Found");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return s;
    }

    @Override
    public List<Song> allSongDetails()
    {
        List<Song> allsongs=new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String query = "select * from Song;";
            PreparedStatement pst1 = con.prepareStatement(query);
            ResultSet rs2 = pst1.executeQuery(query);
            while (rs2.next()) {
                String songid = rs2.getString(1);
                String songname = rs2.getString(2);
                String artist = rs2.getString(3);
                String genre = rs2.getString(4);
                String duration = rs2.getString(5);
                String filepath=rs2.getString(6);
                Song s = new Song(songid, songname, artist, genre,duration,filepath);
                allsongs.add(s);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return allsongs;

    }

    @Override
    public List<Song> songCategory(String category,String categoryvalue)
    {
        List<Song> songcategories = new ArrayList<>();
        try {
            Scanner scan=new Scanner(System.in);

            Connection con=DBConnection.getConnection();
            if (category.equalsIgnoreCase("Artist"))
            {
               // System.out.println("Enter the artist name you want to Search");
                //String artist=scan.next();
              String query="select count(*) from Song where artist='"+categoryvalue+"';";
              PreparedStatement pst=con.prepareStatement(query);
              ResultSet rs=pst.executeQuery();
              rs.next();
              int count=rs.getInt(1);
              if(count==0)
              {
                  System.out.println("No Songs available for Artist "+categoryvalue);
              }
              else
              {
                  String queries="select * from song where artist='"+categoryvalue+"';";
                  PreparedStatement pst1=con.prepareStatement(queries);
                  ResultSet rs1=pst1.executeQuery();

                  while(rs1.next())
                  {
                      String songid = rs1.getString(1);
                      String songname = rs1.getString(2);
                      String artist = rs1.getString(3);
                      String genre = rs1.getString(4);
                      String duration = rs1.getString(5);
                      String filepath=rs1.getString(6);
                      Song s = new Song(songid, songname, artist, genre,duration,filepath);
                      songcategories.add(s);
                  }
                  System.out.format(" %10s  %20s  %20s  %20s %20s ","Songid","SongName","Artist","Genre","Duration");
                  System.out.println();
                  for(Song x:songcategories)
                  {
                      System.out.format(" %10s  %20s  %20s  %20s %20s ",x.getSongid(),x.getSongname(),x.getArtist(),x.getGenre(),x.getDuration());
                      System.out.println();
                  }
              }
            }
            else
            {
                String query3="select count(*) from Song where genre='"+categoryvalue+"';";
                PreparedStatement pst3=con.prepareStatement(query3);
                ResultSet rs3=pst3.executeQuery();
                rs3.next();
                int count1=rs3.getInt(1);
                if(count1==0)
                {
                    System.out.println("No Songs available for Genre "+categoryvalue);
                }
                else
                {
                    String query4="select * from Song where genre='"+categoryvalue+"';";
                    PreparedStatement pst7=con.prepareStatement(query4);
                    ResultSet rs7=pst7.executeQuery();
                    while(rs7.next())
                    {
                        String songid = rs7.getString(1);
                        String songname = rs7.getString(2);
                        String artist = rs7.getString(3);
                        String genre = rs7.getString(4);
                        String duration = rs7.getString(5);
                        String filepath=rs7.getString(6);
                        Song s = new Song(songid, songname, artist, genre,duration,filepath);
                        songcategories.add(s);
                    }
                    System.out.format(" %10s  %20s  %20s  %20s %20s ","Songid","SongName","Artist","Genre","Duration");
                    System.out.println();
                    for(Song x:songcategories)
                    {
                        System.out.format(" %10s  %20s  %20s  %20s %20s ",x.getSongid(),x.getSongname(),x.getArtist(),x.getGenre(),x.getDuration());
                        System.out.println();
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return songcategories;
    }

}
