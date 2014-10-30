/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package midtermpart1;
import java.util.ArrayList;

/**
 *
 * @author Owner
 */
public class AlbumModule implements Comparable<AlbumModule>
{
    String amAlbum;
    String amArtist;
    ArrayList<String> amTracks;
    
    public AlbumModule(String _artist, String _album, ArrayList<String> _tracks)
    {
        this.amArtist = _artist;
        this.amAlbum = _album;
        this.amTracks = _tracks;
    }
    
    public String getAlbum()
    {
        return amAlbum;
    }

    public String getArtist()
    {
        return amArtist;
    }

    public ArrayList<String> getTracks()
    {
        return amTracks;
    }
    
    @Override
    public int compareTo(AlbumModule obj)
    {
        return getArtist().compareToIgnoreCase(obj.getArtist());
    }
    
    @Override
    public String toString()
    {
        return "Artist: "   + amArtist + 
               "\nAlbum: "  + amAlbum  +
               "\nTracks: " + amTracks +
               "\n";
    }
}