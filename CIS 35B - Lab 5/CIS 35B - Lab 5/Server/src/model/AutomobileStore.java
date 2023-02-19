package model;

import java.util.*;

public class AutomobileStore {
    private LinkedHashMap<String, Automobile> autos;

    // default constructor
    public AutomobileStore() {
        autos = new LinkedHashMap();
    }

    // addAuto() inserts new Automobile object to autos
    public void addAuto(String modelName, Automobile autoObj) {
        autos.put(modelName, autoObj);
    }

    // deleteAuto() removes required Automobile object from autos
    public boolean deleteAuto(String modelName) {
        Automobile auto = autos.remove(modelName);
        if (auto == null)
            return false;
        else {
            return true;
        }
    }

    // getAuto() returns required Automobile object from autos
    public Automobile getAuto(String modelName) {
        Iterator<Automobile> iter = autos.values().iterator();
        while (iter.hasNext()) {
            Automobile auto = iter.next();
            if (auto.getModelName().equalsIgnoreCase(modelName)) {
                return auto;
            }
        }
        return null;
    }
}