package db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
        List<Document> documents = new ArrayList<Document>();
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

    /**
     * Get the document containing our application data
     * @return the Document object
     */
    public Document getAppData(){
        return appData;
    }
}
