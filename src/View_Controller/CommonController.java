package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class CommonController {

    public void handleCancelCommon(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm Cancellation");
        alert.setHeaderText("Data entered on this screen will not be saved");
        alert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> res = alert.showAndWait();
        if (res.isPresent() && res.get() == ButtonType.OK) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"))));
            stage.show();
        }
    }

    public void handleReturnHome(ActionEvent event) throws IOException {
        handleGoToView(event, "/View_Controller/MainScreen.fxml");
    }

    public void handleGoToView(ActionEvent event, String resourceName) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(resourceName))));
        stage.show();
    }

    public void noSelectionError(int selectionError) {
        if (selectionError == 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Selection");
            alert.setContentText("Either you must select an item or the table is empty!");
            alert.showAndWait();
        }
    }

    public static void partError(int errorNum, TextField field) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Add Part Error");
        alert.setHeaderText("Unable to add part");
        switch (errorNum) {
            case 1: {
                alert.setContentText("Company name is empty!");
                break;
            }
            case 2: {
                alert.setContentText("Must select either In-House or OutSourced radio button!");
                break;
            }
            case 3: {
                alert.setContentText("Number Format Error, number fields can't be empty or contain non-digit characters!");
                break;
            }
            case 4: {
                alert.setContentText("Name is empty!");
                break;
            }
            case 5: {
                alert.setContentText("Price must be greater than zero!");
                break;
            }
            case 6: {
                alert.setContentText("Inventory can't be less than min!");
                break;
            }
            case 7: {
                alert.setContentText("Inventory can't be greater than max!");
                break;
            }
            case 8: {
                alert.setContentText("Min can't be greater than or equal to max!");
                break;
            }
            case 9: {
                alert.setContentText("Min can't be less than or equal to zero!");
                break;
            }
            default: {
                alert.setContentText("Unknown error!");
                break;
            }
        }
        alert.showAndWait();
    }

    public static void productError(int errorNum, TextField field) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Add Product Error");
        alert.setHeaderText("Unable to add product");
        switch (errorNum) {
            case 1: {
                alert.setContentText("Product must have one or more associated parts!");
                break;
            }
            case 2: {
                alert.setContentText("Min can't be greater than or equal to max!");
                break;
            }
            case 3: {
                alert.setContentText("Number Format Error, number fields can't be empty or contain non-digit characters!");
                break;
            }
            case 4: {
                alert.setContentText("Name is empty!");
                break;
            }
            case 5: {
                alert.setContentText("Price must be greater than zero!");
                break;
            }
            case 6: {
                alert.setContentText("Inventory can't be less than min!");
                break;
            }
            case 7: {
                alert.setContentText("Inventory can't be greater than max!");
                break;
            }
            case 8: {
                alert.setContentText("Min can't be less than or equal to zero!");
                break;
            }
            default: {
                alert.setContentText("Unknown error!");
                break;
            }
        }
        alert.showAndWait();
    }
}