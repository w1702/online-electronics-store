package db;

import com.google.gson.*;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import model.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for making the calls to MongoDB
 */
public class DatabaseClient {
    private static final String URI = "mongodb+srv://asd2020group7ReadWriteUser:DKTxCPtNhbOGxJZl@asd2020group7.jhxog.mongodb.net/asd_assignment?retryWrites=true&w=majority";
    private static final String DATABASE_NAME = "asd_assignment";
    private static final String COLLECTION_NAME_ONLINE_ELECTRONICS_STORE = "onlineElectronicsStore";

    private Document appData;
    /**
     * Constructor, connects to MongoDB and collects all documents from default db and collection
     */
    public DatabaseClient() {
        MongoClientURI uri = new MongoClientURI(URI);
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
        List<Document> documents = new ArrayList<>();
        try {
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_ONLINE_ELECTRONICS_STORE);
            for (Document document : collection.find()) {
                documents.add(document);
            }
            // we will be only using 1 document in MongoDB for now
            this.appData = documents.get(0);
        }
        finally {
            mongoClient.close();
        }
    }

    public ObservableList<Item> readItemsFromDB(){
        JsonArray itemsJsonArray = getAppData()
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

    public ObservableList<User> readUsersFromDB(){
        ObservableList<User> users = FXCollections.observableArrayList();
        JsonArray usersJsonArray = getAppData()
                .get("users").getAsJsonArray();
        // todo: fetch other fields of the user object
        for (JsonElement userJsonElement : usersJsonArray) {
            // todo: initializing cart items may not be needed in final version
            // temporary shopping cart code begin
            ObservableMap<String, Integer> itemQuantity = FXCollections.observableHashMap();
            JsonArray cartItemsJsonArray = userJsonElement.getAsJsonObject()
                    .get("shoppingCart").getAsJsonObject()
                    .get("itemQuantity").getAsJsonArray();
            for (JsonElement cartItemJsonElement : cartItemsJsonArray) {
                String itemId = cartItemJsonElement.getAsJsonObject().get("id").getAsJsonObject().get("$oid").getAsString();
                Integer quantity = cartItemJsonElement.getAsJsonObject().get("quantity").getAsJsonObject().get("$numberLong").getAsInt();
                itemQuantity.put(itemId, quantity);
            }
            ShoppingCart shoppingCart = new ShoppingCart(itemQuantity, false);
            // temporary shopping cart code end

            User user = new User(
                    userJsonElement.getAsJsonObject().get("id").getAsJsonObject().get("$oid").getAsString(),
                    shoppingCart
            );
            users.add(user);
        }
        return users;
    }

    public String readPromoCodeFromDB(){
        return getAppData()
                .get("promotion").getAsJsonObject()
                .get("promoCode").getAsString();
}

    public double readDiscountValueFromDB(){
        return getAppData()
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

    /**
     * Get the app data as a JsonObject
     * @return the JsonObject
     */
    private JsonObject getAppData(){
        return JsonParser.parseString(appData.toJson()).getAsJsonObject();
    }
}
