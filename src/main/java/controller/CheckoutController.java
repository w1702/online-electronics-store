package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.OnlineElectronicsStore;
import model.ShoppingCart;
import utils.MVCController;
import utils.UILoader;

import java.io.IOException;

public class CheckoutController extends MVCController<OnlineElectronicsStore> {
    @FXML
    private Text totalCostText;

    @FXML
    private TextField promoCodeTextField;

    @FXML
    private Button usePromoCodeButton;

    @FXML
    private Text discountValueText;

    @FXML
    private Text subTotalCostText;

    @FXML private void initialize(){
        setTotalCostText();
    }

    public final ShoppingCart getShoppingCart(){
        return getModel().getLoggedInUser().getShoppingCart();
    }

    @FXML
    private void handleUsePromoCode(){
        String totalCostBeforeDiscount = totalCostText.getText();
        if(promoCodeTextField.getCharacters().toString().equals(getModel().getPromoCode())){
            getShoppingCart().setPromoCodeUsed(true);
        }
        setTotalCostText();
        discountValueText.setText("Before discount " + totalCostBeforeDiscount + " (discount applied: " + getModel().getDiscountValue() + ")");
    }

    @FXML
    private void handleProceedWithCheckout() throws IOException {
        UILoader.render(new Stage(), getModel(), "/view/Payment.fxml", "Payment");
    }

    private void setTotalCostText(){
        totalCostText.setText("Total cost: " + getModel().getLoggedInUser().getShoppingCart().getTotalCost());
    }
}
