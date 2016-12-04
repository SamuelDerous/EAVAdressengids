/**
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package creatie;
import java.util.*;
import java.io.*;
/**
 *
 * @author zenodotus
 */
public class Initialisatie {
    
    private final static Properties p = new Properties();
    private final static String bestand = System.getProperty("user.home") + 
            "/.EAVAdressengids/EAVAdressengids.ini";;
    static {
     try {
          p.load(new FileInputStream(bestand));
     }
     catch (IOException ex) {
         opslaan();
     }
    }


  public static String getGegeven(String gegeven) {
      String property = "";
      try{
          property = p.getProperty(gegeven);
      
      }
    catch (Exception e) {
      System.out.println(e);
      }
    finally {
        return property;
    }
    }
  
  public static void setGegeven(String eigenschap, String gegeven) {
      try {
          
          p.setProperty(eigenschap, gegeven);
         
      }
      catch (Exception e) {
          System.out.println(e);
      }
  }
  
  public static void opslaan() {
      try {
       p.store(new FileOutputStream(bestand), "nieuwe gegevens");
      }
      catch (Exception e) {
          System.out.println(e);
          System.out.println("Het bestand is niet gevonden!");
          System.out.println("Aanmaken bestand...");
         try {
            new File(System.getProperty("user.home") + "/.EAVAdressengids").mkdir();
            FileOutputStream out = new FileOutputStream(bestand);
            out.close();
            System.out.println("Bestand aangemaakt.");
         } catch(IOException ex) {
             ex.printStackTrace();
         }
      }
      
  }
}
