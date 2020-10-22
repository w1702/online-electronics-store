package model;

import javafx.collections.ObservableList;

public class Order {
    private ObservableList<Item> items;
    private Payment payment;
    private Shipment shipment;


public void setPayment(Payment payment) {
    this.payment = payment;
}

public void setItems(ObservableList<Item> items) {
    this.items = items;
}

public Payment getPayment() {
    return payment;
}

public ObservableList<Item> getItems() {
    return items;
}
}
