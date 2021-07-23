package View_Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPartController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Label machineIdLbl;

    @FXML
    private TextField idTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField invTxt;

    @FXML
    private TextField priceCostTxt;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField machineIdTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private RadioButton inHouseRBtn;

    @FXML
    private ToggleGroup addPartRadioBtn;

    @FXML
    private RadioButton outsourcedRBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    private boolean outsourced;

    private CommonController commonController = new CommonController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idTxt.setText(Integer.toString(Part.getNextId()));
    }

    @FXML
    void handleCancelAddPart(ActionEvent event) throws IOException {
        commonController.handleCancelCommon(event);
    }

    @FXML
    public void handleRadioInHouse(ActionEvent event) {
        outsourced = false;
        machineIdLbl.setText("Machine ID");
        inHouseRBtn.setSelected(true);
        machineIdTxt.setPromptText("Machine ID");
    }

    @FXML
    public void handleRadioOutsourced(ActionEvent event) {
        outsourced = true;
        machineIdLbl.setText("Company Name");
        outsourcedRBtn.setSelected(true);
        machineIdTxt.setPromptText("Company Name");
    }

    @FXML
    void handleSaveAddPart(ActionEvent event) throws IOException {
        Part part = null;
        try {
            if (outsourced) {
                part = new OutsourcedPart(
                        Integer.parseInt(idTxt.getText()),
                        nameTxt.getText(),
                        Double.parseDouble(priceCostTxt.getText()),
                        Integer.parseInt(invTxt.getText()),
                        Integer.parseInt(minTxt.getText()),
                        Integer.parseInt(maxTxt.getText()),
                        machineIdTxt.getText()
                );
                if (Integer.parseInt(minTxt.getText()) >= Integer.parseInt(maxTxt.getText())) {
                    commonController.partError(8, minTxt);
                    return;
                }
                if (Integer.parseInt(minTxt.getText()) <= 0) {
                    commonController.partError(9, minTxt);
                    return;
                }
                if (Integer.parseInt(invTxt.getText()) < Integer.parseInt(minTxt.getText())) {
                    commonController.partError(6, invTxt);
                    return;
                }
                if (Integer.parseInt(invTxt.getText()) > Integer.parseInt(maxTxt.getText())) {
                    commonController.partError(7, invTxt);
                    return;
                }
                if ((nameTxt.getText().length() < 1)) {
                    commonController.partError(4, nameTxt);
                    return;
                }
                if ((machineIdTxt.getText().length() < 1)) {
                    commonController.partError(1, machineIdTxt);
                    return;
                }
                if (Double.parseDouble(priceCostTxt.getText()) <= 0) {
                    commonController.partError(5, priceCostTxt);
                    return;
                }
                if (!outsourcedRBtn.isSelected()) {
                    commonController.partError(2, null);
                    return;
                }
            } else {
                part = new InHousePart(
                        Integer.parseInt(idTxt.getText()),
                        nameTxt.getText(),
                        Double.parseDouble(priceCostTxt.getText()),
                        Integer.parseInt(invTxt.getText()),
                        Integer.parseInt(minTxt.getText()),
                        Integer.parseInt(maxTxt.getText()),
                        Integer.parseInt(machineIdTxt.getText())
                );
                if (Integer.parseInt(minTxt.getText()) >= Integer.parseInt(maxTxt.getText())) {
                    commonController.partError(8, minTxt);
                    return;
                }
                if (Integer.parseInt(minTxt.getText()) <= 0) {
                    commonController.partError(9, minTxt);
                    return;
                }
                if (Integer.parseInt(invTxt.getText()) < Integer.parseInt(minTxt.getText())) {
                    commonController.partError(6, invTxt);
                    return;
                }
                if (Integer.parseInt(invTxt.getText()) > Integer.parseInt(maxTxt.getText())) {
                    commonController.partError(7, invTxt);
                    return;
                }
                if ((nameTxt.getText().length() < 1)) {
                    commonController.partError(4, nameTxt);
                    return;
                }
                if (Double.parseDouble(priceCostTxt.getText()) <= 0) {
                    commonController.partError(5, priceCostTxt);
                    return;
                }
                if (!inHouseRBtn.isSelected()) {
                    commonController.partError(2, null);
                    return;
                }
            }
            Inventory.addPart(part);
        } catch (NumberFormatException nfe) {
            commonController.partError(3, null);
            return;
        }
        idTxt.setText(Integer.toString(Part.incNextId()));
        commonController.handleReturnHome(event);
    }
}