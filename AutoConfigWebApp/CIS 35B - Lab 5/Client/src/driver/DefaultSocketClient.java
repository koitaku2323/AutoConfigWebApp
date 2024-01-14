package driver;

import java.net.*;

import model.Automobile;
import util.Properties;

import java.io.*;

public class DefaultSocketClient extends Thread
        implements SocketClientInterface {

    private PrintWriter        printWriter        = null;
    private BufferedReader     bufferedReader     = null;
    private ObjectInputStream  objectInputStream  = null;
    private ObjectOutputStream objectOutputStream = null;
    private Socket             socket;
    private String             strHost;
    private int                iPort;

    public DefaultSocketClient(String strHost, int iPort) {
        setPort(iPort);
        setHost(strHost);
    }// constructor

    public void run() {
        if (openConnection()) {
            try {
                handleSession();
            } catch (IOException ie) {
            }
            closeSession();
        }
    }// run

    public boolean openConnection() {
        try {
            socket = new Socket(strHost, iPort);
        } catch (IOException socketError) {
            return false;
        }
        try {
            bufferedReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void handleSession() throws IOException {
        BufferedReader systemInReader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("1. Upload Properties file.");
        System.out.println("2. Configure a car.");
        System.out.println("3. Exit.");
        System.out.println("Choose one of the above options (1, 2 or 3): ");
        String userChoice = systemInReader.readLine();
        while (userChoice.equals("1") || userChoice.equals("2")) {
            if (userChoice.equals("1")) {
                System.out.print("Please enter the name of properties file: ");
                String propertiesFile = systemInReader.readLine();

                Properties properties = new Properties();
                FileInputStream fis = new FileInputStream(propertiesFile);
                properties.load(fis);

                // Send Properties object to server.
                printWriter.println("SendingPropertiesObject");
                if (objectOutputStream == null) {
                    objectOutputStream = new ObjectOutputStream(
                            socket.getOutputStream());
                }
                objectOutputStream.writeObject(properties);
            } else if (userChoice.equals("2")) {
                // Get Automobile model names.
                printWriter.println("NeedAutomobileModelNames");
                String[] models = bufferedReader.readLine().split(",");
                for (int i = 0; i < models.length; ++i) {
                    System.out.println((i + 1) + ". " + models[i]);
                }

                System.out.println("Enter one of the above model names: ");
                String userModelChoice = systemInReader.readLine();
                boolean userChoiceVerified = false;
                for (int i = 0; i < models.length; ++i) {
                    if (userModelChoice.equalsIgnoreCase(models[i])) {
                        userChoiceVerified = true;
                        break;
                    }
                }
                if (!userChoiceVerified) {
                    System.out.print("Invalid selection. Please try again.");
                } else {
                    // Retrieve an Automobile object.
                    Automobile automobile = null;
                    printWriter
                            .println("NeedAutomobileObject:" + userModelChoice);
                    try {
                        if (objectInputStream == null) {
                            objectInputStream = new ObjectInputStream(
                                    socket.getInputStream());
                        }
                        automobile = (Automobile) objectInputStream
                                .readObject();
                        automobile.print();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    } catch (ClassNotFoundException exception) {
                        System.out.println("Properties class not found");
                        exception.printStackTrace();
                    }

                    for (int i = 0; i < 2; ++i) {
                        System.out.print(
                                "Enter an option set name that you want to choose: ");
                        String optionSetName = systemInReader.readLine();
                        System.out.print(
                                "Enter an option name that you want to choose: ");
                        String optionName = systemInReader.readLine();
                        automobile.setOptionChoice(optionSetName, optionName);
                    }
                    System.out.println("Final price of the Automobile: "
                            + automobile.getTotalPrice());
                }
            } else {
                System.out.print("Invalid selection. Please try again.");
            }
            System.out.println("1. Upload Properties file.");
            System.out.println("2. Configure a car.");
            System.out.println("3. Exit.");
            System.out.println("Choose one of the above options (1, 2 or 3): ");
            userChoice = systemInReader.readLine();
        }

        printWriter.println("bye");
        systemInReader.close();
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
            socket.close();
        } catch (IOException e) {
        }
    }

    public void setHost(String strHost) {
        this.strHost = strHost;
    }

    public void setPort(int iPort) {
        this.iPort = iPort;
    }

}// class DefaultSocketClient