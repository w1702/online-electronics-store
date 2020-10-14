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
    private Text discountValueText;

    @FXML
    private Text checkoutStatusText;

    @FXML private void initialize(){
        setTotalCostText();
    }

    public final ShoppingCart getShoppingCart(){
        return getModel().getLoggedInUser().getShoppingCart();
    }

    @FXML
    private void handleUsePromoCode(){
        if(promoCodeTextField.getCharacters().toString().equals(getModel().getPromoCode())){
            getShoppingCart().setPromoCodeUsed(true);
            discountValueText.setText("Before discount: " + getShoppingCart().getTotalCostBeforeDiscounts() + " (discount applied: " + getModel().getDiscountValue() + ")");
        }
        else{
            discountValueText.setText("Not a valid promo code");
        }
        setTotalCostText();
    }

    @FXML
    private void handleRemoveItem(){
        // todo: Yifan's part
    }

    @FXML
    private void handleProceedWithCheckout() throws IOException {
        if(getShoppingCart().getItemQuantity().isEmpty()) {
            checkoutStatusText.setText("Cannot checkout with no items in shopping cart");
        }
        else{
            UILoader.render(new Stage(), getModel(), "/view/Payment.fxml", "Payment");
        }
    }

    private void setTotalCostText(){
        totalCostText.setText("Total cost: " + getModel().getLoggedInUser().getShoppingCart().getTotalCost());
    }
}
