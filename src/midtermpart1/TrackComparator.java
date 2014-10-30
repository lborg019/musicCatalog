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
public class TrackComparator implements Comparator<String>
{
        @Override
        public int compare(String track1, String track2)
        {
            return track1.compareToIgnoreCase(track2);
        }
}
