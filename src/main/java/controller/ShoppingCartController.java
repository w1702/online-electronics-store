
package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.OnlineElectronicsStore;
import model.ShoppingCart;
import utils.MVCController;
import utils.UILoader;
import javafx.scene.control.TableView;

public class ShoppingCartController extends MVCController<OnlineElectronicsStore> {

	
    @FXML
    private Button Checkout;

    @FXML
    private Button Back;

    @FXML
    private TableView<?> shoppingCartTableView;

    @FXML
    private Button Reset;
    



    @FXML
    void handleReset(ActionEvent event) {

    }

    @FXML
    void handleCheckout() throws IOException {
    	
    UILoader.render(new Stage(), getModel(), "/view/Checkout.fxml", "Checkout");
    
    
        }


  

    @FXML
    void handleBack(ActionEvent event) throws IOException {
    	
    	UILoader.render(new Stage(), getModel(), "/view/ViewAllItems.fxml", "Title");
    	

    }




    public final ShoppingCart getShoppingCart(){
        return getModel().getLoggedInUser().getShoppingCart();
    
    
}
}








