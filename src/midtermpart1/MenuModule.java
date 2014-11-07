/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package midtermpart1;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lukas Borges
 * 
 * This class runs the program's menu and also contains extra methods such as:
 *  Searching array by determined artist (searchArtist)
 *  Searching array by determined album (searchAlbum)
 *  Outputting the result (outputSearch)
 *  and Add album entry (addAlbum)
 * 
 */
public class MenuModule
{   
    SortingClass      sort;
    FileModule        fm;
    Scanner           scanner;
    AlbumComparator   albumC;
    ArrayList<String> addedTrackList;
    
    //Constructor
    public MenuModule() throws FileNotFoundException
    {
        this.sort           = new SortingClass();
        this.scanner        = new Scanner(System.in);
        this.albumC         = new AlbumComparator();
        this.fm             = new FileModule();
        this.addedTrackList = new ArrayList<>();
    }
    
    public void runMenu()
    {
        int    option;  //user option
        int    index;   //index of binary searches (unique entries)
        int[]  aIndex;  //bounds for binary search (repeated entries)
        
        fm.readFile(); //read text file
        
        //sort everything
        sort.setUnsorted(fm.catalog);   //pass array to other class
        sort.byArtist(sort.unsorted);   //sort it by artist
        sort.byAlbum(sort.unsorted);    //sort it by album
        sort.tracks();                  //sort tracks
        
        do 
        {    
            System.out.println("Menu:");
            System.out.println("1.Search by Album Title");
            System.out.println("2.Search by Artist");
            System.out.println("3.Add album to catalog");
            System.out.println("4.Quit");
            option = scanner.nextInt();
            
            switch (option)
            {
                case 1:  option = 1; //search by album
                
                    index = searchAlbum();//defining a method also calls it!
                    if(index < 0)
                    {
                        System.out.println("Album not found.\n");
                        break;
                    }
                    else
                        //0, 0 get passed because there are no bounds
                        outputSearch(option, index, 0, 0);
                    
                    break;
                    
                case 2: option = 2; //search by artist
                searchArtist();
                    break;
                
                case 3: option = 3; //add album to catalog
                
                    AlbumModule addedAlbum = addAlbum();
                    
                    fm.readFile();                  //read the file, pop. array
                    fm.addToCatalog(addedAlbum);    //add the extra to the array
                    
                    sort.setUnsorted(fm.catalog);   //pass array to other class
                    sort.byArtist(sort.unsorted);   //sort it by artist
                    sort.byAlbum(sort.unsorted);
                    sort.tracks();                  //sort tracks
                    
                    try
                    {
                        fm.addToFile(addedAlbum);       //add to external File
                    }
                    catch (IOException ex)
                    {
                        Logger.getLogger(MenuModule.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    break;
                    
                default: option = 4;
                    break;
            }
                        
        } while (option!=4);
    }
      
    public int searchAlbum()
    {
        int fl; //flag for binarySearch
        
        String strEntry;
        System.out.println("Please type in album name:");
        scanner.nextLine();
        strEntry = scanner.nextLine().toLowerCase().replaceAll("\\s", "_");
        
        //create an object with album name only
        AlbumModule albumTarget = new AlbumModule(null, strEntry, null);
        
        //search for this object (passing array, target and comparator)
        fl = Collections.binarySearch(sort.getaByAlbum(), albumTarget, albumC);
        
        return fl;
    }
    
    public String searchArtist()
    {
        
        System.out.println("Please type in artist's name:");
        String input = new Scanner(System.in).next().toLowerCase().replaceAll("\\s", "_");
        
        //create an object with artist name only            
        AlbumModule artistTarget = new AlbumModule(input, null, null);
        
        //search for this object (no comparator, using comparable instead):
        int index = Collections.binarySearch(sort.aByArtist, artistTarget);
        
            int firstIndex = index;
            while(artistTarget.compareTo(sort.aByArtist.get(firstIndex-1)) == 0){
                --firstIndex;
            }
            
            int lastIndex = index;
            while(artistTarget.compareTo(sort.aByArtist.get(lastIndex+1)) == 0){
                ++firstIndex;
            }
            
            String accumulator = "";
            for(AlbumModule s : sort.aByAlbum.subList(firstIndex, lastIndex+1)){
                accumulator += s;
            }
            
            return accumulator;
    }
    
    public void outputSearch(int option, int aIndex, int fIndex, int lIndex)
    {
        if(option == 1)
        {
            System.out.print(
            "\nArtist: " + sort.getaByAlbum().get(aIndex).amArtist +
            "\nAlbum:  " + sort.getaByAlbum().get(aIndex).amAlbum  +
            "\nTracks: " + sort.getaByAlbum().get(aIndex).amTracks +
            "\n");
        }
        else
        {
            for (int j = fIndex; j <= lIndex; j++)
            {
                System.out.print( 
                "\nArtist: " + sort.getaByArtist().get(j).amArtist +
                "\nAlbum: "  + sort.getaByArtist().get(j).amAlbum +
                "\nTracks: " + sort.getaByArtist().get(j).amTracks +
                "\n");
            }
        }
    }
    
    public AlbumModule addAlbum()
    {
        int option;
        AlbumModule addedModule;
         
        //artist
        System.out.println("Please enter the artist's name:");
        scanner.nextLine(); //clear buffer
        String addedArtist = scanner.nextLine().toLowerCase().replaceAll("\\s", "_");
        
        //album
        System.out.println("Please enter the album name:");
        String addedAlbum = scanner.nextLine().toLowerCase().replaceAll("\\s", "_");
        
        //tracks
        System.out.println("Please enter the track: ");
        String addedTracks = scanner.nextLine().toLowerCase().replaceAll("\\s", "_");
        addedTrackList.add(addedTracks);
        
        do
        {
            System.out.println("1.Add another song.\n2.Done");
            option = scanner.nextInt();
            
            switch (option)
            {
                case 1:  option = 1; //add another
                scanner.nextLine();
                System.out.println("Please enter the track: ");
                addedTracks = scanner.nextLine().toLowerCase().replaceAll("\\s", "_");
                addedTrackList.add(addedTracks);
                
                    break;
                
                default: option = 2;
                    break;
            }
        }
        while(option != 2);
        
        addedModule = new AlbumModule(addedArtist, addedAlbum, addedTrackList);
        return addedModule;
    }
}