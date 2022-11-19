package org.example.DAOImplClasses;

import org.example.DAOInterfaces.PlayListDetailsInterface;
import org.example.DBConnection;
import org.example.Models.PlaylistDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlayListDetailsImpl implements PlayListDetailsInterface {
    @Override
    public void addSong(String playlistid,String playlistname,String songid)
    {
      try
      {
          String songquery="select songname from Song where songid='"+songid+"';";
          Connection con=DBConnection.getConnection();
          PreparedStatement pst1=con.prepareStatement(songquery);
          ResultSet rs=pst1.executeQuery();
          rs.next();
          String songname=rs.getString(1);
          String query="insert into PlaylistDetails values('"+playlistid+"','"+songname+"','"+songid+"');";
          PreparedStatement pst=con.prepareStatement(query);
          pst.executeUpdate();
          System.out.println("Song Added");
      }
      catch(Exception e)
      {
          System.out.println(e);
      }
    }

    @Override
    public void addPodcast(String playlistid,String playlistname,String podcastid)
    {
      try
      {
          String podcastquery="select podcastname from Podcast where podcastid='"+podcastid+"';";
          Connection con=DBConnection.getConnection();
          PreparedStatement pst1=con.prepareStatement(podcastquery);
          ResultSet rs=pst1.executeQuery();
          rs.next();
          String podcastname=rs.getString(1);
          String query="insert into PlaylistDetails values('"+playlistid+"','"+podcastname+"','"+podcastid+"');";
          PreparedStatement pst=con.prepareStatement(query);
          pst.executeUpdate();
          System.out.println("Podcast Added");
      }
      catch(Exception e)
      {
          System.out.println(e);
      }
    }

    @Override
    public void deleteSong(String songid,String playlistid)
    {
      try
      {
          Connection con=DBConnection.getConnection();
          String query="delete from PlayListDetails where audioid='"+songid+"' and playlistid='"+playlistid+"';";
          PreparedStatement pst=con.prepareStatement(query);
          pst.executeUpdate();
          System.out.println("Song Deleted");
      }
      catch(Exception e)
      {
          System.out.println(e);
      }
    }

    @Override
    public void deletePodcast(String podcastid,String playlistid)
    {
      try
      {
          Connection con=DBConnection.getConnection();
          String query="delete from PlayListDetails where audioid='"+podcastid+"' and playlistid='"+playlistid+"';";
          PreparedStatement pst=con.prepareStatement(query);
          pst.executeUpdate();
          System.out.println("Podcast Deleted");
      }
      catch(Exception e)
      {
          System.out.println(e);
      }

    }

    @Override
    public List<PlaylistDetails> displayPlayListDetails(String playlistid)
    {
        List<PlaylistDetails> totalplaylistdetails=new ArrayList<>();
      try
      {
          String query="select * from PlayListDetails where playlistid='"+playlistid+"';";
          Connection con= DBConnection.getConnection();
          PreparedStatement pst=con.prepareStatement(query);
          ResultSet rs=pst.executeQuery();
          System.out.format(" %10s  %20s","Audioid","AudioName");
          System.out.println();
          while(rs.next())
          {
              PlaylistDetails pld=new PlaylistDetails(rs.getString(1),rs.getString(2), rs.getString(3));
              totalplaylistdetails.add(pld);
              System.out.format(" %10s  %20s",rs.getString(3),rs.getString(2));
              System.out.println();

          }
      }
      catch(Exception e)
      {
          System.out.println(e);
      }
      return totalplaylistdetails;
    }

    @Override
    public List<PlaylistDetails> allplayListDetails(String playlistid)
    {
        List<PlaylistDetails> details=new ArrayList<>();
        try
        {

            String query = "select * from PlayListDetails where playlistid='" + playlistid + "';";
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                PlaylistDetails p=new PlaylistDetails(rs.getString(1),rs.getString(2), rs.getString(3));
                details.add(p);
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return details;
    }


}
