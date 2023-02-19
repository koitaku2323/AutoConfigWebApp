package scale;

import model.Automobile;
import model.OptionSet;

// helper class for ops methods
public class EditOptionInThread {
    private Automobile autoObj;

    // Constructor.
    public EditOptionInThread(Automobile auto) {
        autoObj = auto;
    }

    // updateNameSynchronized() updates the name of given option in autoObj.
    // This method is thread-safe.
    public void updateNameSynchronized(String[] inputs) {
        OptionSet.Option option = autoObj.findOption(inputs[1], inputs[2]);
        if (option == null) {
            return;
        }
        synchronized (option) {
            autoObj.updateOptionName(option, inputs[3]);
        }
    }

    // updatePriceSynchronized() updates the price of given option in autoObj.
    // This method is thread-safe.
    public synchronized void updatePriceSynchronized(String[] inputs) {
        OptionSet.Option option = autoObj.findOption(inputs[1], inputs[2]);
        if (option == null) {
            return;
        }
        synchronized (option) {
            float newPrice = Float.valueOf(inputs[3]);
            autoObj.updateOptionPrice(option, newPrice);
        }
    }

    // updateName() updates the name of given option in autoObj.
    // This method is not thread-safe.
    public void updateName(String[] inputs) {
        OptionSet.Option option = autoObj.findOption(inputs[1], inputs[2]);
        if (option == null) {
            return;
        }
        autoObj.updateOptionName(option, inputs[3]);
    }

    // updatePrice() updates the price of given option in autoObj.
    // This method is not thread-safe.
    public synchronized void updatePrice(String[] inputs) {
        OptionSet.Option option = autoObj.findOption(inputs[1], inputs[2]);
        if (option == null) {
            return;
        }
        float newPrice = Float.valueOf(inputs[3]);
        autoObj.updateOptionPrice(option, newPrice);
    }
}