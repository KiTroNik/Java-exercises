package Client;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Client {
    public static void main(String[] args) {
        ClientManager client = new ClientManager();
        InputHandler inputHandler = new InputHandler();
        try {
            client.startConnection("localhost", 6666);
            client.notificationThread();

            while(true) {
                String message = inputHandler.getMessageFromUser();
                Date date = inputHandler.getDateFromUser();
                Notification request = new Notification(message, date);
                client.sendNotification(request);
            }
        } catch (IOException | DateTimeParseException | TimePassedException e) {
            e.printStackTrace();
        }
    }
}
