package Server;

import Client.Notification;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class ClientHandlerThread extends Thread {
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Timer timer;

    public ClientHandlerThread(Socket socket) throws IOException {
        clientSocket = socket;
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
        timer = new Timer();
    }

    public void run() {
        try {
            while (true) {
                try {
                    Notification request = (Notification) in.readObject();
                    if (request == null) break;
                    System.out.println("New notification");

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                out.writeObject(request);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Notification has been sent");
                        }
                    }, request.getDate());

                } catch (EOFException e) {
                    System.out.println("Client closed");
                    break;
                }
            }

            closeConnection();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
