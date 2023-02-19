package exception;

import model.Automobile;
import util.FileIO;

public class FixErrors {
    // fixHelper() creates an Automobile object using defaultFile.txt
    private Automobile fixHelper() {
        Automobile autoObj = null;
        try {
            FileIO fileIO = new FileIO();
            autoObj = fileIO.readFile("defaultFile.txt");
        } catch (AutoException ae) {
            System.out.println("Error reading defaultFile.txt");
            System.exit(0);
        }
        return autoObj;
    }
    
    public Automobile fixInvalidBasePrice() {
        return fixHelper();
    }

    public Automobile fixMissingOptionSetName() {
        return fixHelper();
    }
    
    public Automobile fixMissingOptionSetData() {
        return fixHelper();
    }
    
    public Automobile fixMissingOptionName() {
        return fixHelper();
    }
    
    public Automobile fixMissingOptionPrice() {
        return fixHelper();
    }
    
    public Automobile fixMissingFileName() {
        return fixHelper();
    }
}