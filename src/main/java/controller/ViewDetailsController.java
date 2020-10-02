package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
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
    private ImageView itemImageView;

    @FXML
    private Text idText;

    @FXML
    private Text nameText;

    @FXML
    private Text costText;

    @FXML
    private Text descriptionText;

    @FXML
    private Text addToCartStatusText;

    @FXML private void initialize(){
        Item item = getCurrentlySelectedItem();
        itemImageView.setImage(item.getConvertBase64toImage());
        idText.setText("ID: " + item.getId());
        nameText.setText("name: " + item.getName());
        costText.setText("cost: " + item.getCost());
        descriptionText.setText("description: " + item.getDescription());
    }
    
    @FXML
    public void handleAddToCart() {
        getModel()
                .getLoggedInUser()
                .getShoppingCart()
                .addItem(getCurrentlySelectedItem().getId(), 1);
        addToCartStatusText.setText("Added item to cart");
    }

    private Item getCurrentlySelectedItem(){
        return getModel().getItemById(getModel().getCurrentlySelectedItem());
    }
}
