package model;

import javafx.collections.ObservableList;

import java.util.List;

public class Order {
    private ObservableList<Item> items;
    private Payment payment;
    private Shipment shipment;
}
