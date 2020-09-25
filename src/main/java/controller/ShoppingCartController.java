package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.OnlineElectronicsStore;
import model.ShoppingCart;
import utils.MVCController;

public class ShoppingCartController extends MVCController<OnlineElectronicsStore> {
    @FXML
    private TableView shoppingCartTableView;

    public final OnlineElectronicsStore getOnlineElectronicsStore(){
        return getModel();
    }

}
