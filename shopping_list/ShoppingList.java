

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

public class ShoppingList {

    private final FileHandler handler;
    private ArrayList<Category> products;

    public ShoppingList(String fileName) throws IOException {
        handler = new FileHandler(fileName);
        products = handler.readShoppingList();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("SHOPPING LIST\n");

        for (Category key : products) {
            result.append(key.getName()).append(" - ");
            for (String p : key.getProducts()) {
                result.append(p).append(",");
            }
            result.append("\n");
        }
        return result.toString();
    }

    public void printShoppingList() {
        if (products.isEmpty()) {
            System.out.println("Your list is empty. Change txt file or add something");
        } else {
            System.out.println(this.toString());
        }
    }

    public void addCategoryToList(String category) {
        products.add(new Category(category));
    }

    public void addProductToList(String category, String product) {
        for (Category key : products) {
            if (key.getName().equals(category)) {
                key.getProducts().add(product);
            }
            break;
        }
    }

    public void deleteCategory(String category) {
        for (Category key : products) {
            if (key.getName().equals(category)) {
                products.remove(key);
            }
            break;
        }
    }

    public void deleteProduct(String category, String product) {
        for (Category key : products) {
            if (key.getName().equals(category)) {
                key.getProducts().remove(product);
            }
            break;
        }
    }

    public void eraseList() {
        products.clear();
    }

    public boolean checkIfCategoryExists(String category) {
        for (Category key : products) {
            if (key.getName().equals(category)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfProductExists(String category, String product) {
        for (Category key : products) {
            if (key.getName().equals(category)) {
                return key.getProducts().contains(product);
            }
        }
        return false;
    }

    public void saveListToFile() throws IOException {
        handler.writeShoppingList(products);
    }
}
