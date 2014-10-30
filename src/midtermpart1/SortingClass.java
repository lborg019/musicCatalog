/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package midtermpart1;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.ArrayList;

/**
 *
 * @author Owner
 * 
 * This class gets the unsorted array of values passed, sorts the unsorted array
 * either by artist or album.
 * Each sort gets passed to a separate array (aByArtist or aByAlbum).
 * Tracks are also sorted.
 * 
 */
public class SortingClass
{   
    FileModule fm;
    AlbumComparator album_c  = new AlbumComparator();
    TrackComparator track_c  = new TrackComparator();
    
    ArrayList<AlbumModule> unsorted = new ArrayList<>();
    
    ArrayList<AlbumModule> aByArtist = new ArrayList<>();
    ArrayList<AlbumModule> aByAlbum = new ArrayList<>();

    //Constructor
    public SortingClass() throws FileNotFoundException
    {
        this.fm       = new FileModule();
        this.album_c  = new AlbumComparator();
        this.track_c  = new TrackComparator();

    }
    
    public void byArtist(ArrayList<AlbumModule> unsorted)
    {   
        Collections.sort(unsorted, album_c);
        Collections.sort(unsorted);
        
        for (AlbumModule unsorted1 : unsorted)
        {
            aByArtist.add(unsorted1);
        }
    }
    
    public void byAlbum(ArrayList<AlbumModule> unsorted)
    {
        Collections.sort(unsorted, album_c);
        
        for (AlbumModule unsorted1 : unsorted)
        {
            aByAlbum.add(unsorted1);
        }
    }
    
    public void tracks()
    {
        for (AlbumModule aByAlbum1 : aByAlbum)
        {
            Collections.sort(aByAlbum1.amTracks, track_c);
        }

        for (AlbumModule aByArtist1 : aByArtist)
        {
            Collections.sort(aByArtist1.amTracks, track_c);
        }

    }
    
    public void setUnsorted(ArrayList<AlbumModule> unsorted)
    {
        this.unsorted = unsorted;
    }
    
    public ArrayList<AlbumModule> getaByArtist()
    {
        return aByArtist;
    }

    public ArrayList<AlbumModule> getaByAlbum()
    {
        return aByAlbum;
    }
        
            
}
