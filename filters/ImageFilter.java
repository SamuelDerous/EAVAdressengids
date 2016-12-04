/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author zenodotus
 */
public class ImageFilter extends FileFilter {
    
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        
        String extension = ImageBestanden.getExtension(f);
        if (extension != null) {
            if (extension.equals(ImageBestanden.tiff) ||
                    extension.equals(ImageBestanden.tif) ||
                    extension.equals(ImageBestanden.gif) ||
                    extension.equals(ImageBestanden.jpeg) ||
                    extension.equals(ImageBestanden.jpg) ||
                    extension.equals(ImageBestanden.png)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
    public String getDescription() {
        return "afbeeldingen";
    }
    
}
