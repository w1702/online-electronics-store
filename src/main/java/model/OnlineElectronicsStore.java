package model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import db.DatabaseClient;

import java.util.ArrayList;
import java.util.List;

public class OnlineElectronicsStore {
    private DatabaseClient databaseClient;
    private Admin admin;
    private List<Item> items;
    private List<User> users;

    public OnlineElectronicsStore(DatabaseClient databaseClient){
        this.databaseClient = databaseClient;
        this.users = fetchUsersAsList();
    }

    private List<User> fetchUsersAsList(){
        List<User> users = new ArrayList<>();
        JsonArray usersJsonArray = databaseClient.getAppData()
                .get("users").getAsJsonArray();
        // todo: fetch other fields of the user object
        for (JsonElement userJsonElement : usersJsonArray) {
            ArrayList<Item> cartItems = new ArrayList<>();
            JsonArray cartItemsJsonArray = userJsonElement.getAsJsonObject()
                    .get("shoppingCart").getAsJsonObject()
                    .get("items").getAsJsonArray();
            for (JsonElement element : cartItemsJsonArray) {
                Item item = new Item(null, // todo: implement reviews later
                        element.getAsJsonObject().get("id").getAsJsonObject().get("$oid").getAsString(),
                        element.getAsJsonObject().get("name").getAsString(),
                        element.getAsJsonObject().get("cost").getAsDouble(),
                        element.getAsJsonObject().get("description").getAsString()
                );
                cartItems.add(item);
            }
            ShoppingCart shoppingCart = new ShoppingCart(cartItems);
            users.add(new User(shoppingCart));
        }
        return users;
    }

    public List<User> getUsers() {
        return users;
    }
}
