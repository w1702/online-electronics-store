package db;

import org.bson.Document;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseClientIntegrationTest {
    @Test
    public void givenDBCredentials_whenConnectionEstablished_thenVerifyDocumentsAreRetrived(){
        DatabaseClient databaseClient = new DatabaseClient();
        Document appData = databaseClient.getAppData();
        String json = appData.toJson();


    }
}