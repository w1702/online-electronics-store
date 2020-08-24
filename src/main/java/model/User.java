package model;

import java.util.List;

public class User {
    private List<Order> orders;
    private ShoppingCart shoppingCart;

    public User(ShoppingCart shoppingCart){
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
