package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Client;

import java.io.IOException;
import java.util.ArrayList;

public class DetailSceneController {
    @FXML
    public Label nameLabel;
    @FXML
    public Label surnameLabel;
    @FXML
    public Label peselLabel;
    @FXML
    public Label countryLabel;
    @FXML
    public Label streetLabel;
    @FXML
    public Label cityLabel;
    @FXML
    public Label postalCodeLabel;
    @FXML
    public Label moneyLabel;
    @FXML
    public Label idLabel;

    // settings for switching scene
    private Stage stage;
    private Scene scene;
    private Parent root;
    private ArrayList<Client> clients;

    public void displayClient(Client client) {
        nameLabel.setText("Imię: " + client.getName());
        surnameLabel.setText("Nazwisko: " + client.getSurname());
        peselLabel.setText("PESEL: " + String.valueOf(client.getPesel()));
        countryLabel.setText("Kraj: " + client.getAddress().getCountry());
        streetLabel.setText("Ulica: " + client.getAddress().getStreet());
        cityLabel.setText("Miasto: " + client.getAddress().getCity());
        postalCodeLabel.setText("Kod pocztowy: " + client.getAddress().getPostalCode());
        moneyLabel.setText("Ilość pieniędzy: " + String.valueOf(client.getMoney()) + " zł");
        idLabel.setText("Numer w systemie: " + String.valueOf(client.getId()));
    }

    @FXML
    public void backToMainScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
