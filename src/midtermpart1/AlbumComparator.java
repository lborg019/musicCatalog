/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package midtermpart1;
import java.util.Comparator;

/**
 *
 * @author Owner
 */
public class AlbumComparator implements Comparator<AlbumModule>
{
    @Override
    public int compare(AlbumModule o1, AlbumModule o2)
    {
        return o1.amAlbum.compareTo(o2.getAlbum());
    }
}
