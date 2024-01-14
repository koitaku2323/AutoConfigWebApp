package driver;

import java.io.IOException;

public interface SocketClientInterface {
    boolean acceptConnection();
    void handleSession() throws IOException;
    void closeSession();
}