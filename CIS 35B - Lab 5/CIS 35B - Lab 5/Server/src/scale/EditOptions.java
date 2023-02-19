package scale;

import adapter.ProxyAutomobile;
import model.Automobile;

public class EditOptions extends ProxyAutomobile implements Runnable {
    // Op 1 : Updates Option name in synchronized manner
    // Op 2 : Updates Option price in synchronized manner
    // Op 3 : Updates Option name in a non-synchronized manner
    // Op 4 : Updates Option price in a non-synchronized manner
    private int      op;
    
    // inputs must be of size 4.
    // inputs[0] : Name of Automobile Model
    // inputs[1] : Name of OptionSet
    // inputs[2] : Name of Option
    // inputs[3] : New Option name or price
    private String[] inputs;
    private Thread   t;

    // Constructor.
    public EditOptions(int op, String[] inputs) {
        this.op = op;
        this.inputs = inputs;
        t = new Thread(this);
        t.start();
    }

    public void run() {
        Automobile autoObj = getAuto(inputs[0]);
        if (autoObj == null)
            return;

        // Apply the edit operation on autoObj.
        EditOptionInThread editHelper = new EditOptionInThread(autoObj);
        switch (op) {
            case 1:
                editHelper.updateNameSynchronized(inputs);
                break;

            case 2:
                editHelper.updatePriceSynchronized(inputs);
                break;

            case 3:
                editHelper.updateName(inputs);
                break;

            case 4:
                editHelper.updatePrice(inputs);
                break;
            default:
        }
    }
}