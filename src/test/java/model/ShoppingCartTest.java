package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {
    @Test
    public void getTotalCostTest(){
        // Arrange - setup the data
        ObservableList<Item> storeItems = FXCollections.observableArrayList();
        Item product1 = new Item(null, "product1", "someProduct", 1000, null, null);
        Item product2 = new Item(null, "product2", "someProduct", 2000, null, null);
        storeItems.add(product1);
        storeItems.add(product2);

        OnlineElectronicsStore onlineElectronicsStore = new OnlineElectronicsStore(storeItems);

        ObservableMap<String, Integer> itemQuantity = FXCollections.observableHashMap();
        itemQuantity.put("product1", 1);
        itemQuantity.put("product2", 1);

        // using a promo code
        ShoppingCart shoppingCart = new ShoppingCart(itemQuantity, true);

        // Act - call the method
        double totalCost = shoppingCart.getTotalCost();

        // Assert - compare the output of the method
        Assertions.assertEquals(2700, totalCost);
    }
}