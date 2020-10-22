package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import model.Item;

import model.OnlineElectronicsStore;
import model.ShoppingCart;
import utils.MVCController;
import utils.UILoader;

import java.awt.ItemSelectable;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CheckoutController extends MVCController<OnlineElectronicsStore> {
	
	
	@FXML
    private Text totalCostText;

    
    @FXML
    private TableView<Item> items = new TableView<>();
    
    @FXML private TableColumn<Item, String> idClm;
	@FXML private TableColumn<Item, String> nameClm;
	@FXML private TableColumn<Item, String> costClm;
	@FXML private TableColumn<Item, String> desClm;
	
	
    @FXML
    private TextField promoCodeTextField;

    @FXML
    private Button usePromoCodeButton;
    
    @FXML
    private Button handleRemoveItem;

    @FXML
    private Button handleClear;

    @FXML
    private Text discountValueText;

    @FXML
    
    private Text checkoutStatusText;
    
   
    @FXML private void initialize(){
    	
    	idClm.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		nameClm.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		costClm.setCellValueFactory(cellData -> cellData.getValue().costProperty().asString("$%.2f"));
		desClm.setCellValueFactory(cellData -> cellData.getValue().desProperty());
		
  
        setTotalCostText();
    }

    public List<Item> getSelectedParts(){ 
        return items.getSelectionModel().getSelectedItems(); 
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
    private void handleRemoveItem(ActionEvent event){
    	
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
    "Are you sure you want to delete this item?",
    ButtonType.YES, ButtonType.NO);
   Optional<ButtonType> buttonType = alert.showAndWait();
   if (buttonType.get() == ButtonType.YES) {
 

    	
    	String id = items.getSelectionModel().getSelectedItem().getId();
    	//System.out.println(id);
    	getModel()
    	        .getLoggedInUser()
    	        .getShoppingCart()
    	        .deleteItem(id);
    	items.refresh();
   }
    	

    }
    
    
    
    @FXML
    private void handleClear(ActionEvent event){
    	
    	getModel()
        .getLoggedInUser()
        .getShoppingCart()
        .clear();
       items.refresh();
    	
    }


    
    @FXML
    private void handleProceedWithCheckout() throws IOException {
        if(getShoppingCart().getItemQuantity().isEmpty()) {
            checkoutStatusText.setText("Cannot checkout with no items in shopping cart");
        }
        else{
            UILoader.render(new Stage(), getModel(), "/view/Payment.fxml", "Payment");

            // close
            Stage stage = (Stage) checkoutStatusText.getScene().getWindow();
            stage.close();
        }
    }

    private void setTotalCostText(){
        totalCostText.setText("Total cost: " + getModel().getLoggedInUser().getShoppingCart().getTotalCost());
    }
    
   
    
}
