import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserHandler {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        boolean done = false;
        while(!done) {
            try {
                System.out.println("Please add two vectors with the same length.");
                
                MyVector first = readVector(scan);
                MyVector second = readVector(scan);
                int sum = first.addVectors(second);

                FileHandler fh = new FileHandler();
                fh.writeToFile("result.txt", sum);

                done = true;
            } catch (VectorsNotSameLengthException e) {
                System.out.println("Length of first vector is " + e.getFirstLength() + " and the second " + e.getSecondLength());
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

        scan.close();

    }

    public static MyVector readVector(Scanner scan){
        System.out.println("Type the vector:");
        int vector = 0;

        boolean done = false;
        while(!done) {
            try {
                vector = scan.nextInt();
                done = true;
            } catch (InputMismatchException e) {
                scan.nextLine();
                System.out.println("Please type proper value: ");
            }
        }
        return new MyVector(vector);
    }
}
