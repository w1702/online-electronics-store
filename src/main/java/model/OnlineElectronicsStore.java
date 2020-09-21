package model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import db.DatabaseClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

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
        this.items = fetchItemsFromDB();
        this.users = fetchUsersFromDB();
        // todo: set the logged in user on login
        this.loggedInUser = users.get(0); // temporary solution
        this.promoCode = fetchPromoCodeFromDB();
        this.discountValue = fetchDiscountValueFrom();
    }

    // for unit testing
    public OnlineElectronicsStore(ObservableList<Item> items){
        this.items = items;
    }

    private ObservableList<Item> fetchItemsFromDB(){
        JsonArray itemsJsonArray = databaseClient.getAppData()
                .get("items").getAsJsonArray();
        ObservableList<Item> itemsObservableList = FXCollections.observableArrayList();
        for (JsonElement itemJsonElement : itemsJsonArray) {
            Item item = new Item(
                    getReviewsFromItemsJsonElement(itemJsonElement),
                    itemJsonElement.getAsJsonObject().get("id").getAsJsonObject().get("$oid").getAsString(),
                    itemJsonElement.getAsJsonObject().get("name").getAsString(),
                    itemJsonElement.getAsJsonObject().get("cost").getAsDouble(),
                    itemJsonElement.getAsJsonObject().get("description").getAsString()
            );
            itemsObservableList.add(item);
        }
        return itemsObservableList;
    }

    private ObservableList<User> fetchUsersFromDB(){
        ObservableList<User> users = FXCollections.observableArrayList();
        JsonArray usersJsonArray = databaseClient.getAppData()
                .get("users").getAsJsonArray();
        // todo: fetch other fields of the user object
        for (JsonElement userJsonElement : usersJsonArray) {

            // todo: initializing cart items may not be needed in final version
            ObservableMap<String, Integer> itemQuantity = FXCollections.observableHashMap();
            JsonArray cartItemsJsonArray = userJsonElement.getAsJsonObject()
                    .get("shoppingCart").getAsJsonObject()
                    .get("itemQuantity").getAsJsonArray();
            for (JsonElement cartItemJsonElement : cartItemsJsonArray) {
                String itemId = cartItemJsonElement.getAsJsonObject().get("id").getAsJsonObject().get("$oid").getAsString();
                Integer quantity = cartItemJsonElement.getAsJsonObject().get("quantity").getAsJsonObject().get("$numberLong").getAsInt();
                itemQuantity.put(itemId, quantity);
            }

            ShoppingCart shoppingCart = new ShoppingCart(this, itemQuantity, false);

            User user = new User(
                    userJsonElement.getAsJsonObject().get("id").getAsJsonObject().get("$oid").getAsString(),
                    shoppingCart
            );
            users.add(user);
        }
        return users;
    }

    private String fetchPromoCodeFromDB(){
        return databaseClient.getAppData()
                .get("promotion").getAsJsonObject()
                .get("promoCode").getAsString();
    }

    private double fetchDiscountValueFrom(){
        return databaseClient.getAppData()
                .get("promotion").getAsJsonObject()
                .get("discountValue").getAsDouble();
    }

    private ObservableList<Review> getReviewsFromItemsJsonElement(JsonElement itemJsonElement){
        ObservableList<Review> reviews = FXCollections.observableArrayList();
        JsonArray reviewsJsonArray = itemJsonElement.getAsJsonObject().get("reviews").getAsJsonArray();
        for (JsonElement reviewJsonElement : reviewsJsonArray) {
            Review review = new Review(
                    reviewJsonElement.getAsJsonObject().get("id").getAsJsonObject().get("$oid").getAsString(),
                    reviewJsonElement.getAsJsonObject().get("date").getAsString(),
                    reviewJsonElement.getAsJsonObject().get("comment").getAsString(),
                    reviewJsonElement.getAsJsonObject().get("userId").getAsJsonObject().get("$oid").getAsString()
            );
            reviews.add(review);
        }
        return reviews;
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
