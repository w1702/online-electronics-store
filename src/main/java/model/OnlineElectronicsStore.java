package model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import db.DatabaseClient;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private String currentlySelectedItem;

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

    void setDiscountValue(double discountValue){
        this.discountValue = discountValue;
    }

    // Get Sorted Items, can sort by name, cost(Price), id(Created time) in ASC or DESC order
    public List<Item> getSortedItems(String sortBy, String order) {
        List<Item> itemsList = getItems();
        List<Item> compareList = new ArrayList<>();
        for (Item item : itemsList){
            compareList.add(item);
        }
        Collections.sort(compareList, new Comparator<Item>() {
            @Override
            public int compare(Item itemA, Item itemB) {
                if (sortBy.equals("cost")) { // sort by cost
                    Double valA = itemA.getCost();
                    Double valB = itemB.getCost();
                    if (order == "DESC"){
                        return -valA.compareTo(valB); // descending
                    } else {
                        return valA.compareTo(valB); // ascending
                    }
                } else if (sortBy.equals("name")) { // sort by name
                    String valA = itemA.getName();
                    String valB = itemB.getName();
                    if (order == "DESC"){
                        return -valA.compareTo(valB); // descending
                    } else {
                        return valA.compareTo(valB); // ascending
                    }
                } else { // default: sort by ID
                    String valA = itemA.getID();
                    String valB = itemB.getID();
                    if (order == "DESC"){
                        return -valA.compareTo(valB); // descending
                    } else {
                        return valA.compareTo(valB); // ascending
                    }
                }
            }
        });
        List<Item> sortedItems = new ArrayList<>();
        for (Item item : compareList){
            sortedItems.add(item);
        }
        return sortedItems; // return sortedItem list
    }
    
    public void setCurrentlySelectedItem(String currentlySelectedItem){
        this.currentlySelectedItem = currentlySelectedItem;
    }
    
    public String getCurrentlySelectedItem(){
        return currentlySelectedItem;
    }
}
