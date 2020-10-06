package model;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class Item {
    private ObservableList<Review> reviews;
    private String id;
    private String name;
    private double cost;
    private String description;
    private String image;

    public Item(ObservableList<Review> reviews, String id, String name, double cost, String description, String image){
        this.reviews = reviews;
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.image = image;
    }

    public void addReview(String userId, String comment){
        reviews.add(new Review(new UUID(8L, 8L).toString(), new Date().toString(), comment, userId));
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

    public ObservableList<Review> getReviews() {
        return reviews;
    }

    public String getImage() {
        return image;
    }

    public Image getConvertBase64toImage() {
        // Convert Base64 to Image
        String base64Image = image.split(",")[1];
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        InputStream stream = new ByteArrayInputStream(imageBytes);
        Image image = new Image(stream);
        return image;
    }

}
