package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {
    @Test
    public void getTotalCostTest(){
        // Arrange - setup the data
        Item item1 = new Item(null, null, null, 100.00, null, null);
        Item item2 = new Item(null, null, null, 150.00, null, null);
        ObservableList<Item> items = FXCollections.observableArrayList();
        items.add(item1);
        items.add(item2);
        double expectedTotalCost = 250.00; // expected output of function
        // Act - call the function to test
        double actualTotalCost = new ShoppingCart(items).getTotalCost(); // actual output of function
        // Assert - check that expected output is equal to actual output
        Assertions.assertEquals(expectedTotalCost, actualTotalCost);
    }
}