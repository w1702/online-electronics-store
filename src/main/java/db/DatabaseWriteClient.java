package db;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import model.Item;
import model.Review;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import static db.DatabaseCredentials.*;

/**
 * This class is responsible for making the write calls to MongoDB
 *
 * NOTE: call these methods BEFORE adding to application data structures to avoid index out of bounds exceptions
 */
public class DatabaseWriteClient {
    public static UpdateResult writeItemReviewToDB(Item item, Review review){
        Bson bsonFilter = eq("items.id", new ObjectId(item.getId()));
        BasicDBList reviews = new BasicDBList();

        for (Review existingReview : item.getReviews()) {
            reviews.add(
                    and(
                        new BasicDBObject("id", new ObjectId(existingReview.getId())),
                        new BasicDBObject("date", existingReview.getDate()),
                        new BasicDBObject("comment", existingReview.getComment()),
                        new BasicDBObject("userId", new ObjectId(existingReview.getUserId()))
                    )
            );
        }
        
        reviews.add(
                and(
                    new BasicDBObject("id", new ObjectId(review.getId())),
                    new BasicDBObject("date", review.getDate()),
                    new BasicDBObject("comment", review.getComment()),
                    new BasicDBObject("userId", new ObjectId(review.getUserId()))
                )
        );

        Bson reviewComment = set("items.$.reviews", reviews);
        UpdateResult updateResult = writeToDatabase(bsonFilter, reviewComment);
        System.out.println(updateResult);
        return updateResult;
    }

    public static void writeUserToDB(){}

    public void writePaymentToDB(){}

    private static UpdateResult writeToDatabase(Bson bsonFilter, Bson bsonWriteOperation){
        try(MongoClient mongoClient = new MongoClient(new MongoClientURI(URI))){
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_ONLINE_ELECTRONICS_STORE);
            return collection.updateOne(bsonFilter, bsonWriteOperation);
        }
    }
}
