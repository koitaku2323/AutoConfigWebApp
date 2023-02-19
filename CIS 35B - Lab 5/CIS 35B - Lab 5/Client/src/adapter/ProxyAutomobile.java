package adapter;

import java.util.LinkedHashMap;

import exception.AutoException;
import model.*;
import util.*;
import scale.EditOptions;

public abstract class ProxyAutomobile {
    private static LinkedHashMap<String, Automobile> autos = new LinkedHashMap<String, Automobile>();

    // getAuto() returns the Automobile object corresponding to the given model
    // name.
    public static synchronized Automobile getAuto(String modelname) {
        return autos.get(modelname);
    }

    // Given a text file name, buildAuto() builds an instance of Automobile.
    public synchronized void buildAuto(String filename) {
        Automobile autoObj = null;
        try {
            FileIO fileIO = new FileIO();
            autoObj = fileIO.readFile(filename);
        } catch (AutoException ae) {
            autoObj = ae.fix();
        }
        autos.put(autoObj.getModelName(), autoObj);
    }

    // printAuto() searches and prints the properties of a given Automobile.
    public void printAuto(String modelname) {
        System.out.println("Properties of " + modelname + " are:");

        Automobile autoObj = getAuto(modelname);
        if (autoObj == null)
            return;
        autoObj.print();
    }

    // updateOptionSetName() searches the model for a given OptionSet and sets
    // the name of OptionSet to newName.
    public void updateOptionSetName(String modelname, String optionSetName,
            String newName) {
        Automobile autoObj = getAuto(modelname);
        if (autoObj == null)
            return;
        autoObj.updateOptionSetName(optionSetName, newName);
    }

    // updateOptionPrice() searches the Model for a given OptionSet and Option
    // name, and sets the price to newPrice.
    public void updateOptionPrice(String modelname, String optionSetName,
            String optionName, float newprice) {
        Automobile autoObj = getAuto(modelname);
        if (autoObj == null)
            return;
        autoObj.updateOptionPrice(optionSetName, optionName, newprice);
    }

    // fix() fixes the error encountered and returns the fixed Automobile
    // object.
    public Automobile fix(int errno) {
        AutoException ae = new AutoException(errno);
        return ae.fix();
    }

    // edit() applies the specified edit operation on the Automobile object with
    // model name inputs[0].
    public void edit(int op, String[] inputs, boolean sync) {
        if (op < 1 || op > 2) {
            System.out.println(
                    "Op code for edit operation should be in [1, 2] range.");
            return;
        }
        if (!sync) {
            // Op codes for non-synchronized operation are in [3, 4] range.
            op += 2;
        }

        // Create new object of EditOptions which will start a new thread to
        // perform the given edit operation.
        new EditOptions(op, inputs);
    }
}