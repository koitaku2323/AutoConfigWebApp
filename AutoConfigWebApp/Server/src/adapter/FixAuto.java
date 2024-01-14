package adapter;

import model.Automobile;

public interface FixAuto {
    // fix() fixes the error encountered and returns the fixed Automobile
    // object.
    public Automobile fix(int errno);
}
