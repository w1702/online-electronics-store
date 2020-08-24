package model;

import db.DatabaseClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OnlineElectronicsStoreIntegrationTest {

    @Test
    public void givenDBConnectionSuccess_whenInitialisingApp_thenVerifyAppModelsContainData(){
        DatabaseClient databaseClient = new DatabaseClient();
        OnlineElectronicsStore onlineElectronicsStore = new OnlineElectronicsStore(databaseClient);
        Assertions.assertNotNull(onlineElectronicsStore.getUsers());
    }
}