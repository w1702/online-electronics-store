package model;

import javafx.collections.ObservableList;

import java.util.List;

public class User {
    private String id;
    private ObservableList<Order> orders;
    private ShoppingCart shoppingCart;

    public User(String id, ShoppingCart shoppingCart){
        this.id = id;
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public String getId() {
        return id;
    }
}
