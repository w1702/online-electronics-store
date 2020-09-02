package controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.OnlineElectronicsStore;
import model.ShoppingCart;
import utils.MVCController;

public class CheckoutController extends MVCController<OnlineElectronicsStore> {
    @FXML
    private Text totalCostText;


    @FXML private void initialize(){
        totalCostText.setText("Total Cost: " + getModel().getLoggedInUser().getShoppingCart().getTotalCost());
    }

    public final ShoppingCart getShoppingCart(){
        return getModel().getLoggedInUser().getShoppingCart();
    }
}
