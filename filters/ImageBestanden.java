/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.File;

/**
 *
 * @author zenodotus
 */
public class ImageBestanden {
    
        public static final String jpeg = "jpeg";
        public static final String jpg = "jpg";
        public static final String gif = "gif";
        public static final String tiff = "tiff";
        public static final String tif = "tif";
        public static final String png = "png";
        
        public static String getExtension(File f) {
            String ext = null;
            String s = f.getName();
            int i = s.lastIndexOf(".");
            
            if (i > 0 && i < s.length() - 1) {
                ext = s.substring(i+1).toLowerCase();
            }
            return ext;
        }
        
    
}
