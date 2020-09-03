package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class ShoppingCart {
    private OnlineElectronicsStore onlineElectronicsStore;
    private ObservableMap<String, Integer> itemQuantity;

    public ShoppingCart(OnlineElectronicsStore onlineElectronicsStore, ObservableMap<String, Integer> itemQuantity){
        this.onlineElectronicsStore = onlineElectronicsStore;
        this.itemQuantity = itemQuantity;

    }

    public ObservableList<Item> getUniqueItemsAsList(){
        ObservableList<Item> uniqueItems = FXCollections.observableArrayList();
        for (String itemName : itemQuantity.keySet()) {
            Item item = onlineElectronicsStore.getItemById(itemName);
            uniqueItems.add(item);
        }
        return uniqueItems;
    }

    public void addItem(String itemId, Integer quantity){
        itemQuantity.put(itemId, quantity);
    }

    public ObservableMap<String, Integer> getItemQuantity() {
        return itemQuantity;
    }

    // todo: write unit test for this
    public double getTotalCost(){
        double totalCost = 0;
        for (String itemName : itemQuantity.keySet()) {
            Item item = onlineElectronicsStore.getItemById(itemName);
            totalCost += item.getCost() * itemQuantity.get(itemName);
        }
        return totalCost;
    }
}
