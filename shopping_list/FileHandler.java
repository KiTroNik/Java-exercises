
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileHandler {

    private final String pathToShoppingList;

    public FileHandler(String fileName) throws IOException {
        this.pathToShoppingList = System.getProperty("user.dir") + "/" + fileName;

        if (!Files.exists(Paths.get(pathToShoppingList))) {
            File newFile = new File(pathToShoppingList);
            newFile.createNewFile();
        }
    }

    public ArrayList<Category> readShoppingList() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(pathToShoppingList));
        ArrayList<Category> result = new ArrayList<>();

        String line = reader.readLine();
        while (line != null) {
            Category e = new Category(getNameOfCategory(line));
            parseLine(e, line);
            result.add(e);
            line = reader.readLine();
        }

        reader.close();
        return result;
    }

    private String getNameOfCategory(String line) {
        return (line.split("-"))[0];
    }

    private void parseLine(Category shoppingList, String line) {
        String category = (line.split("-"))[0];
        String[] products = (line.substring(line.indexOf(category) + category.length()+1)).split(",");
        for (String p : products) {
            shoppingList.getProducts().add(p);
        }
    }

    public void writeShoppingList(ArrayList<Category> shoppingList) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(pathToShoppingList));

        for (Category key : shoppingList) {
            writer.write(createLine(key));
            writer.newLine();
        }

        writer.close();
    }

    private String createLine(Category shoppingList) {
        StringBuilder result = new StringBuilder(shoppingList.getName() + "-");
        for (String p : shoppingList.getProducts()) {
            result.append(p).append(",");
        }
        return result.toString();
    }
}
