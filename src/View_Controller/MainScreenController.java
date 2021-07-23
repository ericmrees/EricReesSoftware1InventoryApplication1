package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button addPartBtn;

    @FXML
    private Button modifyPartBtn;

    @FXML
    private Button deletePartBtn;

    @FXML
    private Button searchPartsBtn;

    @FXML
    private Button clearSearchPartsBtn;

    @FXML
    private Button clearSearchProductsBtn;

    @FXML
    private TextField searchPartsTxt;

    @FXML
    private TableView<Part> partsTableView;

    @FXML
    private TableColumn<Part, Integer> partsPartIdCol;

    @FXML
    private TableColumn<Part, String> partsPartNameCol;

    @FXML
    private TableColumn<Part, Integer> partsInventoryLevelCol;

    @FXML
    private TableColumn<Part, Double> partsPriceCostPerUnitCol;

    @FXML
    private Button exitInventoryBtn;

    @FXML
    private Button addProductBtn;

    @FXML
    private Button modifyProductBtn;

    @FXML
    private Button deleteProductBtn;

    @FXML
    private Button searchProductsBtn;

    @FXML
    private TextField searchProductsTxt;

    @FXML
    private TableView<Product> productsTableView;

    @FXML
    private TableColumn<Product, Integer> productsProductIdCol;

    @FXML
    private TableColumn<Product, String> productsProductNameCol;

    @FXML
    private TableColumn<Product, Integer> productsInventoryLevelCol;

    @FXML
    private TableColumn<Product, Double> productsPriceCostPerUnitCol;

    @FXML
    private CommonController commonController = new CommonController();

    @FXML
    private ObservableList<Part> allParts = FXCollections.observableArrayList();

    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private ObservableList<Product> productSearch = FXCollections.observableArrayList();

    private ObservableList<Part> partsSearch = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partsPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceCostPerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsPriceCostPerUnitCol.setCellFactory(tc -> new CurrencyCell<>());
        partsTableView.setItems(Inventory.getAllParts());

        productsProductIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productsProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productsPriceCostPerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productsPriceCostPerUnitCol.setCellFactory(tc -> new CurrencyCell<>());
        productsTableView.setItems(Inventory.getAllProducts());
    }

    @FXML
    void handleAddPart(ActionEvent event) throws IOException {
        commonController.handleGoToView(event, "/View_Controller/AddPart.fxml");
    }

    @FXML
    void handleModifyPart(ActionEvent event) throws IOException {
        Part SP = partsTableView.getSelectionModel().getSelectedItem();
        if (SP == null) {
            commonController.noSelectionError(1);
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View_Controller/ModifyPart.fxml"));
            loader.load();

            ModifyPartController PartController = loader.getController();
            PartController.retrievePart(SP);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void handleDeletePart(ActionEvent event) {
        Part SP = partsTableView.getSelectionModel().getSelectedItem();
        if (SP == null) {
            commonController.noSelectionError(1);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("This will delete the selected part");
            alert.setContentText("Are you sure you want to delete the selected part?");
            Optional<ButtonType> res = alert.showAndWait();

            if (res.get() == ButtonType.OK) {
                deletePart(SP.getId());
            }
        }
    }

    public boolean deletePart(int id) {
        for (Part part : Inventory.getAllParts()) {
            if (part.getId() == id)
                return Inventory.getAllParts().remove(part);
        }
        return false;
    }

    @FXML
    void handleSearchPart(ActionEvent event) {
        String query = searchPartsTxt.getText();
        if (!query.isEmpty()) {
            partsSearch.clear();
            for (Part SP : Inventory.getAllParts()) {
                if (SP.getName().contains(query) || (Integer.toString(SP.getId()).contains(query))) {
                    partsSearch.add(SP);
                }
            }
            partsTableView.setItems(partsSearch);
            partsTableView.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Parts search bar is empty!");
            alert.showAndWait();
        }
    }

    public boolean searchPartId(int id) {
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

    public Part selectPart(int id) {
        for (Part SP : Inventory.getAllParts()) {
            if (SP.getId() == id)
                return SP;
        }
        return null;
    }

    @FXML
    void handleExitInventory(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm to Exit Inventory Program!");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @FXML
    void handleAddProduct(ActionEvent event) throws IOException {
        commonController.handleGoToView(event, "/View_Controller/AddProduct.fxml");
    }

    @FXML
    void handleModifyProduct(ActionEvent event) throws IOException {
        Product SP = productsTableView.getSelectionModel().getSelectedItem();
        if (SP == null) {
            commonController.noSelectionError(1);
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View_Controller/ModifyProduct.fxml"));
            loader.load();

            ModifyProductController ProductController = loader.getController();
            ProductController.retrieveProduct(SP);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void handleDeleteProduct(ActionEvent event) {
        Product SP = productsTableView.getSelectionModel().getSelectedItem();
        if (SP == null) {
            commonController.noSelectionError(1);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("This will delete the selected product");
            alert.setContentText("Are you sure you want to delete the selected product?");
            Optional<ButtonType> res = alert.showAndWait();

            if (res.get() == ButtonType.OK) {
                deleteProduct(SP.getId());
            }
        }
    }
    public boolean deleteProduct(int id) {
        for (Product SP : Inventory.getAllProducts()) {
            if (SP.getId() == id)
                return Inventory.getAllProducts().remove(SP);
        }
        return false;
    }

    @FXML
    void handleSearchProduct(ActionEvent event) {
        String query = searchProductsTxt.getText();
        if (!query.isEmpty()) {
            productSearch.clear();
            for (Product SP : Inventory.getAllProducts()) {
                if (SP.getName().contains(query) || (Integer.toString(SP.getId()).contains(query))){
                    productSearch.add(SP);
                }
            }
            productsTableView.setItems(productSearch);
            productsTableView.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Product search bar is empty!");
            alert.showAndWait();
        }
    }

    @FXML
    void handleClearSearchParts(ActionEvent event) {
        partsTableView.setItems(Inventory.getAllParts());
        searchPartsTxt.setText("");
    }

    @FXML
    void handleClearSearchProducts(ActionEvent event) {
        productsTableView.setItems(Inventory.getAllProducts());
        searchProductsTxt.setText("");
    }

    public boolean searchProductId(int id) {
        for (Product SP : Inventory.getAllProducts()) {
            if (SP.getId() == id)
                return true;
        }
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setContentText("Product Not Found!");
        alert.showAndWait();
        return false;
    }

    public Product selectProduct(int id) {
        for (Product SP : Inventory.getAllProducts()) {
            if (SP.getId() == id)
                return SP;
        }
        return null;
    }
}