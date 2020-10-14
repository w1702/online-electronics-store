package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    @Test
    public void addReviewTest(){
        String reviewComment = "the review comment";
        ObservableList<Review> reviews = FXCollections.observableArrayList();
        Item item = new Item(reviews, null, null, 0.0, null, null);
        item.addReview("someUserId", reviewComment);
        Assertions.assertEquals(1, item.getReviews().size());
        Assertions.assertEquals(reviewComment, item.getReviews().get(0).getComment());
    }
}