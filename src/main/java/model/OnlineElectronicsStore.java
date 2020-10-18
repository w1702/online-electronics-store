package model;

import db.DatabaseReadClient;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;

public class OnlineElectronicsStore {
    private DatabaseReadClient databaseClient;
    private ObservableList<Item> items;
    private ObservableList<User> users;
    private User loggedInUser;
    private Promotion promotion;
    private String currentlySelectedItem;

    public OnlineElectronicsStore(DatabaseReadClient databaseClient){
        this.databaseClient = databaseClient;
        this.items = databaseClient.readItemsFromDB();
        this.users = databaseClient.readUsersFromDB();
        this.promotion = databaseClient.readPromotionFromDB();

        // todo: revise this hacky code
        // setting shopping cart property manually
        for (User user : users) {
            user.getShoppingCart().setOnlineElectronicsStore(this);
        }
    }

    // for unit testing
    public OnlineElectronicsStore(){
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
    
    void setItems(ObservableList<Item> items){
        this.items = items;
    }

    void setPromotion(Promotion promotion) {
        this.promotion = promotion;
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
        return promotion.getPromoCode();
    }

    public double getDiscountValue() {
        return promotion.getDiscountValue();
    }

    public DatabaseReadClient getDatabaseClient() {
        return databaseClient;
    }

    // Get Sorted Items, can sort by name, cost(Price), id(Created time) in ASC or DESC order
    public List<Item> getSortedItems(String sortBy, String order, String keyword, double min, double max) {
        List<Item> itemsList = new ArrayList<>();
        if (keyword.isEmpty() && min == 0 && max == 999999999){ // check is search list or homepage list
            itemsList = getItems();
        }else {
            itemsList = getItemByKeywords(keyword, min, max);
        }
        return sort(itemsList, sortBy, order); // return sortedItem list
    }
    
    public List<Item> sort(List<Item> itemsList, String sortBy, String order){
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
                    String valA = itemA.getId();
                    String valB = itemB.getId();
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
    
    public List<Item> getItemByKeywords(String keywords, double min, double max){
        ObservableList<Item> searchList = FXCollections.observableArrayList();
        for (Item item : items) {
            if(min <= item.getCost() && item.getCost() <= max){
                if(item.getName().toLowerCase().contains(keywords.toLowerCase())){
                    searchList.add(item);
                }
                else if (item.getDescription().toLowerCase().contains(keywords.toLowerCase())){
                    searchList.add(item);
                }
            }
        }
        return sort(searchList, "name", "ASC");
    }
    
    public void setCurrentlySelectedItem(String currentlySelectedItem){
        this.currentlySelectedItem = currentlySelectedItem;
    }
    
    public String getCurrentlySelectedItem(){
        return currentlySelectedItem;
    }
}
