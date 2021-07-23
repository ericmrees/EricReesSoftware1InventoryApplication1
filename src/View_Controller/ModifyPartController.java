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


public class ModifyPartController implements Initializable {

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
    private ToggleGroup addPartRadioBtn2;

    @FXML
    private RadioButton outsourcedRBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Object part;

    @FXML
    private boolean outsourced;

    @FXML
    private CommonController commonController = new CommonController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData();
    }

    @FXML
    void setData() {
        if (part instanceof InHousePart) {
            InHousePart part1 = (InHousePart) part;
            inHouseRBtn.setSelected(true);
            machineIdLbl.setText("Machine ID");
            this.nameTxt.setText(part1.getName());
            this.idTxt.setText((Integer.toString(part1.getId())));
            this.invTxt.setText((Integer.toString(part1.getStock())));
            this.priceCostTxt.setText((Double.toString(part1.getPrice())));
            this.minTxt.setText((Integer.toString(part1.getMin())));
            this.maxTxt.setText((Integer.toString(part1.getMax())));
            this.machineIdTxt.setText((Integer.toString(part1.getMachineId())));
        }
        if (part instanceof OutsourcedPart) {
            OutsourcedPart part2 = (OutsourcedPart) part;
            outsourcedRBtn.setSelected(true);
            machineIdLbl.setText("Company Name");
            this.nameTxt.setText(part2.getName());
            this.idTxt.setText((Integer.toString(part2.getId())));
            this.invTxt.setText((Integer.toString(part2.getStock())));
            this.priceCostTxt.setText((Double.toString(part2.getPrice())));
            this.minTxt.setText((Integer.toString(part2.getMin())));
            this.maxTxt.setText((Integer.toString(part2.getMax())));
            this.machineIdTxt.setText(part2.getCompanyName());
        }
    }

    @FXML
    void handleRadioInHouse(ActionEvent event) {
        outsourced = false;
        machineIdLbl.setText("Machine ID");
        inHouseRBtn.setSelected(true);
    }

    @FXML
    void handleRadioOutsourced(ActionEvent event) {
        outsourced = true;
        machineIdLbl.setText("Company Name");
        outsourcedRBtn.setSelected(true);
    }

    @FXML
    void handleSaveModifyPart(ActionEvent event) throws IOException {
        try {
            if (outsourced) {
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
            } else {
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
            }
            if (outsourcedRBtn.isSelected()) {
                if (update(Integer.parseInt(idTxt.getText()), new OutsourcedPart(Integer.parseInt(idTxt.getText()), nameTxt.getText(), Double.parseDouble(priceCostTxt.getText()), Integer.parseInt(invTxt.getText()), Integer.parseInt(minTxt.getText()), Integer.parseInt(maxTxt.getText()), machineIdTxt.getText()))) {
                    commonController.handleReturnHome(event);
                }
            } else if (inHouseRBtn.isSelected()) {
                if (update(Integer.parseInt(idTxt.getText()), new InHousePart(Integer.parseInt(idTxt.getText()), nameTxt.getText(), Double.parseDouble(priceCostTxt.getText()), Integer.parseInt(invTxt.getText()), Integer.parseInt(minTxt.getText()), Integer.parseInt(maxTxt.getText()), Integer.parseInt(machineIdTxt.getText())))) {
                commonController.handleReturnHome(event);
                }
            }
        } catch (NumberFormatException nfe) {
            commonController.partError(3, null);
        }
    }

    @FXML
    void handleCancelModifyPart (ActionEvent event) throws IOException {
        commonController.handleCancelCommon(event);
    }

    public void retrievePart (Part rPart) {
        idTxt.setText(String.valueOf(rPart.getId()));
        nameTxt.setText(rPart.getName());
        invTxt.setText(String.valueOf(rPart.getStock()));
        priceCostTxt.setText(String.valueOf(rPart.getPrice()));
        maxTxt.setText(String.valueOf(rPart.getMax()));
        minTxt.setText(String.valueOf(rPart.getMin()));

        if (rPart instanceof InHousePart) {
            InHousePart inHousePart = (InHousePart) rPart;
            machineIdTxt.setText(String.valueOf(((InHousePart) rPart).getMachineId()));
            machineIdLbl.setText("Machine ID");
            inHouseRBtn.setSelected(true);
        } else if (rPart instanceof OutsourcedPart) {
            OutsourcedPart outsourcedPart = (OutsourcedPart) rPart;
            machineIdTxt.setText(((OutsourcedPart) rPart).getCompanyName());
            outsourcedRBtn.setSelected(true);
        }
    }

    public void setCompanyNameField(ActionEvent event) {
        if (inHouseRBtn.isSelected()) {
            machineIdLbl.setText("Machine ID");
            machineIdTxt.setPromptText("Machine ID");
        }
        if (outsourcedRBtn.isSelected()) {
            machineIdLbl.setText("Company Name");
            machineIdTxt.setPromptText("Company Name");
        }
    }

    public boolean update(int id, Part updatePart) {
        int index = Integer.parseInt(idTxt.getText()) - 1;
        for (Part parts : Inventory.getAllParts()) {
            if (updatePart.getId() == id) {
                Inventory.getAllParts().set(index, updatePart);
                return true;
            }
        }
        return false;
    }
}