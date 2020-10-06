package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OnlineElectronicsStoreTest {
    @Test
    public void getItemByIdTest(){
        Item item1 = new Item(null, "1", null, 0.0, null, null);
        Item item2 = new Item(null, "2", null, 0.0, null, null);
        Item item3 = new Item(null, "3", null, 0.0, null, null);
        ObservableList<Item> items = FXCollections.observableArrayList();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        OnlineElectronicsStore onlineElectronicsStore = new OnlineElectronicsStore();
        onlineElectronicsStore.setItems(items);
        Item actualItem = onlineElectronicsStore.getItemById("2");

        Assertions.assertEquals(item2, actualItem);
    }

    @Test
    public void getSortedItemsByCostDescTest(){
        Item item1 = new Item(null, "1", "a", 1.0, null, null);
        Item item2 = new Item(null, "2", "b", 2.0, null, null);
        Item item3 = new Item(null, "3", "c", 3.0, null, null);
        ObservableList<Item> items = FXCollections.observableArrayList();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        List<Item> expectedSortedItems = new ArrayList<>();
        expectedSortedItems.add(item3);
        expectedSortedItems.add(item2);
        expectedSortedItems.add(item1);

        OnlineElectronicsStore onlineElectronicsStore = new OnlineElectronicsStore();
        onlineElectronicsStore.setItems(items);
        List<Item> actualSortedItems = onlineElectronicsStore.getSortedItems("cost", "DESC");

        Assertions.assertEquals(expectedSortedItems, actualSortedItems);
    }

    @Test
    public void getSortedItemsByNameDescTest(){
        Item item1 = new Item(null, "1", "c", 1.0, null, null);
        Item item2 = new Item(null, "2", "a", 2.0, null, null);
        Item item3 = new Item(null, "3", "b", 3.0, null, null);
        ObservableList<Item> items = FXCollections.observableArrayList();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        List<Item> expectedSortedItems = new ArrayList<>();
        expectedSortedItems.add(item1);
        expectedSortedItems.add(item3);
        expectedSortedItems.add(item2);

        OnlineElectronicsStore onlineElectronicsStore = new OnlineElectronicsStore();
        onlineElectronicsStore.setItems(items);
        List<Item> actualSortedItems = onlineElectronicsStore.getSortedItems("name", "DESC");

        Assertions.assertEquals(expectedSortedItems, actualSortedItems);
    }

    @Test
    public void getSortedItemsByIdDescTest(){
        Item item1 = new Item(null, "1", "c", 1.0, null, null);
        Item item2 = new Item(null, "2", "a", 2.0, null, null);
        Item item3 = new Item(null, "3", "b", 3.0, null, null);
        ObservableList<Item> items = FXCollections.observableArrayList();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        List<Item> expectedSortedItems = new ArrayList<>();
        expectedSortedItems.add(item3);
        expectedSortedItems.add(item2);
        expectedSortedItems.add(item1);

        OnlineElectronicsStore onlineElectronicsStore = new OnlineElectronicsStore();
        onlineElectronicsStore.setItems(items);
        List<Item> actualSortedItems = onlineElectronicsStore.getSortedItems("id", "DESC");

        Assertions.assertEquals(expectedSortedItems, actualSortedItems);
    }
}