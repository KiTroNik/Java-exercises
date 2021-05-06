package view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Address;
import model.Client;
import model.ClientBaseHandler;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddAccountSceneController implements Initializable {
    @FXML
    public TextField name;
    @FXML
    public Button saveButton;
    @FXML
    public Button cancelButton;
    @FXML
    public TextField money;
    @FXML
    public TextField postalCode;
    @FXML
    public TextField city;
    @FXML
    public TextField street;
    @FXML
    public TextField country;
    @FXML
    public TextField pesel;
    @FXML
    public TextField surname;
    @FXML

    // settings for switching scene
    private Stage stage;
    private Scene scene;
    private Parent root;
    private ArrayList<Client> clients;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clients = ClientBaseHandler.readClientsFromFile();
    }

    @FXML
    public void switchToMainScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void addClient(ActionEvent event) {
        try {
            String message = validateData();
            if (!message.equals("")) throw new Exception(message);
            Address address = new Address(country.getText(), street.getText(), city.getText(), postalCode.getText());
            int id = clients == null ? 1 : clients.size() + 1;
            Client newClient = new Client(id, name.getText(), surname.getText(), pesel.getText(), address, Double.parseDouble(money.getText()));
            if (clients == null) clients = new ArrayList<>();
            clients.add(newClient);
            ClientBaseHandler.saveClientsToFile(clients);
            switchToMainScene(event);
        } catch (Exception e) {
            createInvalidDataDialog(e.getMessage());
        }
    }

    private void createInvalidDataDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błędne dane");
        alert.setHeaderText("Wprowadziłeś błędne dane w polach:\n" + message);
        alert.setContentText("Spróbuj ponownie");

        alert.showAndWait();
    }

    private String validateData() {
        String result = "";

        if (name.getText().length() <= 1) {
            result += "Imię\n";
        }

        if (surname.getText().length() <= 1) {
            result += "Nazwisko\n";
        }

        if (pesel.getText().length() != 11) {
            result += "Pesel\n";
        }

        if (country.getText().length() < 1) {
            result += "Kraj\n";
        }

        if (street.getText().length() < 1) {
            result += "Ulica\n";
        }

        if (city.getText().length() < 1) {
            result += "Miasto\n";
        }

        if (postalCode.getText().length() != 6) {
            result += "Kod pocztowy\n";
        }

        try {
            if (Double.parseDouble(money.getText()) <= 0) {
                result += "Pieniądze\n";
            }
        } catch (NumberFormatException e) {
            result += "Pieniądze\n";
        }

        return result;
    }
}
