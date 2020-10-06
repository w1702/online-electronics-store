package model;

import db.DatabaseReadClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OnlineElectronicsStoreIntegrationTest {

    @Test
    public void givenDBConnectionSuccess_whenInitialisingApp_thenVerifyAppModelsContainData(){
        DatabaseReadClient databaseClient = new DatabaseReadClient();
        OnlineElectronicsStore onlineElectronicsStore = new OnlineElectronicsStore(databaseClient);
        Assertions.assertNotNull(onlineElectronicsStore.getItems());
        Assertions.assertNotNull(onlineElectronicsStore.getUsers());
        Assertions.assertNotNull(onlineElectronicsStore.getPromoCode());
        Assertions.assertNotNull(onlineElectronicsStore.getDiscountValue());
    }
}