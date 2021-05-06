import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;


public class FileHandler {
	public FileHandler(){}

   	public void writeToFile(String fileName, int value) throws IOException {
        Writer wr = new FileWriter(fileName);
        wr.write(String.valueOf(value));
        wr.flush();
        wr.close();
    }
}