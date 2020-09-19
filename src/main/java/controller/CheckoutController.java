package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.OnlineElectronicsStore;
import model.ShoppingCart;
import utils.MVCController;

public class CheckoutController extends MVCController<OnlineElectronicsStore> {
    @FXML
    private Text totalCostText;

    @FXML
    private TextField promoCodeTextField;

    @FXML
    private Button usePromoCodeButton;

    @FXML private void initialize(){
        totalCostText.setText("Total Cost: " + getModel().getLoggedInUser().getShoppingCart().getTotalCost());
    }

    public final ShoppingCart getShoppingCart(){
        return getModel().getLoggedInUser().getShoppingCart();
    }

    @FXML
    private void handleUsePromoCode(){

    }
}
