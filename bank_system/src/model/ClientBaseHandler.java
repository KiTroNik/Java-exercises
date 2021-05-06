package model;

import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class ClientBaseHandler {
    public static void saveClientsToFile(ArrayList<Client> clients) {
        try {
            FileOutputStream fos = new FileOutputStream("t.tmp");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(clients);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Client> readClientsFromFile() {
        try {
            FileInputStream fis = new FileInputStream("t.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Client> clients = (ArrayList<Client>) ois.readObject();
            ois.close();
            return clients;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
