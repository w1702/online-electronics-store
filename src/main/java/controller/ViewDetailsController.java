package controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Item;
import model.OnlineElectronicsStore;
import utils.MVCController;


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

    @FXML private void initialize(){ ;
        Item item = getCurrentItem();
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
                .addItem(getCurrentItem().getId(), 1);
        addToCartStatusText.setText("Added item to cart");
    }

    public Item getCurrentItem(){
        return getModel().getItemById(getModel().getCurrentlySelectedItem());
    }
}
