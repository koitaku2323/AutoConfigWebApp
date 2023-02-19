package model;

import java.io.*;
import exception.AutoException;
import model.OptionSet.Option;

import java.util.*;

public class Automobile implements Serializable {
    private String               modelName;
    private float                basePrice;
    private String               make;
    private ArrayList<OptionSet> opsetsList;
    private ArrayList<OptionSet> choice;

    // default constructor
    public Automobile() {
        modelName = "";
        basePrice = 0f;
        opsetsList = new ArrayList<OptionSet>();
        choice = new ArrayList<OptionSet>();
    }

    // parameterized constructor
    public Automobile(String modelName, float basePrice) {
        this.modelName = modelName;
        this.basePrice = basePrice;
        opsetsList = new ArrayList<OptionSet>();
        choice = new ArrayList<OptionSet>();
    }

    // getters
    public String getModelName() {
        return modelName;
    }

    public float getBasePrice() {
        return basePrice;
    }

    public OptionSet getOpsets(int i) {
        return opsetsList.get(i);
    }

    public String getMake() {
        return make;
    }

    // getOptionChoice() returns the choice name of the given OptionSet
    public String getOptionChoice(String optionSetName) {
        for (int i = 0; i < choice.size(); ++i) {
            OptionSet optset = choice.get(i);
            if (optset.getName().equalsIgnoreCase(optionSetName)) {
                return optset.getOptionChoice().getName();
            }
        }
        return null;
    }

    // getOptionChoicePrice() returns the choice price of the given OptionSet
    public int getOptionChoicePrice(String optionSetName) {
        for (int i = 0; i < choice.size(); ++i) {
            OptionSet optset = choice.get(i);
            if (optset.getName().equalsIgnoreCase(optionSetName)) {
                return (int) optset.getChoicePrice();
            }
        }
        return 0;
    }

    // getTotalPrice() returns the total choice price
    public int getTotalPrice() {
        float totalPrice = basePrice;
        for (int i = 0; i < choice.size(); ++i) {
            OptionSet optset = choice.get(i);
            totalPrice += optset.getChoicePrice();
        }
        return (int) totalPrice;
    }

    // setters
    public void setModelName(String name) {
        this.modelName = name;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public void setOpset(ArrayList opsets) {
        this.opsetsList = opsets;
    }

    public void setMake(String make) {
        this.make = make;
    }

    // setOptionChoice() sets user choice for given optionSet
    public void setOptionChoice(String optionSetName, String optionName) {
        for (int i = 0; i < opsetsList.size(); ++i) {
            OptionSet optset = opsetsList.get(i);
            if (optset.getName().equalsIgnoreCase(optionSetName)) {
                OptionSet.Option opt = optset.findOption(optionName);
                if (opt != null) {
                    optset.setOptionChoice(optionName);
                    choice.add(optset);
                }
                break;
            }
        }
    }

    // toString() converts buffered string to a string
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Automotive Name: ").append(modelName)
                .append("Base Price: ").append(basePrice);
        String str = stringBuffer.toString();
        return str;
    }

    // print() prints automotive object's attributes
    public void print() {
        System.out.println(toString());
        for (int i = 0; i < opsetsList.size(); ++i) {
            OptionSet optset = opsetsList.get(i);
            optset.print();
        }
    }

    // OptionSet() adds optionSet in the optionSet array
    public void addOptionSet(String str, int length) {
        OptionSet optset = new OptionSet(str, length);
        opsetsList.add(optset);
    }

    // addOption() adds option in the required OptionSet.
    public void addOption(String optionSetName, String optionName,
            float price) {
        for (int i = 0; i < opsetsList.size(); ++i) {
            OptionSet optset = opsetsList.get(i);
            OptionSet.Option opt = optset.new Option();
            if (optset.getName() == optionSetName) {
                opt.setName(optionName);
                opt.setPrice(price);
                optset.addOption(opt);
                break;
            }
        }
    }

    // findOptionSet() searches for the required optionSet using optionSetName
    // and returns OptionSet object if found else null.
    public OptionSet findOptionSet(String optionSetName) {
        for (int i = 0; i < opsetsList.size(); ++i) {
            OptionSet optset = opsetsList.get(i);
            if (optset.getName().equalsIgnoreCase(optionSetName)) {
                optset.print();
                return optset;
            }
        }
        return null;
    }

    // findOption() searches for the option in the required optionSet using
    // optionName and returns option object if found else null.
    public OptionSet.Option findOption(String optionSetName,
            String optionName) {
        for (int i = 0; i < opsetsList.size(); ++i) {
            OptionSet optset = opsetsList.get(i);
            if (optset.getName().equalsIgnoreCase(optionSetName)) {
                return optset.findOption(optionName);
            }
        }
        return null;
    }

    // deleteOptionSet() deletes the required optionset from the option set
    // array
    public boolean deleteOptionSet(String optionSetName) {
        OptionSet opset = findOptionSet(optionSetName);
        if (opset == null)
            return false;

        ArrayList newOptsetList = new ArrayList();
        OptionSet[] newOptset = new OptionSet[opsetsList.size() - 1];
        for (int i = 0; i < opsetsList.size() - 1; ++i) {
            OptionSet optset = opsetsList.get(i);
            if (optset != opset) {
                newOptsetList.set(i, optset);
            }
        }
        opsetsList = newOptsetList;
        return true;
    }

    // deleteOption() deletes the required option from the optionSetArray
    public boolean deleteOption(String optionSetName, String optionName) {
        OptionSet opset = findOptionSet(optionSetName);
        if (opset != null) {
            return opset.deleteOption(optionName);
        }
        return false;
    }

    // updateOptionSetName() searches for the required OptionSet and changes its
    // name when found in the array
    public void updateOptionSetName(String optionSetName, String newName) {
        for (int i = 0; i < opsetsList.size(); ++i) {
            OptionSet optset = opsetsList.get(i);
            if (optset.getName().equalsIgnoreCase(optionSetName)) {
                optset.setName(newName);
            }
        }
    }

    // updateOptionPrice() searches for the required Option in the OptionSet and
    // changes its name when found in the array.
    public void updateOptionPrice(String optionSetName, String optionName,
            float newprice) {
        for (int i = 0; i < opsetsList.size(); ++i) {
            OptionSet optset = opsetsList.get(i);
            if (optset.getName().equalsIgnoreCase(optionSetName)) {
                optset.updateOptionPrice(optionName, newprice);
            }
        }
    }

    // updateOptionName updates name of given Option
    public void updateOptionName(OptionSet.Option option, String newName) {
        option.setName(newName);
    }

    // updateOptionPrice updates price of given Option
    public void updateOptionPrice(OptionSet.Option option, float newPrice) {
        option.setPrice(newPrice);
    }

    // printOptionSet() prints the given OptionSet
    public boolean printOptionSet(String optSetName) {
        for (int i = 0; i < opsetsList.size(); ++i) {
            OptionSet optset = opsetsList.get(i);
            if (optset.getName().equalsIgnoreCase(optSetName)) {
                optset.print();
                return true;
            }
        }
        return false;
    }
}