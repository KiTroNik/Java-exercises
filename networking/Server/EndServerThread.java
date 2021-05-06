package Server;

import java.io.IOException;

public class EndServerThread implements Runnable{

    private Thread t;

    public EndServerThread() {
        t = new Thread(this);
        t.start();
    }

    public void run() {
        char c;
        while(true) {
            try {
                c = (char)System.in.read();
                if (c=='q'){
                    System.out.println("Server killed successfully.");
                    System.exit(1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
