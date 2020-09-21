package model;

import db.DatabaseClient;
import javafx.collections.ObservableList;

import java.util.List;

public class OnlineElectronicsStore {
    private DatabaseClient databaseClient;
    private Admin admin;
    private ObservableList<Item> items;
    private ObservableList<User> users;
    private User loggedInUser;
    // todo: change this to read from db
    private String promoCode;
    private double discountValue;

    public OnlineElectronicsStore(DatabaseClient databaseClient){
        this.databaseClient = databaseClient;
        this.items = databaseClient.readItemsFromDB();
        this.users = databaseClient.readUsersFromDB();
        // todo: set the logged in user on login
        this.loggedInUser = users.get(0); // temporary solution
        this.promoCode = databaseClient.readPromoCodeFromDB();
        this.discountValue = databaseClient.readDiscountValueFromDB();

        // todo: revise this hacky code
        // setting shopping cart property manually
        for (User user : users) {
            user.getShoppingCart().setOnlineElectronicsStore(this);
        }
    }

    // for unit testing
    public OnlineElectronicsStore(ObservableList<Item> items){
        this.items = items;
    }

    public Item getItemById(String itemId){
        return items.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElse(null);
    }

    public Item getItemByName(String itemName){
        for (Item item : items) {
            if(item.getName().equals(itemName)){
                return item;
            }
        }
        return null;
    }

    public ObservableList<Item> getItems() {
        return items;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public double getDiscountValue() {
        return discountValue;
    }
}
