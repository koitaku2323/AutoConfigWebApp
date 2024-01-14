package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import exception.AutoException;
import java.io.*;
import model.Automobile;

public class FileIO {
    // read a file and store its attributes in Automotive object
    public Automobile readFile(String fileName) throws AutoException {
        if (fileName.isEmpty()) {
            throw new AutoException(AutoException.ErrorNo.MISSING_FILE_NAME);
        }

        Automobile automotive = null;
        try {
            FileReader file = new FileReader(fileName);
            BufferedReader buff = new BufferedReader(file);
            boolean eof = false;

            // read and store base price in automotive object
            if (!eof) {
                String line = buff.readLine();
                if (line == null)
                    eof = true;
                else {
                    String[] retval = line.split(":");
                    float basePrice = Float.parseFloat(retval[1]);

                    // throw exception if base price is invalid
                    if (basePrice <= 0) {
                        throw new AutoException(
                                AutoException.ErrorNo.INVALID_BASE_PRICE);
                    }
                    automotive = new Automobile("Focus Wagon ZTW", basePrice);
                }
            }
            while (!eof) {
                final String line = buff.readLine();
                if (line == null)
                    eof = true;
                else {
                    final String[] lineParts = line.split(":");
                    String[] options = lineParts[1].split(";");
                    if (lineParts[0].isEmpty()) {
                        throw new AutoException(
                                AutoException.ErrorNo.MISSING_OPTIONSET_NAME);
                    }
                    if (options.length == 0) {
                        throw new AutoException(
                                AutoException.ErrorNo.MISSING_OPTIONSET_DATA);
                    }

                    automotive.addOptionSet(lineParts[0].trim(),
                            options.length);
                    for (int i = 0; i < options.length; ++i) {
                        String[] optionAttributes = options[i].split(",");
                        if (optionAttributes[0].isEmpty()) {
                            throw new AutoException(
                                    AutoException.ErrorNo.MISSING_OPTION_NAME);
                        }
                        if (optionAttributes[1].isEmpty()) {
                            throw new AutoException(
                                    AutoException.ErrorNo.MISSING_OPTION_PRICE);
                        }
                        automotive.addOption(lineParts[0].trim(),
                                optionAttributes[0].trim(),
                                Float.parseFloat(optionAttributes[1]));
                    }
                }
            }
            buff.close();
        } catch (IOException e) {
            System.out.println("Error -- " + e.toString());
        }
        return automotive;
    }

    // serialize Automotive object in a file
    public void serializeAuto(Automobile automotive) {
        try {
            FileOutputStream fileOut = new FileOutputStream("auto.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(automotive);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in auto.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    // de-serialize Automotive object from given file
    public Automobile deserializeAuto(String fileName) {
        Automobile automotive = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            automotive = (Automobile) in.readObject();
            in.close();
            fileIn.close();
            return automotive;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Automotive class not found");
            c.printStackTrace();
            return null;
        }
    }
    
    public Automobile CreateAutomobile(Properties properties) {
        String model = properties.getProperty("CarModel");
        float basePrice = Float.parseFloat(properties.getProperty("BasePrice"));
        Automobile automotive = new Automobile(model, basePrice);

        int numOptionSets = Integer
                .parseInt(properties.getProperty("OptionSetSize"));
        String alphabets = "0abcdefghijklmnopqrstuvwxyz";
        for (int i = 1; i <= numOptionSets; ++i) {
            String optionSetName = properties.getProperty("OptionSet" + i);
            int numOptions = Integer
                    .parseInt(properties.getProperty("OptionSet" + i + "Size"));
            automotive.addOptionSet(optionSetName, numOptions);

            for (int j = 1; j <= numOptions; ++j) {
                String optionName = properties
                        .getProperty("OptionName" + i + alphabets.charAt(j));
                float optionPrice = Float.parseFloat(properties
                        .getProperty("OptionPrice" + i + alphabets.charAt(j)));
                automotive.addOption(optionSetName, optionName, optionPrice);
            }
        }
        return automotive;
    }
}
//Credit to rs280 github user
//Credit to GreeksforGreeks
//Credit to Stack Overflow