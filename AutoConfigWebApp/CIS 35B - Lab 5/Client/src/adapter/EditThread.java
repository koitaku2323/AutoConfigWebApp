package adapter;

public interface EditThread {
    // edit() applies the specified edit operation on the Automobile object with
    // model name inputs[0]
    
    // Op 1 : Updates Option name in synchronized manner
    // Op 2 : Updates Option price in synchronized manner
    // Op 3 : Updates Option name in a non-synchronized manner
    // Op 4 : Updates Option price in a non-synchronized manner
    
    // inputs must be of size 4.
    // inputs[0] : Name of Automobile Model
    // inputs[1] : Name of OptionSet
    // inputs[2] : Name of Option
    // inputs[3] : New Option name or price
    public void edit(int op, String[] inputs, boolean sync);
}