package db;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.Promotion;
import model.Review;
import model.User;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static db.DatabaseCredentials.*;

/**
 * This class is responsible for making the calls to MongoDB
 */
public class DatabaseReadClient {
    private Document appData;
    /**
     * Constructor, connects to MongoDB and collects all documents from default db and collection
     */
    public DatabaseReadClient() {
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
                    itemJsonElement.getAsJsonObject().get("description").getAsString(),
                    itemJsonElement.getAsJsonObject().get("image").getAsString()
            );
            itemsObservableList.add(item);
        }
        return itemsObservableList;
    }

    public ObservableList<User> readUsersFromDB(){
        ObservableList<User> users = FXCollections.observableArrayList();
        JsonArray usersJsonArray = getAppData()
                .get("users").getAsJsonArray();
        for (JsonElement userJsonElement : usersJsonArray) {
            User user = new User(
                    userJsonElement.getAsJsonObject().get("id").getAsJsonObject().get("$oid").getAsString(),
                    userJsonElement.getAsJsonObject().get("firstName").getAsString(),
                    userJsonElement.getAsJsonObject().get("lastName").getAsString(),
                    userJsonElement.getAsJsonObject().get("email").getAsString(),
                    userJsonElement.getAsJsonObject().get("password").getAsString()
            );
            users.add(user);
        }
        return users;
    }

    public Promotion readPromotionFromDB(){
        return new Promotion(getAppData()
                .get("promotion").getAsJsonObject()
                .get("promoCode").getAsString(),
                getAppData()
                .get("promotion").getAsJsonObject()
                .get("discountValue").getAsDouble()
                );
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
    public JsonObject getAppData(){
        return JsonParser.parseString(appData.toJson()).getAsJsonObject();
    }
}
