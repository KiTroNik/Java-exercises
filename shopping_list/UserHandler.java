

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserHandler {

    private ShoppingList shoppingList;
    private Scanner scan;
    private boolean running;

    public UserHandler(String fileName) throws IOException {
        shoppingList = new ShoppingList(fileName);
    }

    public void printOptions() {
        System.out.println("""
                MENU
                1 - Add product
                2 - Delete product
                3 - Add category
                4 - Delete category
                5 - Erase shopping list
                6 - Save shopping list to file
                Type something else to exit without saving""");
    }

    public void startInteractionWithUser() throws IOException {
        running = true;
        scan = new Scanner(System.in);

        while (running) {
            shoppingList.printShoppingList();;
            printOptions();
            try {
                int userAnswer = scan.nextInt();
                scan.nextLine();
                handleInput(userAnswer);
            } catch (InputMismatchException e) {
                running = false;
            }
        }
        scan.close();
    }

    private void handleInput(int userAnswer) throws IOException {
        switch (userAnswer) {
            case 1 -> handleAddProduct();
            case 2 -> handleDeleteProduct();
            case 3 -> handleAddCategory();
            case 4 -> handleDeleteCategory();
            case 5 -> handleEraseShoppingList();
            case 6 -> handleSaveListToFile();
            default -> running = false;
        }
    }

    private void handleAddProduct() {
        System.out.println("Give the category: ");
        String category = scan.nextLine();
        if (shoppingList.checkIfCategoryExists(category)) {
            System.out.println("Give the product which you want to add: ");
            String product = scan.nextLine();
            shoppingList.addProductToList(category, product);
        } else {
            System.out.println("There is no such category.");
        }
    }

    private void handleDeleteProduct() {
        System.out.println("Give the category: ");
        String category = scan.nextLine();
        if (shoppingList.checkIfCategoryExists(category)) {
            System.out.println("Give the product which you want to remove: ");
            String product = scan.nextLine();
            if (shoppingList.checkIfProductExists(category, product)) {
                shoppingList.deleteProduct(category, product);
            } else {
                System.out.println("There is no such product.");
            }
        } else {
            System.out.println("There is no such category.");
        }
    }

    private void handleAddCategory() {
        System.out.println("Give the category: ");
        String category = scan.nextLine();
        shoppingList.addCategoryToList(category);
    }

    private void handleDeleteCategory() {
        System.out.println("Give the category: ");
        String category = scan.nextLine();
        if (shoppingList.checkIfCategoryExists(category)) {
            shoppingList.deleteCategory(category);
        } else {
            System.out.println("There is no such category.");
        }
    }

    private void handleEraseShoppingList() {
        shoppingList.eraseList();
    }

    private void handleSaveListToFile() throws IOException{
        shoppingList.saveListToFile();
    }

    public static void main(String[] args) {
        try {
            UserHandler handler = new UserHandler("shoppingList.txt");
            handler.startInteractionWithUser();
        } catch (IOException e) {
            System.out.println("Something wen wrong with file I/O. Please try again.");
        }
    }
}
