/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package midtermpart1;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Lukas Borges
 * 
 * This class deals with text file operations. It is also responsible for
 * passing the values of the file to an array.
 * That is why this class also has methods that deal with adding the user entry
 * to catalog and text file.
 * 
 */
public class FileModule
{
    //variables
    File file;
    Scanner fileScan;
    String artist;
    String album;
    
    ArrayList<String>      tracks = new ArrayList<>();
    ArrayList<AlbumModule> catalog = new ArrayList<>();
            
    //Constructor
    public FileModule() throws FileNotFoundException
    {
        this.file = new File("catalog2.txt");
        this.fileScan = new Scanner(file);
    }
    
    public void openFile() throws FileNotFoundException
    {
        if(!file.exists())
        {
            System.out.println("File not found!");
            System.exit(1);
        }
        else
            System.out.println("File found!");
    }
    
    public void readFile()
    {   //try catch statement
        while(fileScan.hasNext())
        {
            artist = fileScan.next();
            album = fileScan.next();
            String _tr = fileScan.nextLine();
            Scanner parseLine = new Scanner(_tr);
            tracks = new ArrayList();  //new object each pass, clears previous
            while(parseLine.hasNext()) //scans the inline tracks
            {
                tracks.add(parseLine.next().toLowerCase());
            }//information is loaded.
            
            //information loaded is now pushed to ArrayList
            catalog.add(new AlbumModule(artist.toLowerCase(),
                                        album.toLowerCase(),
                                        tracks));
        }
    }
    
    //adds entry to catalog
    public void addToCatalog(AlbumModule addedAlbum)
    {
        catalog.add(addedAlbum);
    }
    
    //addes entry to text file
    public void addToFile(AlbumModule addedAlbum) throws IOException
    {   
        try(FileWriter fw = new FileWriter(file ,true)) //true to append
        {
            //write artist and album
            fw.write(addedAlbum.amArtist + " " +
                     addedAlbum.amAlbum  + " ");
            
            //since tracks are in an array, write them accordingly
            for (int i = 0; i < addedAlbum.amTracks.size(); i++)
            {
                if(i == addedAlbum.amTracks.size()-1)
                {
                    //the last track in the array gets printed with a new line
                    fw.write(addedAlbum.amTracks.get(i) + "\n");
                }
                else
                {
                    //all others are just separated by spaces
                    fw.write(addedAlbum.amTracks.get(i) + " ");
                }
            }
        }
    }
    
    public void closeFile()
    {
        fileScan.close();
    }
}