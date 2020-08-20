package model;

import java.util.List;

public class ShoppingCart {
    private List<Item> items;

    public ShoppingCart(List<Item> items){
        this.items = items;

    }

    public List<Item> getItems() {
        return items;
    }
}
