package driver;

import java.io.IOException;

public interface SocketClientInterface {
    boolean openConnection();
    void handleSession() throws IOException;
    void closeSession();
}