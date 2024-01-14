package driver;

import model.Automobile;
import util.Properties;

import java.net.*;
import java.util.Set;
import java.io.*;

import adapter.BuildAuto;

public class Driver {
    public static void main(String[] args) throws IOException {
        BuildAuto buildAuto = new BuildAuto();
        DefaultSocketClient dsc = new DefaultSocketClient(buildAuto);
        dsc.start();
        try {
            dsc.join();
        } catch (InterruptedException ie) {
        }
    }
}