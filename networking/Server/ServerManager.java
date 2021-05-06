package Server;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerManager {
    private ServerSocket serverSocket;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        new EndServerThread();
        while(true) {
            new ClientHandlerThread(serverSocket.accept()).start();
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
    }
}
