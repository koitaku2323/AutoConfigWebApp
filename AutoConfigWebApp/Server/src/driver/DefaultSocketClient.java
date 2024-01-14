package driver;

import java.net.*;
import java.util.Set;

import adapter.BuildAuto;
import model.Automobile;
import util.Properties;

import java.io.*;

public class DefaultSocketClient extends Thread
        implements SocketClientInterface {

    private BuildAuto buildAuto = null;
    private PrintWriter        printWriter        = null;
    private BufferedReader     bufferedReader     = null;
    private ObjectInputStream  objectInputStream  = null;
    private ObjectOutputStream objectOutputStream = null;
    private ServerSocket       serverSocket       = null;
    private Socket             clientSocket       = null;

    public DefaultSocketClient(BuildAuto bAuto) {
        buildAuto = bAuto;
    }// constructor

    public void run() {
        if (acceptConnection()) {
            try {
                handleSession();
            } catch (IOException ie) {
            }
            closeSession();
        }
    }// run

    public boolean acceptConnection() {
        try {
            serverSocket = new ServerSocket(5444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 5444.");
            System.exit(1);
        }

        try {
            clientSocket = serverSocket.accept();
            bufferedReader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public void handleSession() throws IOException {
        String command = bufferedReader.readLine();
        System.out.println("Command: " + command);
        while (!command.equalsIgnoreCase("bye")) {
            if (command.equalsIgnoreCase("NeedAutomobileModelNames")) {
                Set<String> models = buildAuto.getModelNames();
                printWriter.println(String.join(",", models));
            } else if (command.startsWith("NeedAutomobileObject")) {
                String[] retval = command.split(":");
                String model = retval[1];

                Automobile automobile = buildAuto.getAuto(model);
                if (objectOutputStream == null) {
                    objectOutputStream = new ObjectOutputStream(
                            clientSocket.getOutputStream());
                }
                objectOutputStream.writeObject(automobile);
            } else if (command.equalsIgnoreCase("SendingPropertiesObject")) {
                try {
                    if (objectInputStream == null) {
                        objectInputStream = new ObjectInputStream(
                                clientSocket.getInputStream());
                    }
                    Properties properties = (Properties) objectInputStream
                            .readObject();
                    buildAuto.buildAuto(properties);
                } catch (IOException i) {
                    i.printStackTrace();
                } catch (ClassNotFoundException c) {
                    System.out.println("Properties class not found");
                    c.printStackTrace();
                }
            } else {
                System.out.println("Unknown command: " + command);
            }

            if (clientSocket.isClosed()) {
                System.out.println("client socket is closed.");
                break;
            }
            command = bufferedReader.readLine();
            System.out.println("Command: " + command);
        }
    }

    public void sendOutput(String strOutput) {
        printWriter.println(strOutput);
    }

    public void handleInput(String strInput) {
        System.out.println(strInput);
    }

    public void closeSession() {
        try {
            printWriter = null;
            bufferedReader = null;
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
        }
    }

}// class DefaultSocketClient