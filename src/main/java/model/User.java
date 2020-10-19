package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ObservableList<Order> orders;
    private ShoppingCart shoppingCart;

    public User(String id, String firstName, String lastName, String email, String password){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.orders = FXCollections.observableArrayList();
        this.shoppingCart = new ShoppingCart(FXCollections.observableHashMap(), false);
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ObservableList<Order> getOrders() {
        return orders;
    }

}
