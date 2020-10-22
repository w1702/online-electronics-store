package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                if(item != null){
                    checkoutItems.add(item);
                }
            }
        }
        return checkoutItems;
    }

    public void addItem(String itemId, Integer quantity){
        Integer oldQuantity = itemQuantity.get(itemId);
        if(oldQuantity != null){
            Integer newQuantity = oldQuantity + quantity;
            itemQuantity.put(itemId, newQuantity);
        }
        else{
            itemQuantity.put(itemId, quantity);
        }
    }

    public void removeItem(String itemId){
        if(itemQuantity.size() > 0){
            for (String id : itemQuantity.keySet()) {
                if(id.equals(itemId) && itemQuantity.get(id) > 0){
                    itemQuantity.put(itemId, itemQuantity.get(id) - 1);
                }
            }
        }
    }

    public ObservableMap<String, Integer> getItemQuantity() {
        return itemQuantity;
    }

    public double getTotalCost(){
        if(promoCodeUsed) {
            return getTotalCostBeforeDiscounts() * (1 - onlineElectronicsStore.getDiscountValue());
        }
        return getTotalCostBeforeDiscounts();
    }

    public double getTotalCostBeforeDiscounts(){
        double totalCost = 0;
        for (String itemId : itemQuantity.keySet()) {
            Item item = onlineElectronicsStore.getItemById(itemId);
            totalCost += item.getCost() * itemQuantity.get(itemId);
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
