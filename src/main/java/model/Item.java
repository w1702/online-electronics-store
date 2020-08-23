package model;

import java.util.List;

public class Item {
    private List<Review> reviews;
    private String id, name, image, description;
    private Double cost;

    public Item(){

    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getImage(){
        return image;
    }

    public String getDescription(){
        return description;
    }

    public Double getCost(){
        return cost;
    }

    public List<Review> getReviews(){
        return reviews;
    }
}
