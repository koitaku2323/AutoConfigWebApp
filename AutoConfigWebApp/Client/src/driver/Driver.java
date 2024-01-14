package driver;

import java.io.*;
import java.net.*;
import util.*;
import model.Automobile;
import adapter.BuildAuto;

public class Driver {
    public static void main(String[] args) throws IOException {
        DefaultSocketClient dsc = new DefaultSocketClient("localhost", 5444);
        dsc.start();
        try {
            dsc.join();
        } catch (InterruptedException ie) {
        }
    }
}