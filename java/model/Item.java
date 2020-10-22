package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import db.DatabaseWriteClient;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class Item {
    private ObservableList<Review> reviews;
   // private String id;
   // private String name;
   // private double cost;
  //  private String description;
    private String image;
    
    private StringProperty id;
    private StringProperty name;
	private DoubleProperty cost;
	private StringProperty description;

    public Item(ObservableList<Review> reviews, String id, String name, double cost, String description, String image){
        this.reviews = reviews;
        this.id =  new SimpleStringProperty();
        this.id.set(id);
        this.name =  new SimpleStringProperty();
        this.name.set(name);;
        this.cost =  new SimpleDoubleProperty();
        this.cost.set(cost);
        this.description =  new SimpleStringProperty();
        this.description.set(description);
        this.image = image;
    }
    public void addReview(String userId, String comment){
    	
    
        Review review = new Review(new ObjectId().toString(), new Date().toString(), comment, userId);
        // write to database before app data to avoid index out of bounds exceptions
        DatabaseWriteClient.writeItemReviewToDB(this, review);
        reviews.add(review);

    }
   
    public String getId() {
        return id.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public double getCost() {
        return cost.getValue();
    }

    public String getDescription() {
        return description.getValue();
    }

    public List<Review> getReviews() {
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

	public StringProperty nameProperty() {
		
		return this.name;
	}
	
    public StringProperty idProperty() {
		
		return this.id;
	}

     public DoubleProperty costProperty() {
	
	return this.cost;
}

     public StringProperty desProperty() {
	
	return this.description;
}

}
