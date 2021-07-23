package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part part) {
        allParts.add(part);
    }

    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public static Part lookupPartId(int id) {
        for (Part part : allParts) {
            if (part.getId() == id) {
                return part;
            }
        }
        return null;
    }

    public static Product lookupProductId(int id) {
        for (Product product : allProducts) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public static Part lookupPartName(String name) {
        for (Part part : allParts) {
            if (part.getName().equalsIgnoreCase(name)) {
                return part;
            }
        }
        return null;
    }

    public static Product lookupProductName(String name) {
        for (Product product : allProducts) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public static void updatePart(Part selectedPart) {
        allParts.set(selectedPart.getId(), selectedPart);
    }

    public static void updateProduct(Product selectedProduct) {
        allProducts.set(selectedProduct.getId(), selectedProduct);
    }

    public static boolean deletePart(int selectedPart) {
        for (Part part : allParts) {
            if (part.getId() == selectedPart) {
                allParts.remove(part);
                return true;
            }
        }
        return false;
    }

    public static boolean deleteProduct(int selectedProduct) {
        for (Product product : allProducts) {
            if (product.getId() == selectedProduct) {
                allProducts.remove(product);
                return true;
            }
        }
        return false;
    }
}