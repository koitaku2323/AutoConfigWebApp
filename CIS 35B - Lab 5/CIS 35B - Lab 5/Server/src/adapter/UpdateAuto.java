package adapter;

public interface UpdateAuto {
    // updateOptionSetName() searches the Model for a given OptionSet and sets
    // the name of OptionSet to newName.
    public void updateOptionSetName(String Modelname, String OptionSetname,
            String newName);

    // updateOptionPrice() searches the Model for a given OptionSet and Option
    // name, and sets the price to newPrice.
    public void updateOptionPrice(String modelname, String optionSetName,
            String optionName, float newprice);
}