package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import model.Client;
import model.Address;
import model.ClientBaseHandler;
import model.InvalidAmountException;

import javax.swing.text.html.Option;

public class MainSceneController implements Initializable {
    @FXML
    public ListView<Client> clientsListView;

    // settings for switching scene
    private Stage stage;
    private Scene scene;
    private Parent root;

    private ArrayList<Client> clients;
    private Client currentClient;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clients = ClientBaseHandler.readClientsFromFile();

        if (clients != null) {
            clientsListView.getItems().addAll(clients);
            clientsListView.getSelectionModel().selectedItemProperty().addListener((observableValue, client, t1) -> currentClient = clientsListView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    public void switchToAddAccountScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddAccountScene.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deleteCurrentClient(ActionEvent event) {
        if (currentClient != null) {
            Optional<ButtonType> confirmation = confirmationDialog();
            if (confirmation.get() == ButtonType.OK) {
                clients.remove(currentClient);
                clientsListView.getItems().remove(currentClient);
                ClientBaseHandler.saveClientsToFile(clients);
            }
        }
    }

    @FXML
    public void switchToDetailScene(ActionEvent event) throws IOException {
        if (currentClient != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsScene.fxml"));
            root = loader.load();
            DetailSceneController detailSceneController = loader.getController();
            detailSceneController.displayClient(currentClient);
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void depositMoney(ActionEvent event) {
        if (currentClient != null) {
            try {
                Optional<String> result = moneyDialog();
                Optional<ButtonType> confirmation = confirmationDialog();
                if (confirmation.get() == ButtonType.OK) {
                    currentClient.depositMoney(Double.parseDouble(result.get()));
                    clientsListView.getItems().clear();
                    clientsListView.getItems().addAll(clients);
                    ClientBaseHandler.saveClientsToFile(clients);
                }
            } catch (Exception e) {
                createInvalidAmountOfMoneyDialog();
            }
        }
    }

    @FXML
    public void payOutMoney(ActionEvent event) {
        if (currentClient != null) {
            try {
                Optional<String> result = moneyDialog();
                Optional<ButtonType> confirmation = confirmationDialog();
                if (confirmation.get() == ButtonType.OK) {
                    currentClient.payOutMoney(Double.parseDouble(result.get()));
                    clientsListView.getItems().clear();
                    clientsListView.getItems().addAll(clients);
                    ClientBaseHandler.saveClientsToFile(clients);
                }
            } catch (Exception e) {
                createInvalidAmountOfMoneyDialog();
            }
        }
    }

    private Optional<ButtonType> confirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdź");
        alert.setHeaderText("Potwierdź operację");
        alert.setContentText("Czy jesteś pewny, że chcesz to zrobić?");
        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }

    private Optional<String> moneyDialog() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Przelew");
        dialog.setHeaderText("Zmiana stanu konta");
        dialog.setContentText("Wprowadź kwotę:");

        Optional<String> result = dialog.showAndWait();
        return result;
    }

    private void createInvalidAmountOfMoneyDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Nieprawidłowa kwota");
        alert.setHeaderText("Wprowadziłeś nieprawidłową kwotę");
        alert.setContentText("Spróbuj ponownie");

        alert.showAndWait();
    }

    @FXML
    public void exitMenu(ActionEvent event) {
        Optional<ButtonType> confirmation = confirmationDialog();
        if (confirmation.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    @FXML
    public void aboutMenu(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("O programie");
        alert.setHeaderText("Program umożliwa zarządzanie systemem bankowym.");
        alert.showAndWait();
    }

    @FXML
    public void transferMoney(ActionEvent event) {
        if (currentClient != null && clients.size() >= 2) {
            try {
                ArrayList<Client> choiceClients = (ArrayList<Client>) clients.clone();
                choiceClients.remove(currentClient);
                ChoiceDialog<Client> dialog = new ChoiceDialog<>(choiceClients.get(0), choiceClients);
                dialog.setTitle("Transfer");
                dialog.setHeaderText("Transfer między " + currentClient.getName() + " " + currentClient.getSurname() + " a:");
                dialog.setContentText("Wybierz klienta:");
                Optional<Client> resultClient = dialog.showAndWait();
                if (resultClient.isPresent()) {
                    Optional<String> money = moneyDialog();
                    Optional<ButtonType> confirmation = confirmationDialog();
                    if (confirmation.get() == ButtonType.OK) {
                        currentClient.transferMoney(resultClient.get(), Double.parseDouble(money.get()));
                        clientsListView.getItems().clear();
                        clientsListView.getItems().addAll(clients);
                        ClientBaseHandler.saveClientsToFile(clients);
                    }
                }
            } catch (Exception e) {
                createInvalidAmountOfMoneyDialog();
            }
        } else {
            if (currentClient != null) {
                createInvalidTransferDialog();
            }
        }
    }

    private void createInvalidTransferDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Nieprawidłowa operacja");
        alert.setHeaderText("Transfer nie może się udać, ponieważ nie ma wystarczająco użytkowników w systemie.");
        alert.setContentText("Spróbuj ponownie");

        alert.showAndWait();
    }
}
