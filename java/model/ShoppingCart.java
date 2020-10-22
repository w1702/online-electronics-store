package model;

import java.util.Dictionary;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class ShoppingCart {
    private OnlineElectronicsStore onlineElectronicsStore;
    private ObservableMap<String, Integer> itemQuantity;
    private boolean promoCodeUsed;
    private ObservableList<Item> items;

    private Dictionary<String, String> CardPayment;
    private Dictionary<String, String> ApplePayment;

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
        itemQuantity.put(itemId, quantity);
    }

    
    public void deleteItem(String itemId){
        itemQuantity.remove(itemId);
    }
    // clear when payment success
    public void clear() {
        itemQuantity.clear();
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
