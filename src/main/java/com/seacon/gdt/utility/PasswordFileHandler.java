package com.seacon.gdt.utility;

import com.seacon.gdt.main.Gdt;
import java.io.File;
import java.io.PrintWriter;
import java.net.URISyntaxException;

/**
 *
 * @author varsanyi.peter
 */
public class PasswordFileHandler {
    
    public static String getPasswordFilePath() throws URISyntaxException {
        String osName = System.getProperty("os.name");
        String retVal = Gdt.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath() + "password.txt";
        if (osName.toLowerCase().contains("windows")) {
            retVal = retVal.substring(1);  // cut the first / from string's beginning
//            if (retVal.contains(" ")) {
//                retVal = "\"" + retVal + "\"";
//            }
        }
        return retVal;
    }

    public static void createPasswordFile(String password) throws Exception {
        File tFile = new File(getPasswordFilePath());
        PrintWriter writer = new PrintWriter(tFile, "UTF-8");
        writer.println("AS_ADMIN_PASSWORD=" + password);
        writer.println("AS_ADMIN_ADMINPASSWORD=");
        writer.println("AS_ADMIN_USERPASSWORD=");
        writer.println("AS_ADMIN_MASTERPASSWORD=");       
        writer.close();
        writer = null;
        tFile = null;
    }
    
    public static void deletePasswordFile() throws URISyntaxException {
        File tFile = new File(getPasswordFilePath());
        if (tFile.exists()) {
            tFile.delete();
        }
    }
    
}
