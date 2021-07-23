package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    Stage stage;
    Parent scene;

    Product product;

    @FXML
    private TextField idTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField invTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField maxTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private Button searchBtn;

    @FXML
    private Button clearSearchBtn;

    @FXML
    private TextField searchTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button addPartBtn;

    @FXML
    private TableView<Part> inventoryPartTableView;

    @FXML
    private TableColumn<Part, Integer> inventoryPartPartIdCol;

    @FXML
    private TableColumn<Part, String> inventoryPartPartNameCol;

    @FXML
    private TableColumn<Part, Integer> inventoryPartInventoryLevelCol;

    @FXML
    private TableColumn<Part, Double> inventoryPartPricePerUnitCol;

    @FXML
    private TableView<Part> addPartTableView;

    @FXML
    private TableColumn<Part, Integer> addPartPartIdCol;

    @FXML
    private TableColumn<Part, String> addPartPartNameCol;

    @FXML
    private TableColumn<Part, Integer> addPartInventoryLevelCol;

    @FXML
    private TableColumn<Part, Double> addPartPricePerUnitCol;

    @FXML
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    @FXML
    private CommonController commonController = new CommonController();

    private ObservableList<Part> partsSearch = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        product = new Product();

        idTxt.setText(Integer.toString(Product.getNextId()));

        inventoryPartPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        inventoryPartPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryPartInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        inventoryPartPricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        inventoryPartPricePerUnitCol.setCellFactory(tc -> new CurrencyCell<>());
        inventoryPartTableView.setItems(Inventory.getAllParts());

        addPartPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addPartPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addPartInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addPartPricePerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        addPartPricePerUnitCol.setCellFactory(tc -> new CurrencyCell<>());
        addPartTableView.setItems(product.getAllAssociatedParts());
    }

    @FXML
    void handleAddProduct(ActionEvent event) {
        Part SP = inventoryPartTableView.getSelectionModel().getSelectedItem();
        if (SP == null) {
            commonController.noSelectionError(1);
        } else {
            try {
                product.addAssociatedPart(SP);
                addPartTableView.setItems(product.getAllAssociatedParts());
            } catch (NullPointerException npe) {
                commonController.productError(3, null);
            }
        }
    }

    @FXML
    void handleSaveAddProduct(ActionEvent event) throws IOException {
        try {
            if (Integer.parseInt(minTxt.getText()) >= Integer.parseInt(maxTxt.getText())) {
                commonController.productError(2, minTxt);
                return;
            }
            if (Integer.parseInt(minTxt.getText()) <= 0) {
                commonController.productError(8, minTxt);
                return;
            }
            if (Integer.parseInt(invTxt.getText()) < Integer.parseInt(minTxt.getText())) {
                commonController.productError(6, invTxt);
                return;
            }
            if (Integer.parseInt(invTxt.getText()) > Integer.parseInt(maxTxt.getText())) {
                commonController.productError(7, invTxt);
                return;
            }
            if ((nameTxt.getText().length() < 1)) {
                commonController.productError(4, nameTxt);
                return;
            }
            if (Double.parseDouble(priceTxt.getText()) <= 0) {
                commonController.productError(5, priceTxt);
                return;
            }
            if (getAddPartTableView().getItems().size() < 1) {
                commonController.productError(1, null);
                return;
            }
        } catch (NumberFormatException nfe) {
            commonController.productError(3, null);
            return;
        }
        int id = Integer.parseInt(idTxt.getText());
        String name = nameTxt.getText();
        int stock = Integer.parseInt(invTxt.getText());
        Double price = Double.parseDouble(priceTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        Inventory.addProduct(new Product(id, name, price, stock, min, max, product.getAllAssociatedParts()));
        idTxt.setText(Integer.toString(Product.incNextId()));
        commonController.handleReturnHome(event);
    }

    @FXML
    void handleCancelAddProduct(ActionEvent event) throws IOException {
        commonController.handleCancelCommon(event);
    }

    @FXML
    void handleSearchAddProduct(ActionEvent event) {
        String query = searchTxt.getText();
        if (!query.isEmpty()) {
            partsSearch.clear();
            for (Part SP : Inventory.getAllParts()) {
                if (SP.getName().contains(query) || (Integer.toString(SP.getId()).contains(query))) {
                    partsSearch.add(SP);
                }
            }
            inventoryPartTableView.setItems(partsSearch);
            inventoryPartTableView.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Parts search bar is empty!");
            alert.showAndWait();
        }
    }

    @FXML
    void handleClearSearchParts(ActionEvent event) {
        inventoryPartTableView.setItems(Inventory.getAllParts());
        searchTxt.setText("");
    }

    public Part selectPart(int id) {
        for (Part SP : Inventory.getAllParts()) {
            if (SP.getId() == id)
                return SP;
        }
        return null;
    }

    public boolean searchPartID(int id) {
        for (Part SP : Inventory.getAllParts()) {
            if (SP.getId() == id)
                return true;
        }
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setContentText("Part Not Found!");
        alert.showAndWait();
        return false;
    }

    @FXML
    void handleDeleteAddProduct(ActionEvent event) {
        Part SP = addPartTableView.getSelectionModel().getSelectedItem();
        if (SP == null) {
            commonController.noSelectionError(1);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("This will delete the selected product part");
            alert.setContentText("Are you sure you want to delete the selected product part?");
            Optional<ButtonType> res = alert.showAndWait();
            if (res.get() == ButtonType.OK) {
                product.deleteAssociatedPart(SP.getId());
                removeAddedPart(SP.getId());
            }
        }
    }

    public boolean removeAddedPart(int id) {
        for (Part SP : Inventory.getAllParts()) {
            if (SP.getId() == id)
                return Inventory.getAllParts().remove(SP);
        }
        return false;
    }

    public TableView<Part> getAddPartTableView() {
        return addPartTableView;
    }

    public void setAddPartTableView(TableView<Part> addPartTableView) {
        this.addPartTableView = addPartTableView;
    }
}