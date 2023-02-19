package adapter;

public interface CreateAuto {
    // Given a text file name, buildAuto() builds an instance of Automobile.
    public void buildAuto(String filename);

    // printAuto() searches and prints the properties of a given Automobile.
    public void printAuto(String Modelname);
}