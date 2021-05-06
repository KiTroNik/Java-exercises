package Server;

import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerManager server = new ServerManager();
        server.start(6666);
        server.stop();
    }
}
