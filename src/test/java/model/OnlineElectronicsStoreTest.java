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
        List<Item> actualSortedItems = onlineElectronicsStore.getSortedItems("cost", "DESC", "", 0, 999999999);

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
        List<Item> actualSortedItems = onlineElectronicsStore.getSortedItems("name", "DESC", "", 0, 999999999);

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
        List<Item> actualSortedItems = onlineElectronicsStore.getSortedItems("id", "DESC","", 0, 999999999);

        Assertions.assertEquals(expectedSortedItems, actualSortedItems);
    }
    
    // Tests for Release 2 ---------------------------------------------------------------------------------------------------------->
    
    @Test
    public void getItemByNameTest(){
        Item item1 = new Item(null, "1", "TV", 1.0, "", null);
        Item item2 = new Item(null, "2", "Laptop", 2.0, "", null);
        Item item3 = new Item(null, "3", "Washing Machine", 3.0, "", null);
        ObservableList<Item> items = FXCollections.observableArrayList();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        
        List<Item> expectedSortedItems = new ArrayList<>();
        expectedSortedItems.add(item1);
        
        OnlineElectronicsStore onlineElectronicsStore = new OnlineElectronicsStore();
        onlineElectronicsStore.setItems(items);
        List<Item> actualItems = onlineElectronicsStore.getItemByKeywords("tv", 0, 999999999);
        Assertions.assertEquals(expectedSortedItems, actualItems);
    }
    
    @Test
    public void getItemByKeywordInNameNDescriptionTest(){
        Item item1 = new Item(null, "1", "Good TV", 1.0, "This is a powerful TV.", null);
        Item item2 = new Item(null, "2", "Laptop", 2.0, "This laptop is good.", null);
        Item item3 = new Item(null, "3", "Washing Machine", 3.0, "Worth buying!", null);
        ObservableList<Item> items = FXCollections.observableArrayList();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        
        List<Item> expectedSortedItems = new ArrayList<>();
        expectedSortedItems.add(item1);
        expectedSortedItems.add(item2);
        
        OnlineElectronicsStore onlineElectronicsStore = new OnlineElectronicsStore();
        onlineElectronicsStore.setItems(items);
        List<Item> actualSortedItems = onlineElectronicsStore.getItemByKeywords("good", 0, 999999999);

        Assertions.assertEquals(expectedSortedItems, actualSortedItems);
    }
    
    @Test
    public void getItemByKeywordPriceRangeTest(){
        Item item1 = new Item(null, "1", "Good TV", 100.0, "This is a powerful TV.", null);
        Item item2 = new Item(null, "2", "Laptop", 2.0, "This laptop is good.", null);
        Item item3 = new Item(null, "3", "Washing Machine", 3.0, "Worth buying!", null);
        ObservableList<Item> items = FXCollections.observableArrayList();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        
        List<Item> expectedSortedItems = new ArrayList<>();
        expectedSortedItems.add(item2);
        
        OnlineElectronicsStore onlineElectronicsStore = new OnlineElectronicsStore();
        onlineElectronicsStore.setItems(items);
        List<Item> actualSortedItems = onlineElectronicsStore.getItemByKeywords("good", 1, 10);

        Assertions.assertEquals(expectedSortedItems, actualSortedItems);
    }
    
    @Test
    public void getSortItemByKeywordPriceRangeCostDescTest(){
        Item item1 = new Item(null, "1", "Good TV", 50.0, "This is a powerful TV.", null);
        Item item2 = new Item(null, "2", "Laptop", 82.0, "This laptop is good.", null);
        Item item3 = new Item(null, "3", "Washing Machine", 300.0, "Good! Worth buying.", null);
        Item item4 = new Item(null, "4", "Dryer", 64.0, "Excellent dryer.", null);
        ObservableList<Item> items = FXCollections.observableArrayList();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        
        List<Item> expectedSortedItems = new ArrayList<>();
        expectedSortedItems.add(item2);
        expectedSortedItems.add(item1);
        
        OnlineElectronicsStore onlineElectronicsStore = new OnlineElectronicsStore();
        onlineElectronicsStore.setItems(items);
        List<Item> actualSortedItems = onlineElectronicsStore.getSortedItems("cost", "DESC","good", 50, 100);

        Assertions.assertEquals(expectedSortedItems, actualSortedItems);
    }
    
    @Test
    public void getSortItemByKeywordPriceRangeNameDescTest(){
        Item item1 = new Item(null, "1", "Good TV", 50.0, "This is a powerful TV.", null);
        Item item2 = new Item(null, "2", "Laptop", 82.0, "This laptop is good.", null);
        Item item3 = new Item(null, "3", "Washing Machine", 300.0, "Good! Worth buying.", null);
        Item item4 = new Item(null, "4", "Dryer", 64.0, "Excellent dryer.", null);
        ObservableList<Item> items = FXCollections.observableArrayList();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        
        List<Item> expectedSortedItems = new ArrayList<>();
        expectedSortedItems.add(item2);
        expectedSortedItems.add(item1);
        
        OnlineElectronicsStore onlineElectronicsStore = new OnlineElectronicsStore();
        onlineElectronicsStore.setItems(items);
        List<Item> actualSortedItems = onlineElectronicsStore.getSortedItems("name", "DESC","good", 50, 100);

        Assertions.assertEquals(expectedSortedItems, actualSortedItems);
    }
    
    @Test
    public void getSortItemByKeywordPriceRangeIdDescTest(){
        Item item1 = new Item(null, "1", "Good TV", 50.0, "This is a powerful TV.", null);
        Item item2 = new Item(null, "2", "Laptop", 82.0, "This laptop is good.", null);
        Item item3 = new Item(null, "3", "Washing Machine", 300.0, "Good! Worth buying.", null);
        Item item4 = new Item(null, "4", "Dryer", 64.0, "Excellent dryer.", null);
        ObservableList<Item> items = FXCollections.observableArrayList();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        
        List<Item> expectedSortedItems = new ArrayList<>();
        expectedSortedItems.add(item2);
        expectedSortedItems.add(item1);
        
        OnlineElectronicsStore onlineElectronicsStore = new OnlineElectronicsStore();
        onlineElectronicsStore.setItems(items);
        List<Item> actualSortedItems = onlineElectronicsStore.getSortedItems("id", "DESC","good", 50, 100);

        Assertions.assertEquals(expectedSortedItems, actualSortedItems);
    }
}