package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private Double price;
    private int stock;
    private int min;
    private int max;

    static int nextId = 1;

    public static int incNextId() {
        return nextId++;
    }

    public static int getNextId() {
        return nextId;
    }

    public Product(int id, String name, Double price, int stock, int min, int max, ObservableList associatedParts) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts.addAll(associatedParts);
    }

    public Product(int id, String name, Double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    public Product() {

    };

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    public int getAssociatedPartsCount() {
        return associatedParts.size();
    }

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public boolean deleteAssociatedPart(int partId){
        for (Part p : associatedParts) {
            if (p.getId() == partId) {
                associatedParts.remove(p);
                return true;
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}