package Client;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputHandler {
    private Scanner scan;

    public InputHandler() {
        scan = new Scanner(System.in);
    }

    public String getMessageFromUser() {
        System.out.println("Give a message: ");
        return scan.nextLine();
    }

    public Date getDateFromUser() throws TimePassedException {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Give a date in format yyyy-MM-dd HH:mm:ss ");
        String date = scan.nextLine();

        try {
            Date result = dateFormatter.parse(date);
            if (new Date().after(result)) {
                throw new TimePassedException("The time that you gave evaluated already.");
            }

            return result;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closeInputHandler(){
        scan.close();
    }
}
