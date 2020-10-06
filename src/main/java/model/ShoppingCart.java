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
        Integer oldQuantity = itemQuantity.get(itemId);
        if(oldQuantity != null){
            Integer newQuantity = oldQuantity + quantity;
            itemQuantity.put(itemId, newQuantity);
        }
        else{
            itemQuantity.put(itemId, quantity);
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
