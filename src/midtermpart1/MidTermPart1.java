/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package midtermpart1;
import java.io.FileNotFoundException;

/**
 *
 * @author Lukas Borges
 */
public class MidTermPart1
{

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    
    public static void main(String[] args) throws FileNotFoundException
    {
        FileModule fm = new FileModule();
        MenuModule mm = new MenuModule();
        //fm.openFile();
        mm.runMenu();
        fm.closeFile();
    }
    
}
