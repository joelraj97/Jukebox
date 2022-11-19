package org.example.DAOImplClasses;

import org.example.DAOInterfaces.PodcastInterface;
import org.example.DBConnection;
import org.example.Models.Podcast;
import org.example.Models.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.Date;
import java.util.List;

public class PodcastImpl implements PodcastInterface
{

    @Override
    public List<Podcast> displayAllPodcasts()
    {

        List<Podcast> allpodcasts=new ArrayList<>();
        try
        {
            Connection con = DBConnection.getConnection();
            String query = "select * from Podcast;";
            PreparedStatement pst1 = con.prepareStatement(query);
            ResultSet rs2 = pst1.executeQuery(query);
            while(rs2.next())
            {
                String podcastid= rs2.getString(1);
                String podcastname=rs2.getString(2);
                String speaker=rs2.getString(3);
                int episodes=rs2.getInt(4);
                String duration=rs2.getString(5);
                Date releasedate=rs2.getDate(6);
                String filepath=rs2.getString(7);
                Podcast p=new Podcast(podcastid,podcastname,speaker,episodes,duration,releasedate,filepath);
                allpodcasts.add(p);
            }
            System.out.format(" %10s  %20s  %20s  %20s %20s %20s","PodcastId","PodcastName","Speaker","Episodes","Duration","Release Date");
            System.out.println();
            for(Podcast x:allpodcasts)
            {
                System.out.format(" %10s  %20s  %20s  %20s %20s",x.getPodcastid(),x.getPodcastname(),x.getSpeaker(),x.getEpisodes(),x.getDuration(),x.getReleasedate());
                System.out.println();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return allpodcasts;
    }

    @Override
    public Podcast searchPodcast(String podcastname)
    {
        Podcast p=new Podcast();
        try {
            Connection con = DBConnection.getConnection();
            String query = "select * from Podcast;";
            PreparedStatement pst1 = con.prepareStatement(query);
            ResultSet rs2 = pst1.executeQuery(query);
            int flag=0;
            while(rs2.next())
            {
                if(rs2.getString(2).equalsIgnoreCase(podcastname))
                {

                    p.setPodcastid(rs2.getString(1));
                    p.setPodcastname(rs2.getString(2));
                    p.setSpeaker(rs2.getString(3));
                    p.setEpisodes(rs2.getInt(4));
                    p.setDuration(rs2.getString(5));
                    p.setReleasedate(rs2.getDate(6));
                    p.setFilepath(rs2.getString(7));
                    flag=1;
                    break;
                }
            }
            if(flag==1)
            {
                System.out.println("Song Found");
                System.out.format(" %10s  %20s  %20s  %20s %20s %20s","Podcastid","PodcastName","Speaker","Episodes","Duration","Release Date");
                System.out.println();
                System.out.format(" %10s  %20s  %20s  %20s %20s %20s ",p.getPodcastid(), p.getPodcastname(),p.getSpeaker(),p.getEpisodes(),p.getDuration(),p.getReleasedate());
                System.out.println();

            }
            else
            {
                System.out.println("Podcast Not Found");
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return p;
    }

    @Override
    public List<Podcast> allPodcastDetails()
    {
        List<Podcast> allpodcasts=new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String query = "select * from Podcast;";
            PreparedStatement pst1 = con.prepareStatement(query);
            ResultSet rs2 = pst1.executeQuery(query);
            while (rs2.next()) {
                String podcastid = rs2.getString(1);
                String podcastname = rs2.getString(2);
                String speaker = rs2.getString(3);
                int episodes = rs2.getInt(4);
                String duration=rs2.getString(5);
                Date releasedate=rs2.getDate(6);
                String filepath = rs2.getString(7);
                Podcast p = new Podcast(podcastid, podcastname, speaker, episodes, duration,releasedate,filepath);
                allpodcasts.add(p);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return allpodcasts;
    }
}
