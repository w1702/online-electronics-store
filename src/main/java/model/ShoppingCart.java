package model;

import javafx.collections.ObservableList;

public class ShoppingCart {
    private ObservableList<Item> items;

    public ShoppingCart(ObservableList<Item> items){
        this.items = items;

    }

    public ObservableList<Item> getItems() {
        return items;
    }

    public double getTotalCost(){
        double totalCost = 0;
        for (Item item : items) {
            totalCost += item.getCost();
        }
        return totalCost;
    }
}
