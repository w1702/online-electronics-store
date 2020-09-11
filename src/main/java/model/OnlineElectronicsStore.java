package model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import db.DatabaseClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class OnlineElectronicsStore {
    private DatabaseClient databaseClient;
    private Admin admin;
    private List<Item> items;
    private List<User> users;
    private User loggedInUser;

    public OnlineElectronicsStore(DatabaseClient databaseClient){
        this.databaseClient = databaseClient;
        this.users = fetchUsersAsList();
        // todo: set the logged in user on login
        this.loggedInUser = users.get(0); // temporary solution
        this.items = fetchItemsAsList();
    }

    private List<User> fetchUsersAsList(){
        List<User> users = new ArrayList<>();
        JsonArray usersJsonArray = databaseClient.getAppData()
                .get("users").getAsJsonArray();
        // todo: fetch other fields of the user object
        for (JsonElement userJsonElement : usersJsonArray) {
            ObservableList<Item> cartItems = FXCollections.observableArrayList();
            JsonArray cartItemsJsonArray = userJsonElement.getAsJsonObject()
                    .get("shoppingCart").getAsJsonObject()
                    .get("itemQuantity").getAsJsonArray();
            for (JsonElement element : cartItemsJsonArray) {
                String itemId = element.getAsJsonObject().get("id").getAsJsonObject().get("$oid").getAsString();
                JsonArray itemsJsonArray = databaseClient.getAppData()
                    .get("items").getAsJsonArray();
                for (JsonElement itemJsonElement : itemsJsonArray){
                    if (itemJsonElement.getAsJsonObject().get("id").getAsJsonObject().get("$oid").getAsString() == itemId){
                        Item item = new Item(null, // todo: implement reviews later
                                itemId, 
                                itemJsonElement.getAsJsonObject().get("name").getAsString(),
                                itemJsonElement.getAsJsonObject().get("cost").getAsDouble(),
                                itemJsonElement.getAsJsonObject().get("description").getAsString(),
                                itemJsonElement.getAsJsonObject().get("image").getAsString()
                        );
                        cartItems.add(item);
                    }
                }
            }
            ShoppingCart shoppingCart = new ShoppingCart(cartItems);
            users.add(new User(shoppingCart));
        }
        return users;
    }
    
    private List<Item> fetchItemsAsList(){
        List<Item> items = new ArrayList<>();
        JsonArray itemsJsonArray = databaseClient.getAppData()
                    .get("items").getAsJsonArray();
        for (JsonElement itemJsonElement : itemsJsonArray){
            Item item = new Item(null, // todo: implement reviews later
                    itemJsonElement.getAsJsonObject().get("id").getAsJsonObject().get("$oid").getAsString(), 
                    itemJsonElement.getAsJsonObject().get("name").getAsString(),
                    itemJsonElement.getAsJsonObject().get("cost").getAsDouble(),
                    itemJsonElement.getAsJsonObject().get("description").getAsString(),
                    itemJsonElement.getAsJsonObject().get("image").getAsString()
            );
            items.add(item);
        }
        return items;
    }
    

    public List<User> getUsers() {
        return users;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public List<Item> getItems() {
        return items;
    }
}
