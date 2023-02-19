package adapter;

import java.util.Set;

import util.Properties;

public interface CreateAuto {
    // Returns model names of Automobiles created so far.
    public Set<String> getModelNames();
    
    // Given a text file name, buildAuto() builds an instance of Automobile.
    public void buildAuto(String filename);

    // printAuto() searches and prints the properties of a given Automobile.
    public void printAuto(String Modelname);
}