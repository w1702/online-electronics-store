package model;

/**
 * This class represents a review given to an Item
 */
public class Review {
    private String id;
    private String date;
    private String comment;
    private User user;

    public Review(){
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

    public User getUser() {
        return user;
    }
}
