package db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import static db.DatabaseCredentials.*;

/**
 * This class is responsible for making the write calls to MongoDB
 */
public class DatabaseWriteClient {
    public static UpdateResult writeItemReviewToDB(){

        Bson bsonWriteOperation = set("NEW_TEST_KEY", "SOME NEW VALUE");

        UpdateResult updateResult = writeToDatabase(bsonWriteOperation);

        System.out.println(updateResult);
        return updateResult;
    }

    public static void writeUserToDB(){
        // todo: Balraj's part
    }

    public void writePaymentToDB(){
        // todo: Alan's part
    }

    private static UpdateResult writeToDatabase(Bson bsonWriteOperation){
        try(MongoClient mongoClient = new MongoClient(new MongoClientURI(URI))){
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_ONLINE_ELECTRONICS_STORE);
            Bson bsonFilter = eq("_id", new ObjectId("5f34fefcb1fc6f6ab7b57870"));
            return collection.updateOne(bsonFilter, bsonWriteOperation);
        }
    }
}
