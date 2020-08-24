package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.OnlineElectronicsStore;
import model.ShoppingCart;
import utils.MVCController;

public class CheckoutController extends MVCController<OnlineElectronicsStore> {
    @FXML
    private TableView checkoutTableView;

    public final OnlineElectronicsStore getOnlineElectronicsStore(){
        return getModel();
    }

    public final ShoppingCart getShoppingCart(){
        // todo: this gets the first user as of right now, should be getting the currently logged in user
        return getModel().getUsers().get(0).getShoppingCart();
    }
}
