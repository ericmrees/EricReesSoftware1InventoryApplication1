package Main;

import Model.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InventoryApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        ObservableList<Part> associatedParts = FXCollections.observableArrayList();

        Part in1 = new InHousePart(Part.incNextId(), "Part in1", 5.00, 25, 10, 50, 101);
        Part in2 = new InHousePart(Part.incNextId(), "Part in2", 10.00, 20, 10, 50, 102);
        Part in3 = new InHousePart(Part.incNextId(), "Part in3", 15.00, 30, 10, 50, 103);
        Part in4 = new InHousePart(Part.incNextId(), "Part in4", 20.00, 23, 10, 50, 104);
        Part in5 = new InHousePart(Part.incNextId(), "Part in5", 25.00, 27, 10, 50, 105);
        Inventory.addPart(in1);
        Inventory.addPart(in2);
        Inventory.addPart(in3);
        Inventory.addPart(in4);
        Inventory.addPart(in5);

        Part out1 = new OutsourcedPart(Part.incNextId(), "Part out1", 2.00, 17, 10, 50, "Brahma");
        Part out2 = new OutsourcedPart(Part.incNextId(), "Part out2", 4.00, 27, 10, 50, "NAC");
        Part out3 = new OutsourcedPart(Part.incNextId(), "Part out3", 6.00, 37, 10, 50, "IHG");
        Part out4 = new OutsourcedPart(Part.incNextId(), "Part out4", 8.00, 47, 10, 50, "Micron");
        Part out5 = new OutsourcedPart(Part.incNextId(), "Part out5", 12.00, 33, 10, 50, "IM Flash");
        Inventory.addPart(out1);
        Inventory.addPart(out2);
        Inventory.addPart(out3);
        Inventory.addPart(out4);
        Inventory.addPart(out5);

        Product productA = new Product(Product.incNextId(), "Product A", 10.50, 8, 5, 20);
        productA.addAssociatedPart(in1);
        productA.addAssociatedPart(out1);
        Inventory.addProduct(productA);
        Product productB = new Product(Product.incNextId(), "Product B", 21.00, 11, 5, 20);
        productB.addAssociatedPart(in2);
        productB.addAssociatedPart(out2);
        Inventory.addProduct(productB);
        Product productC = new Product(Product.incNextId(), "Product C", 31.50, 14, 5, 20);
        productC.addAssociatedPart(in3);
        productC.addAssociatedPart(out3);
        Inventory.addProduct(productC);
        Product productD = new Product(Product.incNextId(), "Product D", 42.00, 16, 5, 20);
        productD.addAssociatedPart(in4);
        productD.addAssociatedPart(out4);
        Inventory.addProduct(productD);
        Product productE = new Product(Product.incNextId(), "Product E", 55.50, 18, 5, 20);
        productE.addAssociatedPart(in5);
        productE.addAssociatedPart(out5);
        Inventory.addProduct(productE);

        launch(args);
    }
}