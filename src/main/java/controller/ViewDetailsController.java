package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Item;
import model.OnlineElectronicsStore;
import model.ShoppingCart;
import utils.MVCController;
import utils.UILoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class ViewDetailsController extends MVCController<OnlineElectronicsStore> {
    
    @FXML
    private ImageView image;


    @FXML
    private Label name;
    
    @FXML
    private Label ID;
    
    @FXML
    private Label cost;
    
    
    

    @FXML
    private Button AddToCart;
    
    
    @FXML
    private Button Return;
    
    
    @FXML
    private Label description;

 public final Item getItem(){
 return getModel().getItems().get(0);
}
    
public final List<Item> getItems(){
  return getModel().getItems();
 }

    public final OnlineElectronicsStore getOnlineElectronicsStore(){
        return getModel();
    }
    
    public void initialize(URL url, ResourceBundle rb) {
      
    }

    
  
    
    public void initData(Item item) {

      ID.setText(item.getID());
      name.setText(item.getName());
      description.setText(item.getDescription());
    }

    
  
    
    
    
    
    @FXML
    public void handleAddToCart(ActionEvent event) throws IOException {
        UILoader.render(new Stage(), getModel(), "/view/ShoppingCart.fxml", "Shopping Cart");
    }
    
    
    @FXML
    public void handleReturn(ActionEvent event) throws IOException {
        UILoader.render(new Stage(), getModel(), "/view/ViewAllItems.fxml", "Title");
    }

    
    

}
