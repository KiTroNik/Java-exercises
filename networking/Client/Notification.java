package Client;

import java.io.Serializable;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Notification implements Serializable {
    private String message;
    private Date date;

    public Notification(String message, Date date){
        //InputHandler inputHandler = new InputHandler();
        //this.message = inputHandler.getMessageFromUser();
        //this.date = inputHandler.getDateFromUser();
        this.message = message;
        this.date = date;

        //inputHandler.closeInputHandler();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date time) {
        this.date = time;
    }
}
