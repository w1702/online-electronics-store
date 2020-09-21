package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class ShoppingCart {
    private OnlineElectronicsStore onlineElectronicsStore;
    private ObservableMap<String, Integer> itemQuantity;
    private boolean promoCodeUsed;

    public ShoppingCart(ObservableMap<String, Integer> itemQuantity, boolean promoCodeUsed){
        this.itemQuantity = itemQuantity;
        this.promoCodeUsed = promoCodeUsed;
    }

    public ObservableList<Item> getItemsAsList(){
        ObservableList<Item> checkoutItems = FXCollections.observableArrayList();
        for (String itemId : itemQuantity.keySet()) {
            Integer quantity = itemQuantity.get(itemId);
            for(int i = 0; i < quantity; i++ ){
                Item item = onlineElectronicsStore.getItemById(itemId);
                checkoutItems.add(item);
            }
        }
        return checkoutItems;
    }

    public void addItem(String itemId, Integer quantity){
        // todo: add to db
        itemQuantity.put(itemId, quantity);
    }

    public ObservableMap<String, Integer> getItemQuantity() {
        return itemQuantity;
    }

    // todo: write unit test for this
    public double getTotalCost(){
        double totalCost = 0;
        for (String itemId : itemQuantity.keySet()) {
            Item item = onlineElectronicsStore.getItemById(itemId);
            totalCost += item.getCost() * itemQuantity.get(itemId);
        }
        if(promoCodeUsed) {
            totalCost = totalCost * (1 - onlineElectronicsStore.getDiscountValue());
        }
        return totalCost;
    }

    public void setPromoCodeUsed(boolean promoCodeUsed) {
        this.promoCodeUsed = promoCodeUsed;
    }


    // todo: revise this hacky code
    // for setting shopping cart property manually
    void setOnlineElectronicsStore(OnlineElectronicsStore onlineElectronicsStore){
        this.onlineElectronicsStore = onlineElectronicsStore;
    }
}
