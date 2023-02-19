package util;

import java.io.*;
import java.util.*;

public class Properties implements Serializable {
    private HashMap<String, String> hashMap;

    // Constructor
    public Properties() {
        hashMap = new HashMap<String, String>();
    }

    public void load(FileInputStream fileIn) {
        // read a file and store its attributes in Automotive object
        try {
            BufferedReader buffReader = new BufferedReader(
                    new InputStreamReader(fileIn));
            boolean eof = false;

            while (!eof) {
                // read and store file data in hashMap object
                String line = buffReader.readLine();
                if (line == null)
                    eof = true;
                else {
                    String[] retval = line.split("=");
                    hashMap.put(retval[0], retval[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error -- " + e.toString());
        }
    }

    public String getProperty(String propertyName) {
        return hashMap.get(propertyName);
    }
}