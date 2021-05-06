package Client;

import java.io.*;
import java.net.Socket;

public class ClientManager {
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
        System.out.println("Connection established.");
    }

    public void sendNotification(Notification request) throws IOException {
        out.writeObject(request);
    }

    public void notificationThread() {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Notification response = (Notification) in.readObject();
                    System.out.println(response.getMessage());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
