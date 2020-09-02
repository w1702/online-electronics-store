package model;

/**
 * This class represents a review given to an Item
 */
public class Review {
    private String id;
    private String date;
    private String comment;
    private String userId;

    public Review(String id, String date, String comment, String userId){
        this.id = id;
        this.date = date;
        this.comment = comment;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public String getUserId() {
        return userId;
    }
}
