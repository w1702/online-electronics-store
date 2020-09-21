package model;

import javafx.collections.ObservableList;

import java.util.List;

public class Item {
    private ObservableList<Review> reviews;
    private String id;
    private String name;
    private double cost;
    private String description;

    public Item(ObservableList<Review> reviews, String id, String name, double cost, String description){
        this.reviews = reviews;
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
