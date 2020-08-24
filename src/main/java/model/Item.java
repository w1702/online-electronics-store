package model;

import java.util.List;

public class Item {
    private List<Review> reviews;
    private String id;
    private String name;
    private double cost;
    private String description;

    public Item(List<Review> reviews, String id, String name, double cost, String description){
        this.reviews = reviews;
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.description = description;
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
